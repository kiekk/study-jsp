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
	//findid.html에서 이름과 이메일 받아오기
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String user_id = null;
	//findID()메소드로 이름, 이메일을 전달하여 일치한 경우 회원 ID를 반환 받습니다.
	if(m_dao.findId(name, email) != null){
		user_id = m_dao.findId(name, email);
		//반환 받은 회원 아이디를 request 객체에 저장하여 전달합니다.
		request.setAttribute("user_id", user_id);
		request.getRequestDispatcher("findId_success.jsp").forward(request, response);
	}else {
%>
	<script>
		alert("일치하는 ID가 없습니다.");
		location.href = "find_id.html";
	</script>
<%
	}
%>
</body>
</html>