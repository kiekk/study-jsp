<%@page import="com.board.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean class="com.board.dao.MemberDAO" id="m_dao"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	if(m_dao.login(id, pw)){
		session.setAttribute("id", id);
		response.sendRedirect("index2.html");
	}else {
%>
	<script>
		alert("아이디 또는 패스워드를 확인해 주세요.");
		location.href = "login.html";
	</script>
<%
	}
%>
</body>
</html>