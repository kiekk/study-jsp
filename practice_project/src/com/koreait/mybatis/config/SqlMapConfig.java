package com.koreait.mybatis.config;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {
	//작성한 config.xml을 객체화 하는 클래스
	private static SqlSessionFactory sqlsession;
	
	//클래스 초기화 블럭
	static {
		try {
			String resource = "./com/koreait/mybatis/config/config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlsession = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			//xml파일을 불러오는 것이기 때문에 파일 입출력 Exception 처리를 해야 합니다.
		}catch(IOException ie) {
			System.out.println(ie);
			throw new RuntimeException("초기화 문제 발생,SqlMapConfig.java");
		}
	}
	
	public static SqlSessionFactory getSqlMapInstance() {
		return sqlsession;
	}
}
