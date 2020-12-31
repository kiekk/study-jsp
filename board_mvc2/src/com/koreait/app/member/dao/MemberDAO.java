package com.koreait.app.member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.app.member.vo.MemberVO;
import com.koreait.mybatis.config.SqlMapConfig;
//MyBatis를 통해 .xml 파일의 쿼리문을 사용합니다. (JAVA코드와 SQL문이 분리되어 있어서 가독성이 좋습니다.)
//쿼리문을 실행할 때 필요한 데이터를 매개변수 등으로 전달 받고
//실행한 결과를 리턴해주는 메소드가 모여있습니다.
//컨트롤러에서 선언하지 않고 DAO에 따로 분리하여 선언해 놓습니다.
public class MemberDAO {
	SqlSessionFactory sessionFactory = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public MemberDAO() {
		sqlsession = sessionFactory.openSession(true);
	}
	//회원 가입
	public boolean join(MemberVO member) {
		//외부에서 사용자가 입력한 정보를 DB에 저장합니다.
		if(sqlsession.insert("Member.join",member) == 1) {
			return true;
		}
		return false;
	}
	//중복 검사
	public boolean checkId(String id) {
		//아이디를 DB에서 조회한 후 검색된 결과 개수를 가져옵니다.
		if((Integer)sqlsession.selectOne("Member.checkId", id) == 1) {
			//중복이 있을 때
			return true;
		}
		//중복이 없을 때
		return false;
	}
	//로그인 처리
	public String login(Map<String,String> user) {
		//DB 조회 시 아이디와 비밀번호가 일치하면 해당 아이디를 리턴해줍니다.(세션을 사용하기 위함)
		return (String)sqlsession.selectOne("Member.login", user);
	}
}
