$(function() {
	let temp = JSON.parse(localStorage.getItem("temp_user"))
	$("#user_id").attr('value', temp['id'])
	$("#user_password").attr('value', temp['pw']);
	$("#user_name").attr('value', temp["name"])
	$("#user_email").attr('value', temp["email"])
	$("#user_tel").attr('value', temp["tel"])
})

function back() {
	 window.history.back()
}

function register(event) {
	var form = document.getElementById("register_form");
	form.action = getContextPath() + "/user";
	form.method = "POST";
	form.submit();
}

// ContextPath 구하기
function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	var contextPath = location.href.substring(hostIndex, location.href.indexOf(
			'/', hostIndex + 1));
	return contextPath;
}