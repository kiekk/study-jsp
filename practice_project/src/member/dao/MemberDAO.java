package member.dao;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.mybatis.config.SqlMapConfig;

import member.model.MemberVO;

public class MemberDAO {
	SqlSessionFactory sessionFactory = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public MemberDAO() {
		sqlsession = sessionFactory.openSession(true);
	}
	
	public MemberVO selectById(String id) {
		if((sqlsession.selectOne("Member2.selectById",id) != null)) {
			MemberVO member = new MemberVO();
			member = (MemberVO) sqlsession.selectList("Member2.selectById",id);
			return member;
		}
		return null;
	}
	
	public Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}
	
	public boolean insert(MemberVO member) {
		int result = sqlsession.insert("Member2.insert",member);
		if(result == 1) {
			return true;
		}
		return false;
	}
}
