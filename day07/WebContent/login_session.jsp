<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean class="com.koreait.dao.MemberDAO" id="m_dao"/>
<jsp:useBean class="com.koreait.vo.MemberVO" id="m_vo"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 세션</title>
</head>
<body>
	<%
		String user_id = "";
		if(session.getAttribute("user_id") != null){
			user_id = (String)session.getAttribute("user_id");
		}
		if(!user_id.equals("")){
	%>
		<p><%=user_id%>님 환영합니다.</p>
		<p><a href="logout_session.jsp">[로그아웃]</a></p>
	<%
		}else {
	%>
		<form action="login_ok.jsp" name="loginForm" method="post">
			<p><label>아이디 : <input type="text" name="user_id"></label></p>
			<p><label>패스워드 : <input type="password" name="pw"></label></p>
			<p><input type="button" value="로그인" onclick="sendIt()"></p>
		</form>
	<%
		}
	%>
</body>
<script>
	function sendIt(){
		var frm = document.loginForm;
		if(frm.user_id.value == ""){
			alert("아이디를 입력해주세요.");
			frm.user_id.focus();
			return false;
		}
		
		if(frm.pw.value == ""){
			alert("패스워드를 입력해주세요.");
			frm.pw.focus();
			return false;
		}
		frm.submit();
	}
</script>
</html>