<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GET과 POST 방식의 요청</title>
</head>
<body>
	<h1>GET방식의 요청</h1>
	<%
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		
		if(city == "" || zipcode == null){
			//해당 페이지로 이동할 때 자기가 가지고 있던 값은 전부 초기화하고 이동합니다.
			response.sendRedirect("response.jsp");
		}else {
	%>
	<!-- method속성을 사용하지 않을 경우 default는 get방식입니다. -->
	<form action="request_response.jsp" method="get">
		<input type="text" value="Seoul" name="city" readonly>
		<input type="text" value="06141" name="zipcode" readonly>
		<button type="submit">GET 방식으로 요청보내기</button>
	</form>
	<p><%= city %><%= zipcode %></p>
	<%
		}
	%>
</body>
</html>