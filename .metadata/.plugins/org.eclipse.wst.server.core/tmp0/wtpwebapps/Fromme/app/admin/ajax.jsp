<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.fromme.app.admin.vo.ChartVO"%>
<%@page import="java.util.List"%>
<%@page import="com.fromme.app.admin.dao.AdminDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

	String classify = request.getParameter("classify");
	System.out.print(classify);
	if(classify == null) {
		out.print("asdasd");
	} else {
		AdminDAO a_dao = new AdminDAO();
		Map<String, Integer> arMap = a_dao.views(classify);
 		String json = a_dao.getJsonArr(arMap);
		System.out.print(json);
		out.print(json); 
	}
	
	
	
	
	
	
%>