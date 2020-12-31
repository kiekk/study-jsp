<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산기 만들기 실습</title>
</head>
<body>
	<h1>계산기</h1>
	<form action="Result" method="get">
		<input type="text" name="calc" placeholder="계산할 수식을 입력하세요." autofocus/>
		<button type="submit">결과 보기</button>
	</form>
</body>
</html>