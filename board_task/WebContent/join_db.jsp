<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean class="com.board.vo.MemberVO" id="m_vo"/>
<jsp:useBean class="com.board.dao.MemberDAO" id="m_dao"/>
<!-- 각각의 값에 맞게 모델 객체를 넣어줍니다 -->
<!-- 외부에서 전달받은 name과 일치하는 name이 있다면 자동으로 값을 넣어줍니다. -->
<jsp:setProperty property="*" name="m_vo"/>
<%
	//DB처리
	//join()메소드에 입력된 회원 정보를 전달합니다.
	if(m_dao.join(m_vo)){
		//회원가입이 성공하면 회원 id를 cookie에 저장하여 전달합니다.
		Cookie cookie = new Cookie("name",m_vo.getName());
		response.addCookie(cookie);
%>
	<script>
		alert("회원가입 성공\n로그인 해주세요.");
		location.href = "login.html";
	</script>
<% 
	}else {
%>
	<script>
		alert("회원가입 실패");
		location.href = "join.html";
	</script>
<%
	}
%>
