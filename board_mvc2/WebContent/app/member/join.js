/**
 * 회원가입 
 */
function formSubmit(){
	var form = document.joinForm;
	var textfield = document.getElementById("checkId_text");
	var check = document.getElementById("isOk").value;
	if(form.member_id.value == ''){
		alert("아이디를 확인해주세요.");
		form.member_id.focus();
		return false;
	}
	//alert(check);
	if(check == 'false'){
		alert("중복확인을 체크해주세요.");
		form.member_id.focus();
		return false;
	}
	form.submit();
}
//input태그 값 변경 감지
function changeValue(){
	document.getElementById("checkId_text").textContent = "";
	document.getElementById("isOk").value = "false";
}
//아이디 중복 검사
function checkId(member_id){
	var inputTag = document.joinForm.member_id;
	var textfield = document.getElementById("checkId_text");
	var resultmsg = "";
	
	if(member_id == ""){
		alert("아이디를 입력해주세요.");
		return false;
	}
	//Ajax
	//결과를 DOM을 통해서 태그에 메세지 추가
	var request = new XMLHttpRequest();
	request.open("POST",contextPath + "/member/MemberCheckId.me",true);
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	request.send("member_id=" + member_id);
	request.onreadystatechange = function(){
		if(request.readyState == 4 && request.status == 200){
			if(request.responseText.trim() == "ok"){
				//console.log(request.responseText);
				resultmsg = "사용할 수 있는 아이디 입니다.";
				document.getElementById("isOk").value = "true";
			}else {
				//console.log(request.responseText);
				resultmsg = "사용할 수 없는 아이디 입니다.";
			}
			textfield.textContent = resultmsg;
		}
	}
}
/*
function checkId(id){
	if(id == ""){
		$("#checkId_text").text("아이디를 입력해주세요.");
	}else {
		$.ajax({
			url: contextPath + "/member/MemberCheckId.me?member_id="+id,
			type: 'get',
			dataType: 'text',
			success: function(data){ //ajax의 결과값이 data에 저장
				if(data == "ok"){
					$("#checkId_text").text("사용할 수 있는 아이디입니다.");
				}else {
					$("#checkId_text").text("중복된 아이디입니다.");
				}
			},
			error: function(){
				console.log("오류");
			}
		});
	}
}

$("input[name='member_id']").keyup(function(event){
	var id = $("input[name='member_id']").val();
	checkId(id);
});
*/