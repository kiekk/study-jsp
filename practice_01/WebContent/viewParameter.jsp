<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>request.getParameter() 메소드 사용</h2>
name 파라미터 = <%= request.getParameter("name") %><br />
address 파라미터 = <%= request.getParameter("address") %><br />
<p>
	<h2>request.getParameterValues() 메소드 사용</h2>
	<%
		String[] values = request.getParameterValues("pet");
		if(values != null){
			for(int i=0;i<values.length;i++){
	%>
		<%= values[i] %>
	<% 
			}
		}
	%>
<p>
	<h2>request.getParameterNames() 메소드 사용</h2>
	<%
		Enumeration paramEnum = request.getParameterNames();
		while(paramEnum.hasMoreElements()){
			String name = (String)paramEnum.nextElement();
	%>
		<%= name %>
	<%
		}
	%>
<p>
	<h2>request.getParameterMap() 메소드 사용</h2>
	<%
		Map parameterMap = request.getParameterMap();
		String[] nameParam = (String[])parameterMap.get("name");
		if(nameParam != null){
	%>
		name = <%= nameParam[0] %>
	<%		
		}
	%>
</body>
</html>