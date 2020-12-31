<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>MVC 게시판</title>
   </head>
   
 
   <body>
      <center>
         <table border="0" cellpadding="0" cellspacing="0" width="900px" >
            <tr align="right" valign="middle">
               <td>
                  ${session_id} 님 환영합니다.
                  <a href="${pageContext.request.contextPath}/member/MemberLogOut.me">로그아웃</a>
                  <%-- <a href="${pageContext.request.contextPath}/member/MemberList.me">회원 리스트보기</a> --%>
               </td>
            </tr>
         </table>
         <br />
         <br />
         
         <!-- 게시판 등록 -->
         <form action="${pageContext.request.contextPath}/board/BoardWriteOk.bo?cat=${category_no}" method="post" enctype="multipart/form-data" name="boardform">
            <table width="900px" border="0" cellpadding="0" cellspacing="0">
               <tr align="center" valign="middle">
                  <td><h3>MVC 게시판</h3></td>
               </tr>
            </table>
         
            <table  border="1" cellpadding="0" cellspacing="0" width="900px" >
               <tr height="30px">
                  <td align="center" width="150px">
                     <div align="center">제 목</div>
                  </td>
                  <td style="padding-left:10px;">
                     <input name="board_title" type="text" size="50" maxlength="100" value=""/>
                  </td>
               </tr>
               <tr height="30px">
                  <td align="center" width="150px">
                     <div align="center">글쓴이</div>
                  </td>
                  <td style="padding-left:10px;">
                     <input name="board_id" type="text" size="10" maxlength="10" value="${session_id}" readonly />
                  </td>
               </tr>
               <tr height="200px">
                  <td align="center" width="150px">
                     <div align="center">내 용</div>
                  </td>
                  <td style="padding-left:10px;">
                     <textarea name="board_contents" style="width:700px; height:185px"></textarea>
                  </td>
               </tr>
               <tr height="30px">
                  <td align="center" width="150px">
                     <div align="center">파일 첨부</div>
                  </td>
                  <td style="padding-left:10px;">
                     <input name="board_file1" type="file"/>
                     <input type="button" value="첨부 삭제" onclick="deleteFile(this)"/>
                  </td>
               </tr>
               <tr height="30px">
                  <td align="center" width="150px">
                     <div align="center">파일 첨부</div>
                  </td>
                  <td style="padding-left:10px;">
                   	 <input name="board_file2" type="file"/>
                     <input type="button" value="첨부 삭제" onclick="deleteFile(this)"/>
                  </td>
               </tr>
               <tr height="30px">
                  <td align="center" width="150px">
                     <div align="center">파일 첨부</div>
                  </td>
                  <td style="padding-left:10px;">
                     <input name="board_file3" type="file"/>
                     <input type="button" value="첨부 삭제" onclick="deleteFile(this)"/>
                  </td>
               </tr>
            </table>
            
            <table border="0" cellpadding="0" cellspacing="0" width="900px">
               <tr align="right" valign="middle">
                  <td>
                     <a href="javascript:addboard()">[등록]</a>&nbsp;&nbsp;
                     <a href="${pageContext.request.contextPath}/board/BoardList.bo">[목록]</a>&nbsp;&nbsp;
                  </td>
               </tr>
            </table>
         </form>
         <!-- 게시판 등록 -->
      </center>
   </body>
   <script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
   <script>
      function addboard(){
         boardform.submit();
      }
      
      function deleteFile(button){
    	  //$.browser.msie는 안 되는 이유
    	  //JQuery 1.8 버전 이후 $.browser 가 삭제되었습니다.
    	  
    	  //현재 웹 페이지가 열린 브라우저의 이름을 반환합니다.
    	  var agent = navigator.userAgent.toLowerCase();
    	  //IE에서는 val("")으로는 file의 값을 초기화 할 수 없기 때문에, 똑같은 태그를 복사하여 덮어쓰는 방식으로 작성해야 합니다.
    	  //따라서 IE버전을 따로 조건문으로 확인해 줘야 하는데,
    	  //appName으로도 Microsoft Internet Explorer값을 반환하기 때문에 이를 가지고 판단해도 되지만, msie만 두 가지의 조건을 모두 사용하는 이유
    	  //1.IE 11버전에서 userAgent에서 msie가 사라졌습니다. 따라서 IE11 버전부터는 위의 agent의 값으로 msie를 비교할 수 없습니다.
    	  //2.IE 11버전부터 navaigator.appName을 더 이상 Microsoft Internet Explorer가 아닌 Netscape로 바꿔서 반환합니다.
    	  //3.IE 11버전에서 userAgent를 확인하면 Trident값이 확인됩니다.
    	  //Trident는 IE에서 사용하고 있는 레이아웃의 이름, 다른 브라우저에서는 Trident를 사용하지 않기 때문에 IE를 구분할 수 있는 유일한 값
    	  //즉 1. (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1)
    	  //1번 조건으로 IE가 11버전인지 확인하고
    	  //2. (agent.indexOf("msie") != -1)
    	  //2번 조건으로 IE가 10버전이하인지 확인하여 모든 IE의 버전을 검사하여 현재 브라우저가 IE를 확인합니다.
    	  var $inputFileTag = $(button).prev(); //클릭한 버튼을 전달받아, 해당 버튼의 옆에 위치한 input태그를 가져옵니다.
    	  if((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1)) {
    		  $inputFileTag.replaceWith($inputFileTag.clone(true));
    	  }else {
    		  $inputFileTag.val("");  
    	  }
      }
   </script>
</html>