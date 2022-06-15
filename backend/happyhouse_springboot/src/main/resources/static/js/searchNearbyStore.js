var map;
$(function(){
	// ajax로 db통해서 시도 가져오기
	$.ajax({
		type : "GET",
		url:getContextPath()+"/nearbystore/sido",
		success:function(response){
			// response 기반으로 시도 옵션 추가
			console.log(response);
			console.log("시도 찾기 성공");
			makeSidoList(response);
		},
		error:function(response){
			console.log("시도 찾기 오류");
			console.log(response);
		}
	})
	makeMap();
})

function searchNearbyStoreBtn(){
	var sido=$("#sido").val();
	var gugun=$("#gugun").val();
	var dong=$("#dong").val();
	var field=$("#field").val()
	if(sido===""||gugun===""||dong===""||field===""){
		alert("선택하지 않은 조건이 있습니다.")
	}else{
		$(".loading").css("display","block");
		$.ajax({
		type : "GET",
		url:getContextPath()+`/nearbystore/list/${sido}/${gugun}/${dong}/${field}`,
		
		success:function(response){
			// response 기반으로 시도 옵션 추가
			console.log("주변 상가 찾기 성공");
			console.log(response);
			makeNearbyStoreList(response);
		},
		error:function(response){
			console.log("주변 상가 찾기 오류");
			makeNearbyStoreList("error");
		}
	})
	}
}

// ContextPath 구하기
function getContextPath(){
	var hostIndex=location.href.indexOf(location.host)+location.host.length;
	var contextPath=location.href.substring(hostIndex,location.href.indexOf('/',hostIndex+1));
	return contextPath; 
}

function findGugun(){
	var sido=$("#sido").val();
	
	obj = {"sido":sido};
	console.log(sido);
	
	$.ajax({
		type : "GET",
		url:getContextPath()+`/nearbystore/gugun/${sido}`,
		
		success:function(response){
			// response 기반으로 구군 옵션 추가
			console.log("구군 찾기 성공");
			console.log(response);
			makeGugunList(response);
		},
		error:function(response){
			console.log("구군 찾기 오류");
			console.log(response);
		}
	})
}

function findDong(){
	var sido=$("#sido").val();
	var gugun=$("#gugun").val();
	$.ajax({
		type : "GET",
		url:getContextPath()+`/nearbystore/dong/${sido}/${gugun}`,
		success:function(response){
			// response 기반으로 동 옵션 추가
			console.log("동 찾기 성공");
			console.log(response);
			makeDongList(response);
		},
		error:function(response){
			console.log("동 찾기 오류");
			console.log(response);
		}
	})
}

	// 시도 옵션 추가
function makeSidoList(data){
	var sidoList=data;
	let option=`<option value='' selected='selected'>시/도 선택</option>`;
	for(var i=0;i<sidoList.length;i++){
		option+=`
			<option value='`+sidoList[i].sido+`'>`+sidoList[i].sido+`</option>
		`;
	}
	
	$("#sido").empty().append(option);
}



	// 구군 옵션 추가
function makeGugunList(data){
	var gugunList=data;
	let option=`<option value='' selected='selected'>구/군 선택</option>`;
	for(var i=0;i<gugunList.length;i++){
		option+=`
			<option value='`+gugunList[i].gugun+`'>`+gugunList[i].gugun+`</option>
		`;
	}
	
	$("#gugun").empty().append(option);
	$("#dong").empty().append(`<option value='' selected='selected'>동/면/읍/리 선택</option>`);
}

	// 동 옵션 추가
function makeDongList(data){
	var dongList=data;
	let option=`<option value='' selected='selected'>동/면/읍/리 선택</option>`;
	for(var i=0;i<dongList.length;i++){
		option+=`
			<option value='`+dongList[i].dong+`'>`+dongList[i].dong+`</option>
		`;
	}
	$("#dong").empty().append(option);
}

// 주변 상가 목록 출력, Data를 한 객체의 정보는 공백, 객체들은 :로 구분 예정
function makeNearbyStoreList(data){
	var positions=[];
	$(".loading").css("display","none");
	$(".leftSide").empty();
	console.log(data);
	if(!data || data==="error"){
		$(".leftSide").append("검색된 내용이 없습니다.<br><br>");
		$(".leftSide").append(`<img class="loading" src="../res/loading.gif" alt="이미지가 없습니다.">`);
	}else{
		$(".leftSide").append("검색 결과<br><br>");
		$(".leftSide").append(`<img class="loading" src="../res/loading.gif" alt="이미지가 없습니다.">`);
		var dataList=data;
		for(var i=0;i<dataList.length;i++){
			
			
			// rs.getString("storeName")+" "+rs.getString("storeMinorName")+"
			// "+rs.getString("jibunAddress")+" "+rs.getString("lng")+"
			// "+rs.getString("lat");
			var content="가게 분류 : "+dataList[i].storeMinorName+"<br>가게 명 : "+dataList[i].storeName+"<br>가게 주소 : "+dataList[i].jibunAddress+" "+dataList[i].lat+" "+dataList[i].lng+"<br><br><hr>"
			$(".leftSide").append(content);
			var temp={title:dataList[i].storeName,
					latlng:new kakao.maps.LatLng(parseFloat(dataList[i].lat),parseFloat(dataList[i].lng))};
			console.log("Lat : "+dataList[i].lat+"Lng : "+dataList[i].lng)
			positions.push(temp);
		}
		makeMap(positions)
	}
}

	// 지도 JS 코드
function makeMap(positions){
	try{
		positions.length;
		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		for (var i = 0; i < positions.length; i ++) {
		    // 마커 이미지의 이미지 크기 입니다
		    var imageSize = new kakao.maps.Size(24, 35); 
		    
			if(i===0){
				map.setCenter(positions[i].latlng);
			}
		    // 마커 이미지를 생성합니다
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage // 마커 이미지
		    });
		}
	}catch(error){
		$(".loading").css("display","none");
		$(".leftSide").empty();
		$(".leftSide").append("검색 결과가 없습니다.");
		$(".leftSide").append(`<img class="loading" src="../res/loading.gif" alt="이미지가 없습니다.">`);
		
		var container = document.getElementById('map'); // 지도를 담을 영역의 DOM 레퍼런스
		var options = { // 지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표.
			level: 5 // 지도의 레벨(확대, 축소 정도)
		};
		
		map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
	}
}