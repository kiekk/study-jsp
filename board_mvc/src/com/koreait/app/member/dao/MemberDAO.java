package com.koreait.app.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.app.member.vo.MemberVO;
import com.koreait.mybatis.config.SqlMapConfig;

public class MemberDAO {
	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public MemberDAO() {
		sqlsession = sessionf.openSession(true);
	}
	
	public boolean join(MemberVO member) {
		if(sqlsession.insert("Member.join", member) == 1) {
			return true;
		}
		return false;
	}
	
}
