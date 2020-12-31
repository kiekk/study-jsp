<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 예제</title>
</head>
<body>
	<form action="object_ok.jsp" name="join" method="post">
		<fieldset>
			<legend>개인 정보</legend>
			<input type="text" name="name">
			<input type="date" name="birthday">
			<input type="button" value="확인" onclick="sendit()">
		</fieldset>
	</form>
</body>
<script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	function sendit(){
		var frm = document.join;				//form 태그
		var name = $("input[name='name']");		//name 태그
		var date = $("input[name='birthday']");	//birthday 태그
		
		//이름을 입력하지 않았을 때
		if(name.val() == ""){
		//이름 input태그 테두리를 빨간색으로 변경
		//input태그 안에 '성함을 입력하세요' 문구도 추가
			name.css('border','2px solid red').attr('placeholder','성함을 입력하세요');
		}
		//생년월일을 입력하지 않았을 때
		//생년월일 input태그 테두리를 빨간색으로 변경
		if(date.val() == ""){
			date.css('border','2px solid red');
		}
		
		//데이터 sumbit(전송)
		frm.submit();
		
		//테두리가 생긴 영역을 클릭하면 원래대로 원상복구시키기
		name.on('click', function(){
			$(this).removeAttr('style');
			$(this).removeAttr('placeholder');
		})
		date.on('click',function(){
			$(this).removeAttr('style');
		})
	}
</script>
<!--
<script>
	function sendit(){
		//테두리가 생긴 영역을 클릭하면 원래대로 원상복구시키기
		$("input[name='name']").click(function(){
			$("input[name='name']").removeAttr('style');
			$("input[name='name']").removeAttr('placeholder');
		});
		$($("input[name='birthday']")).click(function(){
			$("input[name='birthday']").removeAttr('style');
		});
		//강사님 방법
		var frm = document.join;
		
		//return false를 작성하면 바로 함수가 종료되기 때문에
		//위에서부터 순차적으로 검사하도록 구현할 때 return false를 사용합니다.(카카오톡 방식)
		
		//만약 모든 것을 한번에 체크하고 싶다면 return false를 사용하지 않습니다.(네이버 방식)
		if(frm.name.value == ""){
			$("input[name='name']").css('border-color','red');
			$("input[name='name']").attr('placeholder','성함을 입력하세요');
			return false;
		}
		//데이터 sumbit(전송)
		frm.submit();
		if(frm.birthday.value == ""){
			$("input[name='birthday']").css('border-color','red');
			return false;
		}
	}
</script>
 -->
</html>