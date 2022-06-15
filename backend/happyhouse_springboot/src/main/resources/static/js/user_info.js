
function info_change() {
// alert("회원 정보가 수정되었습니다.")
	const user_id = $("#user_id").val();
	let user_pw = $("#user_password").val();
	let user_name = $("#user_name").val();
	let user_email = $("#user_email").val();
	let user_tel = $("#user_tel").val();
	if (user_pw.length < 6) {
		alert("PW 길이가 6 미만입니다.");
		return false;
	} else if (user_pw.search(/[0-9]/g) < 0 || user_pw.search(/[a-z]/ig) < 0) {
		alert("영문 혹은 숫자가 포함되지 않았습니다.");
		return false;
	} else if (user_name.length == 0) {
		alert("이름을 입력하세요.");
		return false;
	} else if (user_email.search(/[\.]/ig) < 0
			|| user_email.search(/[@]/ig) < 0
			|| user_email.search(/[a-z]/ig) < 0) {
		alert("이메일 형식이 잘못 되었습니다.")
		return false
	} else if (user_tel.length != 11) {
		alert("전화 번호가 잘못 되었습니다.")
		return false
	} else{
		$.ajax({
			type : "PUT",
			url:"/user/update",
			data:{"id":user_id,"user_password":user_pw, "user_name":user_name, "user_email":user_email,"user_tel":user_tel},
			success:function(response){
				// response 기반으로 결과 Data 삽입
				console.log("업데이트 성공");
				
			},
			error:function(response){
				console.log("업데이트 오류");
				console.log(response);
			}
		})
	}
}

function check(){
	location.href = "/";
}

function user_delete() {
	event.preventDefault();
	if(confirm("정말 탈퇴하시겠습니까?")){
		const user_id = $("#user_id").val();
		$.ajax({
			type : "DELETE",
			url:`/user/${user_id}`,
			success:function(response){
				// response 기반으로 결과 Data 삽입
				console.log("업데이트 성공");
				
			},
			error:function(response){
				console.log("업데이트 오류");
				console.log(response);
				location.href="/";
			}
		})
	}
}

// ContextPath 구하기
function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	var contextPath = location.href.substring(hostIndex, location.href.indexOf(
			'/', hostIndex + 1));
	return contextPath;
}