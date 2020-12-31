<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>MVC 게시판</title>
      <script>
      /*
      	persisted를 통해서는 파이어폭스,사파리에서는 가능하지만 크롬,IE에서는 불가능합니다.
      	performance를 통해서 크롬,IE까지 뒤로가기의 유무를 확인할 수 있습니다.
      	navigation.type == 2가 뒤로가기 버튼을 이용해서 뒤로가기를 한 경우 입니다.
      */
      	window.onpageshow = function(event) {
    	    if (event.persisted || (window.performance && window.performance.navigation.type == 2)) {
    			window.location.reload();
    	    }
    	}
	  </script>
   </head>
   <body>
      <c:if test="${session_id eq null}">
         <script>
            alert("로그인 후 이용해주세요");
            location.replace("${pageContext.request.contextPath}/member/MemberLogin.me");
         </script>
      </c:if>
      <c:set var="list" value="${requestScope.boardList}"/>
      <c:set var="nowPage" value="${requestScope.nowPage}"/>
      <c:set var="startPage" value="${requestScope.startPage}"/>
      <c:set var="endPage" value="${requestScope.endPage}"/>
      <c:set var="totalCnt" value="${requestScope.totalCnt}"/>
      <c:set var="totalPage" value="${requestScope.totalPage}"/>
      <center>
         <table border="0" cellpadding="0" cellspacing="0" width="900px" >
            <tr align="right" valign="middle">
               <td>
                  ${session_id} 님 환영합니다.
                  <a href="${pageContext.request.contextPath}/member/MemberLogOut.me">로그아웃</a>
               </td>
            </tr>
         </table>
         <br />
         <br />
                 <div id="map" style="width:500px;height:400px;"></div>
         <!-- 게시판 리스트 -->
         <table width="900px" border="0" cellpadding="0" cellspacing="0">
            <tr align="center" valign="middle">
               <td><h3>MVC 게시판</h3></td>
            </tr>
            <tr align="right" valign="middle">
               <td>글 개수 : ${totalCount} 개</td>
            </tr>
         </table>
         
         <table border="1" cellpadding="0" cellspacing="0" width="900px" >
            <tr align="center" valign="middle">
               <td width="8%" height="26">
                  <div align="center">번호</div>
               </td>
               <td width="50%">
                  <div align="center">제목</div>
               </td>
               <td width="14%">
                  <div align="center">작성자</div>
               </td>
               <td width="17%">
                  <div align="center">날짜</div>
               </td>
               <td width="11%">
                  <div align="center">조회수</div>
               </td>
            </tr>
            <c:choose>
            <c:when test="${list != null and fn:length(list) > 0}">
               <c:forEach var="b_bean" items="${list}">
                  <tr align="center" valign="middle" onmouseover="this.style.backgroundColor='F8F8F8'" onmouseout="this.style.backgroundColor=''">
                     <td height="23" style="font-family:Tahoma;font-size:10pt;">
                        ${b_bean.getBoard_num()}
                     </td>
                     
                     <td style="font-family:Tahoma;font-size:10pt;">
                        <div align="left">
                        <a href="${pageContext.request.contextPath}/board/BoardView.bo?seq=${b_bean.getBoard_num()}">${b_bean.getBoard_title()}</a>
                        </div>
                     </td>
                     <td>
                        <div align="center">${b_bean.getBoard_id()}</div>
                     </td>
                     <td>
                        <div align="center">${b_bean.getBoard_date()}</div>
                     </td>   
                     <td>
                        <div align="center">${b_bean.getReadCount()}</div>
                     </td>
                  </tr>
               </c:forEach>
            </c:when>
            <c:otherwise>
            <tr height="50px">
               <td colspan="5" align="center">등록된 게시물이 없습니다.</td>
            </tr>
            </c:otherwise>
         </c:choose>
         </table>
         
         <br/>
         <table border="0" cellpadding="0" cellspacing="0" width="900px">
            <tr align="center" valign="middle">
               <td>
               <c:choose>
                  <c:when test="${nowPage > 1}">
                     <a href="${pageContext.request.contextPath}/board/BoardList.bo?page=${nowPage - 1}">[이전]</a>&nbsp;
                  </c:when>
               </c:choose>
               <c:forEach var="i" begin="${startPage}" end="${endPage}">
                  <c:choose>
                     <c:when test="${i eq nowPage}">
                        [${i}]&nbsp;
                     </c:when>
                     <c:otherwise>
                        <a href="${pageContext.request.contextPath}/board/BoardList.bo?page=${i}">${i}&nbsp;</a>
                     </c:otherwise>
                  </c:choose>
               </c:forEach>
               <c:choose>
                  <c:when test="${nowPage != totalPage}">
                     <a href="${pageContext.request.contextPath}/board/BoardList.bo?page=${nowPage + 1}">[다음]</a>&nbsp;
                  </c:when>
               </c:choose>
               </td>
            </tr>
         </table>
         
         <table border="0" cellpadding="0" cellspacing="0" width="900px">
            <tr align="right" valign="middle">
               <td><a href="${pageContext.request.contextPath}/board/BoardWrite.bo">[글쓰기]</a></td>
            </tr>
         </table>
      </center>
   </body>
       <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1a354b9eb9cb42ab99114924570a7b8d&libraries=services"></script>
    <script>
      var container = document.getElementById('map');
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
      };
  
      var map = new kakao.maps.Map(container, options);
   // 마커가 표시될 위치입니다 
      var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
          position: markerPosition,
          clickable: true
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);

      var iwContent = '<div style="padding:5px;">000공방<br><a href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">공방페이지</a> <a href="https://map.kakao.com/link/from/강동역 5호선,,33.450701,126.570667/to/강남역 2호선,33.450701,126.570667" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
          iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
		  iwRemoveable = true;
      // 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
          position : iwPosition, 
          content : iwContent,
          remoable : iwRemoveable
      });
        
      // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
      //infowindow.open(map, marker); 
   // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(marker, 'click', function() {
            // 마커 위에 인포윈도우를 표시합니다
            infowindow.open(map, marker);  
      });
    </script>
</html>
