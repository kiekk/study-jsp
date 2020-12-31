<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 실습(제어문 - 반복문)</title>
</head>
<body>
	<!--  
		1단부터 100단까지 중 사용자에게 입력받은 단수로 구구단 출력하기
		입력받은 값이 정수인지 아닌지 판단, 값을 입력했는지 안했는지 판단
		다른 페이지 이동 없이 현재 페이지에서만 기능 구현
		자바스크립트 사용 가능
	-->
	
	<!-- 값이 입력되지 않은 경우 -->
	<form action="#" name="gugudanForm" onsubmit="return false;">
		<fieldset style="display:inline-block">
			<legend>구구단</legend>
			<input type="text" name="gugudan" placeholder="1~100 중에서 입력하세요" id="gugudan" autofocus>
			<input type="button" value="확인" id="button">
		</fieldset>
	</form>
	
	<!-- 값이 정상적으로 입력된 경우(script에서 먼저 검사) -->
	<c:set var="dan" value="${param.gugudan}" scope="page" />
	<c:if test="${!empty dan}">
		<table border="1">
			<tr style="text-align:center"><td><c:out value="${dan} 단"/></td></tr>
			<c:forEach var="i" begin="1" end="9">
				<tr><td><c:out value="${dan} * ${i} = ${dan * i}"/></td></tr>			
			</c:forEach>
		</table>	
	</c:if>

</body>
</html>
<script>
	//입력된 값 검사
	function checkInputValue(){
		var button = document.getElementById('button');		//button 태그
		var form = document.gugudanForm;					//form 태그
		var gugudan = form.gugudan;	//input 태그
		
		//실수값 확인
		var check = parseFloat(gugudan.value) - parseInt(gugudan.value) == 0.0;
		console.log(gugudan.value);
		if(gugudan.value == ""){
			alert("값을 입력해주세요.");
		}else if(isNaN(gugudan.value)){
			alert("정수를 입력해주세요.");
		}else if(!check){
			alert("실수는 입력할 수 없습니다.");
		}else if(gugudan.value < 1 || gugudan.value > 100){
			alert("1~100 중에서 입력해주세요.");
		}else {
			gugudan.value = parseInt(gugudan.value);
			form.submit();
		}
		gugudan.value = '';
		gugudan.focus();
	}
	
	button.addEventListener('click', checkInputValue);	//클릭 이벤트 연결
	/*
	form.addEventListener('submit', (e)=>{				//엔터 이벤트 연결
	    e.preventDefault();
		checkInputValue();
	});
	*/
	document.addEventListener('keydown', function(e) {
		  if (e.keyCode === 13) {
		    e.preventDefault();
		    checkInputValue();
		  }
	});
</script>