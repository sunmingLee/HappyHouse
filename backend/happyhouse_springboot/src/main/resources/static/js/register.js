var idAvailable = false;
function keyevent(event) {
	var user_id = $("#user_id").val();
	$.ajax({
		url : '/user/idcheck',
		data : {
			'ckid' : user_id
		},
		type : 'GET',
		dataType : 'text',
		success : function(response) {
			var cnt = parseInt(response);
			if (cnt == 1) {
				$("#idresult").text(user_id + "는 사용 불가능합니다.");
				idAvailable = false;
			} else {
				$("#idresult").text(user_id + "는 사용 가능합니다.");
				idAvailable = true;
			}
		}
	});
	// $('#user_id').keyup(function() {
	// // console.log(user_id);
	// });
}

function register_check(event) {
	event.preventDefault()
	let user_id = $("#user_id").val()
	let user_pw = $("#user_password").val()
	let user_name = $("#user_name").val()
	let user_email = $("#user_email").val()
	let user_tel = $("#user_tel").val()
	if (user_id.length == 0) {
		alert("ID를 입력하세요.")
		return false
	} else if (user_pw.length < 6) {
		alert("PW 길이가 6 미만입니다.")
		return false
	} else if (user_pw.search(/[0-9]/g) < 0 || user_pw.search(/[a-z]/ig) < 0) {
		alert("영문 혹은 숫자가 포함되지 않았습니다.")
		return false
	} else if (user_name.length == 0) {
		alert("이름을 입력하세요.")
		return false
	} else if (user_email.search(/[\.]/ig) < 0
			|| user_email.search(/[@]/ig) < 0
			|| user_email.search(/[a-z]/ig) < 0) {
		alert("이메일 형식이 잘못 되었습니다.")
		return false
	} else if (user_tel.length != 11) {
		alert("전화 번호가 잘못 되었습니다.")
		return false
	} else if (!idAvailable) {
		alert("중복된 아이디입니다. 다른 아이디를 사용해주세요.")
	} else {
		localStorage.setItem('temp_user',JSON.stringify({'id':user_id,'pw':user_pw,'name':user_name,'email':user_email,'tel':user_tel}))
		var form = document.getElementById("register_form");
		form.action = "/user/register";
		form.method = "POST";
		form.submit();
	}
}

// ContextPath 구하기
function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	var contextPath = location.href.substring(hostIndex, location.href.indexOf(
			'/', hostIndex + 1));
	return contextPath;
}
