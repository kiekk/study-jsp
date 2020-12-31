package com.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.koreait.vo.MemberVO;

public class MemberDAO {
	Context context;
	DataSource dataSource;
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	
	public boolean checkId(String id) {
		boolean check = false;
		try {
			context = new InitialContext(null);
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			conn = dataSource.getConnection();
			
			String query = "SELECT COUNT(*) FROM MEMBER WHERE ID=?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			
			rs.next();
			
			if(rs.getInt(1) != 0) {
				check = true;
			}
			
		} catch (NamingException ne) {
			System.out.println(ne);
			System.out.println("checkId(String id) 오류 ne");
		} catch (SQLException sqle) {
			System.out.println(sqle);
			System.out.println("checkId(String id) 오류 sqle");
		} catch(Exception e) {
			System.out.println(e);
			System.out.println("checkId(String id) 오류 e");
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (SQLException sqle) {
				throw new RuntimeException(sqle.getMessage()+"\n checkId(String id) close 오류");
			}
		}
		return check;
	}
	
	//회원가입
	public boolean join(MemberVO member) {
		boolean check = false;
		try {
			context = new InitialContext(null);
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			conn = dataSource.getConnection();
			
			String query = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,?)";
			String hobby = "";
			for(int i=0;i<member.getHobby().length;i++) {
				if(i == (member.getHobby().length - 1)) {
					hobby += member.getHobby()[i];
				}else {
					hobby += member.getHobby()[i] + ",";					
				}
			}
			/*
			for(int i=0;i<member.getHobby().length;i++) {
				hobby += member.getHobby()[i] + ",";
			}
			hobby.substring(0, hobby.length()-1);
			*/
			pstm = conn.prepareStatement(query);
			int idx = 0;
			pstm.setString(++idx, member.getId());
			pstm.setString(++idx, member.getName());
			pstm.setString(++idx, member.getPw());
			pstm.setString(++idx, member.getGender());
			pstm.setString(++idx, member.getZipcode());
			pstm.setString(++idx, member.getAddress());
			pstm.setString(++idx, member.getAddress_detail());
			pstm.setString(++idx, member.getAddress_etc());
			pstm.setString(++idx, member.getHp());
			pstm.setString(++idx, hobby);
			
			if(pstm.executeUpdate() == 1) {
				check = true;
			}
			//취미는 ,를 넣어서 추가해줍니다.
			//ex)hobby컬럼 값 : 게임,영화
			
		} catch(NamingException ne) {
			System.out.println(ne);
			System.out.println("join(MemberVO member) ne");
		} catch(SQLException sqle) {
			System.out.println(sqle);
			System.out.println("join(MemberVO member) sqle");
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("join(MemberVO member) 오류 e");
		}finally {
			try {
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (SQLException sqle) {
				throw new RuntimeException(sqle.getMessage()+"\n join(MemberVO member) close 오류");
			}
		}
		return check;
	}
	
	//로그인
	public boolean login(String id, String pw) {
		boolean check = false;
		try {
			context = new InitialContext(null);
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			conn = dataSource.getConnection();
			
			String query = "SELECT COUNT(*) FROM MEMBER WHERE ID=? AND PW=?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			rs = pstm.executeQuery();
			
			rs.next();
			if(rs.getInt(1) == 1) {
				check = true;
			}
		} catch(NamingException ne) {
			System.out.println(ne);
			System.out.println("login(String id, String pw) ne");
		} catch(SQLException sqle) {
			System.out.println(sqle);
			System.out.println("login(String id, String pw) sqle");
		} catch(Exception e) {
			System.out.println(e);
			System.out.println("login(String id, String pw) 오류 e");
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (SQLException sqle) {
				throw new RuntimeException(sqle.getMessage()+"\n login(String id, String pw) close 오류");
			}
		}
		return check;
	}
	
	//정보보기
	public MemberVO userInfo(String id) {
		MemberVO member = new MemberVO();
		try {
			context = new InitialContext(null);
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			conn = dataSource.getConnection();
			
			String query = "SELECT * FROM MEMBER WHERE ID=?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setAddress(rs.getString("ADDRESS"));
				member.setAddress_detail(rs.getString("ADDRESS_DETAIL"));
				member.setAddress_etc(rs.getString("ADDRESS_ETC"));
				member.setGender(rs.getString("GENDER"));
				member.setZipcode(rs.getString("ZIPCODE"));
				member.setHp(rs.getString("HP"));
				member.setHobby(rs.getString("HOBBY").split(","));
			}
			
		} catch(NamingException ne) {
			System.out.println(ne);
			System.out.println("userInfo(String id) ne");
		} catch(SQLException sqle) {
			System.out.println(sqle);
			System.out.println("userInfo(String id) sqle");
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("userInfo(String id) 오류 e");
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (SQLException sqle) {
				throw new RuntimeException(sqle.getMessage()+"\n userInfo(String id) close 오류");
			}
		}
		return member;
	}
}
