$(function(){
	// ajax로 뉴스 크롤링해서 가져오기
	$.ajax({
		type : "GET",
		url:"/newscrawling",
		success:function(response){
			// response 기반으로 결과 Data 삽입
			makeNewsList(response);
			console.log("뉴스 크롤링 성공");
		},
		error:function(response){
			console.log("뉴스 크롤링 오류");
			console.log(response);
		}
	})
})

// ContextPath 구하기
function getContextPath(){
	var hostIndex=location.href.indexOf(location.host)+location.host.length;
	var contextPath=location.href.substring(hostIndex,location.href.indexOf('/',hostIndex+1));
	return contextPath; 
}

function makeNewsList(data){
	html = "";
	for (var i = 0; i < data.length; i++) {
		item = data[i];
		html += 
		`<div>
			<a href="${item.url}">${item.text}</a>
		</div>`
	}
	$(".news").append(html);
	console.log(data);
}