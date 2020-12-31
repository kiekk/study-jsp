package member.service;

import java.util.Date;

import member.dao.MemberDAO;
import member.model.MemberVO;

public class JoinService {
	private MemberDAO memberDAO = new MemberDAO();
	
	public void join(JoinRequest joinReq) {
		MemberVO member = memberDAO.selectById(joinReq.getId());
		if(member != null) {
			throw new DuplicateIdException();
		}
		
		memberDAO.insert(new MemberVO(
					joinReq.getId(),
					joinReq.getName(),
					joinReq.getPassword(),
					new Date()
				));
	}
}
