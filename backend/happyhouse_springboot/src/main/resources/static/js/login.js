function login() {
	event.preventDefault();
	let user_id = $("#user_id").val();
	let user_password = $("#user_password").val()
	if(user_id.length==0){
		alert("ID를 입력하세요.")
		return false
	} else if(user_password.length == 0){
		alert("비밀번호를 입력하세요.")
		return false
	} else{
		var form = document.getElementById("login_form");
		form.action = getContextPath() + "/login";
		form.method = "POST";
		form.submit();
	}
}