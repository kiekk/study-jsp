<%@page import="util.Cookies"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	if(id.equals(password)){
		response.addCookie(Cookies.createCookies("AUTH", id, "/", -1));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
로그인에 성공했습니다.
<%
	}else {
%>
<script>
	alert("로그인에 실패했습니다.");
	history.go(-1);
</script>
<%
	}
%>
</body>
</html>