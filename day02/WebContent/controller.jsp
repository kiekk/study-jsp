<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%
		String site = request.getParameter("site");
		String viewPageURI = null;
		
		if(site.equals("naver")){
			viewPageURI = "forward_naver.jsp";
		}else if(site.equals("daum")){
			viewPageURI = "forward_daum.jsp";
		}else if(site.equals("google")){			
			viewPageURI = "forward_google.jsp";
		}
	%>
	<jsp:forward page="<%=viewPageURI%>"/>
</body>
</html>