package com.board.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.board.mybatis.config.SqlMapConfig;
import com.board.vo.MemberVO;

public class MemberDAO {
	SqlSessionFactory session_f = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public MemberDAO() {
		sqlsession = session_f.openSession(true);	//true하면 자동 commit
	}
	//아이디 중복 검사
	public boolean checkId(String id) {
		int result = sqlsession.selectOne("Member2.checkId", id);
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
		int result = sqlsession.selectOne("Member2.login", user);
		
		if(result == 1) {
			return true;
		}
		return false;
	}
	//회원가입
	public boolean join(MemberVO member) {
		Map<String,String> user = new HashMap<>();
		Map<String,String> user_hobby = new HashMap<>();
		user.put("id", member.getId());
		user.put("pw", member.getPw());
		user.put("name", member.getName());
		user.put("email", member.getEmail());
		String hobby = "";
		for(int i=0;i<member.getHobby().length;i++) {
			hobby += member.getHobby()[i] + ",";
		}
		hobby = hobby.substring(0, hobby.length()-1);
		user_hobby.put("id", member.getId());
		user_hobby.put("hobby", hobby);
		int result = sqlsession.insert("Member2.join_member",user);
		if(result == 1) {
			int result2 = sqlsession.insert("Member2.join_hobby",user_hobby);
			if(result2 == 1) {
				return true;				
			}
		}
		return false;
	}
	
	//아이디 찾기
	public String findId(String name, String email) {
		String user_id = null;
		Map<String,String> user = new HashMap<>();
		user.put("name", name);
		user.put("email", email);
		
		user_id = sqlsession.selectOne("Member2.find_id",user);
		
		user_id = user_id.substring(0, user_id.length()-2) + "**";
		
		return user_id;
	}
	
	//비밀번호 찾기
	public String findPw(String id, String name) {
		String pw = null;
		Map<String,String> user = new HashMap<>();
		user.put("id", id);
		user.put("name", name);
		
		pw = sqlsession.selectOne("Member2.find_pw",user);
		
		return pw;
	}
}
