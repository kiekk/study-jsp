<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>MVC 게시판</title>
   </head>
   
   <body>
   <c:set var="boardBean" value="${requestScope.boardBean}"/>
   <c:set var="filesBeanList" value="${requestScope.filesBeanList}"/>
   <c:set var="replyBeanList" value="${requestScope.replyBeanList}"/>
      <center>
      <c:choose>
         <c:when test="${session_id eq null}">
         <script>
            alert("로그인 후 이용해주세요");
            location.replace("${pageContext.request.contextPath}/member/MemberLogin.me");
         </script>
         </c:when>
         <c:otherwise>
         <table border="0" cellpadding="0" cellspacing="0" width="900px" >
            <tr align="right" valign="middle">
               <td>
                  ${session_id} 님 환영합니다.
                  <a href="${pageContext.request.contextPath}/member/MemberLogOut.me">로그아웃</a>
               </td>
            </tr>
         </table>
         </c:otherwise>
         </c:choose>
         <br />
         <br />
      
         <table width="900px" border="0" cellpadding="0" cellspacing="0">
            <tr align="center" valign="middle">
               <td><h3>MVC 게시판</h3></td>
            </tr>
         </table>
            
         <!-- 게시판 수정 -->
         <table width="900px" border="1" cellpadding="0" cellspacing="0">
            <tr height="30px">
               <td align="center" width="150px">제 목</td>
               <td style="padding-left:10px;">${boardBean.getBoard_title()}</td>
            </tr>
            
            <tr height="30px">
               <td  align="center" width="150px">아이디</td>
               <td style="padding-left:10px;">${boardBean.getBoard_id()}</td>
            </tr>
            
            <tr height="200px">
               <td align="center" width="150px">내 용</td>
               <td valign="top" style="padding-top:10px; padding-left:10px;">${boardBean.getBoard_contents()}</td>
            </tr>
            
            <tr height="30px">
               <td align="center">첨부파일</td>
               <c:if test="${filesBeanList != null}">
                  <td>
                     <c:forEach var="file" items="${filesBeanList}">
                        <a href="${pageContext.request.contextPath}/board/FileDownload.bo?file_name=${file.getFile_name()}">${file.getFile_name()}</a>
                     </c:forEach>
                  </td>
               </c:if>
            </tr>
         </table>
         
         <table width="900px" border="0" cellpadding="0" cellspacing="0">
            <tr align="right" valign="middle">
               <td>
                  <c:if test="${boardBean.getBoard_id() eq session_id}">
                     <a href="${pageContext.request.contextPath}/board/BoardModify.bo?seq=${boardBean.getBoard_num()}">[수정]</a>&nbsp;&nbsp;
                     <a href="javascript:deleteBoard()">[삭제]</a>&nbsp;&nbsp;
                  </c:if>
                  <a href="${pageContext.request.contextPath}/board/BoardList.bo">[목록]</a>&nbsp;&nbsp;
               </td>
            </tr>
         </table>
         <form action="${pageContext.request.contextPath}/board/BoardDeleteOk.bo" method="post" name="boardform">
            <input type="hidden" name="seq" value="${boardBean.getBoard_num()}" />
          </form>
          
          <!-- 댓글 -->
          <form action="${pageContext.request.contextPath}/board/BoardReplyOk.bo?seq=${boardBean.getBoard_num()}" method="POST" name="boardReply">
          	 <table>
          	 	<tr>
          	 		<td align="center" width="80px">
          	 			<div align="center">댓글</div>
          	 		</td>
          	 		<td>
          	 			<textarea name="reply_contents" style="width:750px; height:85px; resize:none;"></textarea>
          	 			<a href="javascript:comment()">[등록]</a>
          	 		</td>          	 		
          	 	</tr>
          	 	<c:set var="i" value="${0}"/>
          	 	<c:choose>
          	 		<%-- 댓글이 한 개라도 있을 때--%>
          	 		<c:when test="${!empty replyBeanList}">
          	 		<%-- <c:when test="${replyBeanList != null and fn:length(replyBeanList) > -}">--%>
          	 			<c:forEach var="reply" items="${replyBeanList}">
          	 				<c:set var="i" value="${i+1}"/>
          	 				<tr>
          	 					<td align="center" width="150px;">${reply.getMember_id()}</td>
          	 					<td valign="top" style="padding-left:10px;">
									<textarea id="${i}" name="board_contents${i}" class="replys" style="width:750px; resize:none;" readonly>${reply.getReply_contents()}</textarea>
									<%-- 작성자만 수정과 삭제 가능 --%>
									<c:if test="${reply.getMember_id() eq session_id}">
										<%-- 수정 버튼과 삭제버튼 구현 --%>
										<a id="ready${i}" href="javascript:updateReply(${i})">[수정]</a>
										<a id="ok${i}" href="javascript:submitReplyUpdateForm(${reply.getReply_num()},${reply.getBoard_num()},${i})" style="display:none;">[수정 완료]</a>
										<a href="${pageContext.request.contextPath}/board/BoardReplyDeleteOk.bo?reply_num=${reply.getReply_num()}&seq=${boardBean.getBoard_num()}">[삭제]</a>
									</c:if>
								</td>
          	 					<td></td>
          	 				</tr>
          	 			</c:forEach>
          	 		</c:when>
          	 		<c:otherwise>
          	 			<tr align="center">
          	 				<td align="center" width="150px" colspan="2">댓글이 없습니다.</td>
          	 			</tr>
          	 		</c:otherwise>
          	 	</c:choose>
          	 </table>
          </form>
      </center>
   </body>
  	 <script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
  	 <script src="https://rawgit.com/jackmoore/autosize/master/dist/autosize.min.js"></script>
     <script>
       var check = false;
       autosize($("textarea.replys"));
       function deleteBoard(){
			boardform.submit();
       }
       function comment(){
			var textarea = $("textarea[name='reply_contents']");
			if(textarea.val() == ""){
				alert("댓글을 작성해주세요.");
				return false;
			}
			boardReply.submit();
       }
       function updateReply(num){
    	   var textarea = $("textarea#" + num);
    	   var modify_ready_a = $("a#ready"+num);
    	   var modify_ok_a = $("a#ok"+num);
    	   
    	   if(!check){
    		   textarea.removeAttr('readonly');
    		   modify_ready_a.css('display','none');
    		   modify_ok_a.css('display','inline');
    		   check = true;
    	   }else {
    		   alert("수정중인 댓글이 있습니다.");
    	   }
       }
       
       function submitReplyUpdateForm(reply_num,seq,num){
    	   $("form[name='boardReply']").attr("action","${pageContext.request.contextPath}/board/BoardReplyModifyOk.bo?reply_num="+reply_num+"&seq="+seq+"&num="+num);
    	   boardReply.submit();
       }
     </script>
</html>