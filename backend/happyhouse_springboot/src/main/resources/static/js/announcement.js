$(function(){
	announcementPage(1)
})
// ContextPath 구하기
function getContextPath(){
	var hostIndex=location.href.indexOf(location.host)+location.host.length;
	var contextPath=location.href.substring(hostIndex,location.href.indexOf('/',hostIndex+1));
	return contextPath; 
}

// 공지사항 리스트 호출
function announcementPage(pageNum){
	$.ajax({
		type : "GET",
		url:getContextPath()+`/announcements/${pageNum}`,
		
		success:function(response){
			// response 기반으로 결과 Data 삽입
			console.log("공지 사항 리스트 성공");
			makeAnnouncementList(response,pageNum);
		},
		error:function(response){
			console.log("공지 사항 리스트 오류");
			console.log(response);
		}
	})
}


function makeAnnouncementList(data,pageNum){
	console.log(data);
	var list=data;
	
	var totalData=list.length
	$(".announcementList").empty()
	for(var i=0;i<list.length;i++){
		var content=`<a href="`+getContextPath()+`/announcements/announcement/`+list[i].announcementNo+`">
		<table>
			<tr>
				<td rowspan="2" id="itemNum">`+list[i].announcementNo+`</td>
				<td rowspan="2" id="itemTitle">`+list[i].subject+`</td>
				<td id="itemUserId">`+list[i].userid+`</td>
			</tr>
			<tr>
				<td id="itemTime">`+list[i].regtime+`</td>
			</tr>
		</table><br>
	</a>`
		$(".announcementList").append(content)
	}
	
	var currPage=parseInt((totalData-list[0].announcementNo)/5)+1
	var lastPage=parseInt((totalData/5))+1;
	var btns=``
	var start,end
	// 현재 페이지 번호가 3보다 작을 때
	if(currPage<3){
		start=1
		end=5
	// 현재 페이지 번호가 마지막 페이지 번호와 차이가 2이하일 때
	}else if(currPage+2>lastPage){
		start=lastPage-4
		end=lastPage
	// 현제 페이지 번호가 정 중앙 일 때
	}else{
		start=currPage-2
		end=currPage+2
	}
	var left,right
	if(currPage>5){
		left=currPage-5
	}else{
		left=1
	}
	if(currPage<=lastPage-5){
		right=currPage+5
	}else{
		right=lastPage
	}
	btns+=`<button type="button" class="pageNumFirst btn btn-outline-primary btn-sm" value="1" onclick="announcementPage(1)">처음</button>`
	btns+=`<button type="button" class="pageNumFirst btn btn-outline-primary btn-sm" value="`+left+`" onclick="announcementPage(`+left+`)">이전</button>`
	for(var i=start;i<=end;i++){
		if(i===currPage){
			btns+=`<button class="pageNum btn btn-outline-primary disabled" value="`+i+`" style="pointer-events:none;" onclick="announcementPage(`+i+`)">`+i+`</button>`
		}else{
			btns+=`<button class="pageNum btn btn-outline-primary" value="`+i+`" onclick="announcementPage(`+i+`)">`+i+`</button>`
		}
	}
	btns+=`<button type="button" class="pageNumLast btn btn-outline-primary btn-sm" value="`+right+`" onclick="announcementPage(`+right+`)">다음</button>`
	btns+=`<button type="button" class="pageNumLast btn btn-outline-primary btn-sm" value="`+lastPage+`" onclick="announcementPage(`+lastPage+`)">마지막</button>`
	$(".announcementList").append(`<div class="pageNumList"></div>`)
	$(".pageNumList").empty().append(btns)
}