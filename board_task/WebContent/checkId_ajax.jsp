<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean class="com.board.dao.MemberDAO" id="m_dao"/>
<%
	String user_id = request.getParameter("id");
	//DB 처리
	if(!m_dao.checkId(user_id)){
		out.println("ok");		
	}else {
		out.println("not-ok");
	}
%>