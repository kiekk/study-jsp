<%@page import="com.koreait.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean class="com.koreait.dao.MemberDAO" id="m_dao"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
	String user_id = request.getParameter("user_id");
	String pw = request.getParameter("pw");
	
	if(m_dao.login(user_id, pw)){
		session.setAttribute("user_id", user_id);
		response.sendRedirect("login_session.jsp");
	}else {
%>
	<script>
		alert("아이디 또는 패스워드를 확인해 주세요.");
		location.href = "login_session.jsp";
	</script>
<%
	}
%>
</body>
</html>