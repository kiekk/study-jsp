<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL TEST(출력의 종류)</title>
</head>
<body>
	<!-- EL문만 사용할 경우 HTML 또는 script가 실행될 수 있습니다. -->
	${"<script>alert('안녕')</script>"}
	<!-- JSTL Core의 out을 작성하면 실행이 아닌 문자열 값으로 출력할 수 있습니다. -->
	<!-- escapeXml 속성의 default 값은 true입니다. -->
	<c:out value="<script>alert('안녕')</script>" />
</body>
</html>