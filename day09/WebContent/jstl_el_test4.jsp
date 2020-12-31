<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL TEST(제어문 - 반복문)</title>
</head>
<body>
	<h2>JSTL TEST(제어문 - 반복문)</h2>
	forEach 연습 결과
	<c:forEach var="i" begin="1" end="10" step="1">
		<c:out value="${i}" /> &nbsp;&nbsp;
	</c:forEach>
	vatStatus 연습 결과<br />
	<c:forEach var="i" begin="1" end="10" step="1" varStatus="stat">
		index : <c:out value="${stat.index}" /><br />
		count : <c:out value="${stat.count}" /><br />
		begin : <c:out value="${stat.begin}" /><br />
		end : <c:out value="${stat.end}" /><br />
		first : <c:out value="${stat.first}" /><br />				
		last: <c:out value="${stat.last}" /><br /><br />	
	</c:forEach>
</body>
</html>