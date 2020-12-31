<%@page import="com.board.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean class="com.board.dao.MemberDAO" id="m_dao"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
	//findpw.html에서 아이디, 이름을 전달받습니다.
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String user_pw = null;
	//findPw()메소드로 아이디, 이름을 전달하여 일치하는 회원의 Pw를 반환받습니다.
	if(m_dao.findPw(id, name) != null){
		user_pw = m_dao.findPw(id, name);
		//회원의 pw를 request객체에 저장하여 전달합니다.
		request.setAttribute("user_pw", user_pw);
		request.getRequestDispatcher("findPw_success.jsp").forward(request, response);
	}else {
%>
	<script>
		alert("일치하는 정보가 없습니다.");
		location.href = "find_pw.html";
	</script>
<%
	}
%>
</body>
</html>