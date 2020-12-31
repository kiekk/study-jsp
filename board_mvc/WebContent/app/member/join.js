/**
 * 회원가입
 */

function formSubmit(){
	var form = document.joinForm;
	if(form.member_id.value == ''){
		alert("아이디를 확인해주세요.");
		form.member_id.focus();
		return false;
	}
	form.submit();
}