/**
 * 회원가입 세부설정
 */

function sendIt(){
	//alert("호출");
	
	var frm = document.joinForm;
	
	if(frm.id.value == ""){
		alert("아이디를 입력해주세요.");
		frm.id.focus();
		return false;
	}
	if(frm.id.value.length < 4 || frm.id.value.length > 16){
		alert("아이디는 4자 이상, 16자 이하로 입력해주세요.");
		frm.id.focus();
		return false;
	}
	if(frm.name.value == ""){
		alert("이름을 입력해주세요.");
		frm.name.focus();
		return false;
	}
	if(frm.pw.value == ""){
		alert("비밀번호를 입력해주세요.");
		frm.password.focus();
		return false;
	}else {
		//8자리 이상, 대문자/소문자 0~9, 특수문자 모두 포함되어 있는지 검사
		var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
		var hangleCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		
		if(!reg.test(frm.pw.value)){
			alert("비밀번호는 8자 이상이어야하며, 대문자/소문자/숫자/특수무자 모두 포함해야 합니다.");
			return false;
		//같은 문자를 4번 이상 사용할 수 없습니다.
		}else if(/(\w)\1\1\1/.test(frm.pw.value)){
			alert("같은 문자를 4번 이상 사용하실 수 없습니다.");
			return false;
		//비밀번호에 아이디가 포함되어 있을 수 없습니다.
		}else if(frm.pw.value.search(frm.id.value) > -1){
			alert("비밀번호에 아이디를 포함할 수 없습니다.");
			return false;
		//비밀번호에 공백을 포함할 수 없습니다.
		}else if(frm.pw.value.search(/\s/) != -1){
			alert("비밀번호는 공백없이 입력해주세요.");
			return false;
		//비밀번호에 한글을 포함할 수 없습니다.
		}else if(hangleCheck.test(frm.pw.value)){
			alert("비밀번호에 한글을 사용할 수 없습니다.");
			return false;
		}
	}
	if(frm.name.value == ""){
		alert("이름을 입력해주세요.");
		frm.name.focus();
		return false;
	}
	if(frm.email.value == ""){
		frm.email.focus();
		return false;
	}
	frm.submit();
}

function checkId(id){
	//결과값을 출력할 p태그 가져오기
	var textfield = document.getElementById("checkId_text");
	var resultmsg = "";
	//alert(id);
	if(id == ""){
		alert("아이디를 입력해주세요.");
		return false;
	}
	//Ajax
	//결과를 DOM을 통해서 태그에 메세지 추가
	var request = new XMLHttpRequest();
	request.open("POST","checkId_ajax.jsp",true);
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	request.send("id=" + id);
	request.onreadystatechange = function(){
		if(request.readyState == 4 && request.status == 200){
			if(request.responseText.trim() == "ok"){
				resultmsg = "사용할 수 있는 아이디 입니다.";
			}else {
				resultmsg = "사용할 수 없는 아이디 입니다.";
			}
			textfield.textContent = resultmsg;
		}
	}
}
