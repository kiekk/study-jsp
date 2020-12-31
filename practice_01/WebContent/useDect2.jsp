<%@ page language="java" charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%!
	public int add(int a, int b){
		int c = a + b;
		return c;
	}
	public int subtract(int a, int b){
		int c = a - b;
		return c;
	}
%>
<title>Insert title here</title>
</head>
<body>
<%
	int num1 = 9;
	int num2 = 5;
	
	int addResult = add(num1, num2);
	int subtractResult = subtract(num1, num2);
%>
<%= num1 %> + <%= num2 %> = <%= addResult %>
<br />
<%= num1 %> - <%= num2 %> = <%= subtractResult %>
</body>
</html>