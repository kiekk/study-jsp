<%@page import="java.util.ArrayList"%>
<%@page import="com.koreait.beans.MemberDAO"%>
<%@page import="com.koreait.beans.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class="com.koreait.beans.MemberVO" id="member"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바빈즈 예제</title>
</head>
<body>
	<%--
	<jsp:setProperty property="name" value="한동석" name="member"/>
	<jsp:setProperty property="birthday" value="12-04" name="member"/>
	 
	
	<jsp:getProperty property="name" name="member"/>
	<jsp:getProperty property="birthday" name="member"/>
	--%>
	<!-- 
		table을 만들고 DAO의 메소드를 실행한 결과 값의 개수만큼 행을 만들어서 출력
	 -->
	 <table border="1">
	 	<%
			MemberDAO members = new MemberDAO();
		 	ArrayList<MemberVO> lists = members.select("윤순호");
	 		for(int i=0;i<lists.size();i++){
	 	%>
	 		<tr>
	 			<td><%= lists.get(i).getName() %></td>
	 			<td><%= lists.get(i).getBirthday() %></td>
	 		<tr>
	 	<%		
	 		}
	 	%>
	 </table>
</body>
</html>