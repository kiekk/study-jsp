<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include 디렉티브 태그 예제</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h4>------------현재 페이지 영역--------------</h4>
	<%!
		//한 번 메모리에 할당된 변수는 다시 초기화 되지 않습니다
		int pageCnt = 0;
		void addCnt(){
			pageCnt++;
		}
	%>
	<%
		addCnt();
	%>
	<p>이 사이트의 방문은 <%=pageCnt%>번째 입니다.</p>
	<jsp:include page="footer.jsp" />
	
</body>
</html>