<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트 태그 실습</title>
</head>
<body>
	<!--  
		□
		□□
		□□□
		□□□□
		□□□□□
		□□□□□
		□□□□
		□□□
		□□
		□
		위 모양과 똑같은 테이블 만들기
	-->	
	<table>
	<%!
		int i = 0;
	%>
	<% 
		for(;i<5;i++){
	%>
		<tr>
			<%
				for(int j=0;j<=i;j++){
			%>
			<td style="border:1px solid black; width:50px; height:50px;"></td>
			<%
				}
				/* for(j) */
			%>
		</tr>
	<% 
		}
		/* for(i) */
	%>
	<% 
		for(;i>=0;i--){
	%>
		<tr>
			<%
				for(int j=0;j<i;j++){
			%>
			<td style="border:1px solid black; width:50px; height:50px;"></td>
			<%
				}
				/* for(j) */
			%>
		</tr>
	<% 
		}
		/* for(i) */
	%>
	</table>
</body>
</html>