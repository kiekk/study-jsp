<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
</head>
<body>
	회원가입이 완료되었습니다.
	<%
		String name = "";
		String cookie = request.getHeader("Cookie");
		if(cookie != null){
			Cookie[] cookies = request.getCookies();
			for(Cookie cook : cookies){
				if(cook.getName().equals("name")){
					name = cook.getValue();
				}
			}
		}
	%>
	<%= name %>님 환영합니다.
</body>
</html>