package com.koreait.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class MemberDAO {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	MemberVO member;
	
	public ArrayList<MemberVO> select(String name){
		String query = "SELECT * FROM \"MEMBER\" WHERE NAME = ?";
		ArrayList<MemberVO> members = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		
		try {
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				member = new MemberVO();
				member.setName(rs.getString("NAME"));
				member.setBirthday(sdf.format(rs.getDate("BIRTHDAY")));
				members.add(member);
			}
		} catch (SQLException sqle) {
			System.out.println("select() 쿼리 오류");
		} catch (Exception e) {
			System.out.println("select(String name 알 수 없는 오류 ");
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return members;
	}
}
