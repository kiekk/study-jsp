<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form action="join_db.jsp" name="joinForm" method="post">
		<p>
			<label>아이디 : <input type="text" name="id"></label>
			<input type="button" value="중복확인" onclick="checkId(joinForm.id.value)">
		</p>
		<p id="checkId_text">
		</p>
		<p>
			<label>이름 : <input type="text" name="name"></label>
		</p>
		<p>
			<label>패스워드 : <input type="password" name="pw"></label>
		</p>
		<p>
			<label>패스워드 확인 : <input type="password" name="pw_re"></label>
		</p>
		<p>
			성별 : <label>남자 <input type="radio" name="gender" value="남자" checked></label>
				 <label>여자 <input type="radio" name="gender" value="여자"></label>
		</p>
		<p>
			우편번호 : <input type="text" name="zipcode" class="postcodify_postcode5" value="" readonly/>
					<input type="button" id="postcodify_search_button" value="검색"><br />
		</p>
		<p>
			주소 : <input type="text" name="address" class="postcodify_address" value="" readonly/><br />
		</p>
		<p>
			상세주소 : <input type="text" name="address_detail" class="postcodify_details" value="" /><br />
		</p>
		<p>
			참고항목 : <input type="text" name="address_etc" class="postcodify_extra_info" value="" readonly/><br />
		</p>
		<p>
			<label>휴대폰번호 : <input type="text" name="hp"></label>
		</p>
		<p>
			취미 : <label>게임  <input type="checkbox" name="hobby" value="게임"></label>
				 <label>드라이브  <input type="checkbox" name="hobby" value="드라이브"></label>
			 	 <label>등산 <input type="checkbox" name="hobby" value="등산"></label>
			 	 <label>영화감상  <input type="checkbox" name="hobby" value="영화감상"></label>
			 	 <label>운동 <input type="checkbox" name="hobby" value="운동"></label>
		</p>
		<p>
			<input type="button" value="가입완료" onclick="sendIt()">
		</p>
	</form>
</body>
<script src="//code.jquery.com/jquery-3.5.1.min.js?"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script src="member.js"></script>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
</html>