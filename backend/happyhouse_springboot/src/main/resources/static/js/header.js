function go_register() {
	location.href = "/user/register";
}

function go_user_info(){
	location.href =  "/user/info";
}

function go_login(){
	location.href = "/user/login";
}

function logout() {
	location.href ="/user/logout";
}

// ContextPath 구하기
function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	var contextPath = location.href.substring(hostIndex, location.href.indexOf(
			'/', hostIndex + 1));
	return contextPath;
}
