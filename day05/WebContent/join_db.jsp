<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean class="com.koreait.vo.MemberVO" id="m_vo"/>
<!-- 각각의 값에 맞게 모델 객체를 넣어줍니다 -->
<!-- 외부에서 전달받은 name과 일치하는 name이 있다면 자동으로 값을 넣어줍니다. -->
<jsp:setProperty property="*" name="m_vo"/>
<%
	System.out.println(m_vo);
	//DB처리
	
	Cookie cookie = new Cookie("name",m_vo.getName());
	response.addCookie(cookie);
	
	response.sendRedirect("join_ok.jsp");
%>
