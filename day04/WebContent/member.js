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
		frm.pw.focus();
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
	if(frm.pw.value != frm.pw_re.value){
		alert("패스워드를 확인해주세요.");
		frm.pw_re.focus();
		return false;
	}
	if(frm.zipcode.value == ""){
		alert("우편번호를 입력해주세요.");
		frm.zipcode.focus();
		return false;
	}
	if(frm.address_detail.value == ""){
		frm.address_detail.focus();
		return false;
	}
	if(frm.hp.value == ""){
		alert("휴대폰 번호를 입력해주세요.");
		frm.hp.focus();
		return false;
	}
	for(var i=0;i<frm.hobby.length;i++){
		if(frm.hobby[i].checked){
			//만약 1개라도 체크되어 있다면 들어옵니다.
			break;
		}
	}
	//위의 for문이 break를 만나서(체크됨) 탈출했다면,
	//i는 frm.hobby.length까지 증가하지 못하고 탈출하게 됩니다.
	//만약 정상 탈출이라면 i == frm.hobby.length이고 하나도 체크가 되어있지 않았다는 의미입니다.
	if(i == frm.hobby.length){
		alert("취미는 1개 이상 입력해주세요.");
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
