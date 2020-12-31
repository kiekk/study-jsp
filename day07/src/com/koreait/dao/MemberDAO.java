package com.koreait.dao;

import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.mybatis.config.SqlMapConfig;
import com.koreait.vo.MemberVO;

public class MemberDAO {
	SqlSessionFactory session_f = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public MemberDAO() {
		sqlsession = session_f.openSession(true);	//true하면 자동 commit
	}
	//아이디 중복 검사
	public boolean checkId(String id) {
		int result = sqlsession.selectOne("Member.checkId", id);
		if(result == 1) {
			return true;
		}
		return false;
	}
	//로그인
	public boolean login(String id, String pw) {
		Map<String,String> user = new HashMap<>();
		user.put("id", id);
		user.put("pw", pw);
		int result = sqlsession.selectOne("Member.login", user);
		
		if(result == 1) {
			return true;
		}
		return false;
	}
	//회원가입
	public boolean join(MemberVO member) {
		Map<String,String> user = new HashMap<>();
		user.put("name", member.getName());
		user.put("id", member.getId());
		user.put("pw", member.getPw());
		user.put("gender", member.getGender());
		user.put("zipcode", member.getZipcode());
		user.put("address", member.getAddress());
		user.put("address_detail", member.getAddress_detail());
		user.put("address_etc", member.getAddress_etc());
		user.put("hp", member.getHp());
		String hobby = "";
		for(int i=0;i<member.getHobby().length;i++) {
			if(i == (member.getHobby().length - 1)) {
				hobby += member.getHobby()[i];
			}else {
				hobby += member.getHobby()[i] + ",";					
			}
		}
		user.put("hobby", hobby);

		int result = sqlsession.insert("Member.join",user);
		if(result == 1) {
			return true;
		}
		return false;
	}
}
