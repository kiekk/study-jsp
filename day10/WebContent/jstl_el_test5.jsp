<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL TEST(문자열 분할)</title>
</head>
<body>
	<h2>JSTL TEST(문자열 분할)</h2>
	<c:set var="cnt" value="0" />
	<c:forTokens var="ani" items="horse,tiger,lion" delims=",">
		동물${cnt = cnt + 1} : <c:out value="${ani}" />
		<%-- ${cnt += 1} : +=를 사용하면 문자열로 인식하여 연결합니다. --%>
		<br />
	</c:forTokens>
</body>
</html>