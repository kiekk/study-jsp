<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax 예제</title>
</head>
<body>
	<!-- 
	<h1>GET 방식의 요청</h1>
	<button type="button" onclick="sendRequest()">GET 방식으로 요청 보내기</button>
	 -->
	<h1>POST 방식의 요청</h1>
	<button type="button" onclick="sendRequest()">GET 방식으로 요청 보내기</button>
	<p id="text"></p>
</body>
<script>
	function sendRequest(){
		//서버와 통신을 하기 위해 객체를 생성합니다.
		var httpRequest = new XMLHttpRequest();
		/*
		//GET 방식
		//데이터를 전송하기 전 설정값을 open메소드에 담아줍니다.
		httpRequest.open("GET","request_ajax.jsp?city=Seoul&zipcode=06141",true);
		//위의 설정대로 전송합니다.
		httpRequest.send();
		*/
		//POST 방식
		httpRequest.open("POST","request_ajax.jsp",true);
		//전송할 데이터의 전달 방법(타입)을 작성합니다.
		httpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//전송할 데이터의 전달 방법에 맞추어 작성하여 데이터를 전달합니다.
		httpRequest.send("city=Seoul&zipcode=06141");
		//서버와 통신 준비가 되었다면, 
		httpRequest.onreadystatechange = function(){
			//현재 준비 완료 상태이며, 통신 결과가 성공적인지 판단
			if(httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200){
				//통신된 결과값을 현재 페이지의 p태그 안에 넣어줍니다.(DOM)
				document.getElementById("text").innerHTML = httpRequest.responseText;
			}
		};
	}
</script>
</html>