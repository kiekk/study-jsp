<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트 태그 예제</title>
</head>
<body>
	<%
		out.print(myMethod(0));
	%>
	
	<%-- 선언문 태그[전역 메소드] --%>
	<%!
		public int myMethod(int count){
			return ++count;
		}
	%>
	
</body>
</html>