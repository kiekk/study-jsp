<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>로그인 페이지</title>
   </head>
   <body>
   	  <c:if test="${login eq 'false'}">
   	  	<script>
   	  		alert("로그인 실패");
   	  	</script>
   	  </c:if>
      <form name="loginForm" action="${pageContext.request.contextPath}/member/MemberLoginOk.me" method="post">
         <center>
            <table border="1" cellpadding="0" cellspacing="0" width="400px">
               <tr height="50px">
                  <td colspan="2" align=center>f
                     <b><font size=5>로그인 페이지</font></b>
                  </td>
               </tr>
               <tr height="40px">
                  <td align="center" width="100px">아이디</td>
                  <td align="center"><input type="text" name="member_id" style="width:280px;" /></td>
               </tr>
               <tr height="40px">
                  <td align="center" width="100px">비밀번호</td>
                  <td align="center"><input type="password" name="member_pw"  style="width:280px;" /></td>
               </tr>
               <tr height="30px">
                  <td colspan="2" align=center>
                     <a href="javascript:loginForm.submit()">로그인</a>&nbsp;&nbsp;
                     <a href="${pageContext.request.contextPath}/member/MemberJoin.me">회원가입</a>
                  </td>
               </tr>
            </table>
         </center>
      </form>
   </body>
</html>