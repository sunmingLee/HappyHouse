function registAnnouncemnet(){
	if($("#title").val()===""){
		alert("제목을 입력하세요")
	}else if($("#content").val()===""){
		alert("내용을 입력하세요")
	}else{
		var check=confirm("등록하시겠습니까??")
		if(check){
			alert("등록 완료")
			$("#registAnnouncement").attr("action", getContextPath()+"/AnnouncementServlet").submit();
		}else{
			alert("등록 취소")
			return
		}
	}
}

function check(data){
	if(data==="success"){
		alert("등록 성공하였습니다.")
		//location.href="/announcement/announcementList.jsp"
	}else{
		alert("등록 실패하였습니다.")
	}
}
//ContextPath 구하기
function getContextPath(){
	var hostIndex=location.href.indexOf(location.host)+location.host.length;
	var contextPath=location.href.substring(hostIndex,location.href.indexOf('/',hostIndex+1));
	return contextPath; 
}