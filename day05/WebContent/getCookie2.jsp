<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 출력, 쿠키 삭제</title>
</head>
<body>
	<%
		String cookie_check = request.getHeader("Cookie");
		String id = "";
		if(cookie_check != null){
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("id")){
					id = cookie.getValue();
					break;
				}
			}
	%>
	<p>수정된 아이디 : <%=id%></p>
	<%		
			for(Cookie cookie : cookies){
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
	%>
	<%
		}
	%>
	<a href="delete_check.jsp">쿠키 삭제 확인</a>
</body>
</html>