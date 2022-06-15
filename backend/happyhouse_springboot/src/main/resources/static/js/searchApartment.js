var map;
var marker_list=[];
$(function(){
	$("#year").empty();
	var option=$("<option value='' selected='selected'>년도 선택</option>");
	$("#year").append(option);
	for(var i=2010;i<=2020;i++){
		var option=$("<option value='"+i+"'>"+i+"년</option>");
		$("#year").append(option);
	}
	
	// ajax로 db통해서 시도 가져오기
	$.ajax({
		type : "GET",
		url:getContextPath()+"/apartment/sido",
		success:function(response){
			// response 기반으로 시도 옵션 추가
			makeSidoList(response);
			console.log("시도 찾기 성공");
		},
		error:function(response){
			console.log("시도 찾기 오류");
			console.log(response);
		}
	})
	var container = document.getElementById('map'); // 지도를 담을 영역의 DOM 레퍼런스
		var options = { // 지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표.
			level: 5 // 지도의 레벨(확대, 축소 정도)
		};
		
		map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
})

function searchApartDealBtn(){
	var sido=$("#sido").val();
	var gugun=$("#gugun").val();
	var dong=$("#dong").val();
	var year=$("#year").val();
	var order=$("#order").val();
	if(sido===""||gugun===""||dong===""||year===""){
		alert("선택하지 않은 조건이 있습니다.")
	}else{
		$.ajax({
			type : "GET",
			url:getContextPath()+`/apartment/list/${sido}/${gugun}/${dong}/${year}`,
			success:function(response){
				// response 기반으로 시도 옵션 추가
				if (navigator.geolocation) {

					// GeoLocation을 이용해서 접속 위치를 얻어옵니다
					navigator.geolocation.getCurrentPosition(function(position) {

						lat = position.coords.latitude, // 위도
						lon = position.coords.longitude; // 경도
						console.log(lat, lon);
						var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가
																			// 표시될
						// 위치를
						// geolocation으로
						// 얻어온 좌표로
						// 생성합니다
						message = '<div style="padding:5px;">현재 위치</div>'; // 인포윈도우에
						// 주소-좌표 변환 객체를 생성합니다
						var geocoder = new kakao.maps.services.Geocoder(); // 표시될
						makeApartDealList(response, locPosition);
						console.log("실거래가 찾기 성공");

						// 마커와 인포윈도우를 표시합니다
						displayMarker(locPosition, message);

					});

				} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을
							// 설정합니다

					var locPosition = new kakao.maps.LatLng(33.450701, 126.570667), message = 'geolocation을 사용할수 없어요..'

					displayMarker(locPosition, message);
				}
				
			},
			error:function(response){
				console.log("실거래가 찾기 오류");
				makeApartDealList("error");
			}
		})
	}
}

function searchApartBtn(){
	var sido=$("#sido").val();
	var gugun=$("#gugun").val();
	var dong=$("#dong").val();
	var year=$("#year").val();
	var apart=$("#apartSearch").val();
	if(sido===""||gugun===""||dong===""||year===""||apart===""){
		alert("선택하지 않은 조건이 있습니다.")
	}else{
		$.ajax({
		type : "GET",
		url:getContextPath()+`/apartment/list/${sido}/${gugun}/${dong}/${year}/${apart}`,
		success:function(response){
			// response 기반으로 시도 옵션 추가
			makeApartList(response);
			console.log("아파트 찾기 성공");
		},
		error:function(response){
			console.log("아파트 찾기 오류");
			makeApartDealList("error");
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
	$.ajax({
		type : "GET",
		url:getContextPath()+`/apartment/gugun/${sido}`,
// data:{"act":"findGugun",
// "sido":sido},
		success:function(response){
			// response 기반으로 구군 옵션 추가
			makeGugunList(response);
			console.log("구군 찾기 성공");
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
		url:getContextPath()+`/apartment/dong/${sido}/${gugun}`,
// data:{"act":"findDong",
// "sido":sido,
// "gugun":gugun},
		success:function(response){
			// response 기반으로 동 옵션 추가
			makeDongList(response);
			console.log("동 찾기 성공");
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

// 실거래가 목록 출력
var makeApartDealList = (data, locPosition) => {
	var positions=[];
	var order=$("#order").val();
	$(".leftSide").empty();
	let dataList=data;
	if(!data || data==="error"){
		$(".leftSide").append("검색된 내용이 없습니다.")
	} else {
		$(".leftSide").append("검색 결과<br><br>")
		$(".leftSide").append(`<input type="text" id="apartSearch"></input>&nbsp;&nbsp;<button onclick="searchApartBtn()">아파트 검색</button><br><br>`);
		
		
		
		// 정렬 조건 별로 리스트 정렬
		if(order==="거래가격"){
			dataList = dataList.sort((a,b) => a.dealAmount < b.dealAmount? -1 : 1);
		} else if(order==="건축연도"){
			dataList = dataList.sort((a,b) => b.buildYear - a.buildYear);
		} else if(order==="거리") {
			// 내 위치 받아오기
			for (let i = 0; i < dataList.length; i++) {
	            let calcLat = Math.abs(locPosition.getLat() - dataList[i].lat);
	            let calcLng = Math.abs(locPosition.getLng() - dataList[i].lng);
	            let res = calcLat * calcLat + calcLng * calcLng;
	            res = Math.sqrt(res) * 10000;
	            dataList[i].distance = res;
	        }
	        dataList = dataList.sort((a,b) => a.distance-b.distance);
	        console.log(dataList);

		}
		
		console.log(dataList);
		for(var i=0;i<dataList.length;i++){
			console.log(dataList[i].distance);
			// 0 1 2 3 4 5 6 7 8 9 10
			// rs.getString("aptName")+" "+rs.getString("buildYear")+"
			// "+rs.getString("jibun")+" "+rs.getString("lat")+"
			// "+rs.getString("lng")+" "+dealAmount+"
			// "+rs.getString("dealYear")+" "+rs.getString("dealMonth")+"
			// "+rs.getString("dealDay")+" "+rs.getString("area")+"
			// "+rs.getString("floor");
			var content=`아파트 명 : `+dataList[i].aptName+`<br>건축 연도 : `+dataList[i].buildYear+`<br>거래 가격 : `+dataList[i].dealAmount+`<br>거래 일자 : `+dataList[i].dealYear+`.`+dataList[i].dealMonth+`.`+dataList[i].dealDay+`<br>층 : `+dataList[i].floor+`<br><br><hr>`;
			$(".leftSide").append(content);
			var temp={title:dataList[i].aptName,
					latlng:new kakao.maps.LatLng(parseFloat(dataList[i].lat),parseFloat(dataList[i].lng))};
			positions.push(temp);
		}
		makeMap(positions)
	}
}

// 실거래가를 아파트 이름으로 검색한 결과 출력
function makeApartList(data){
	var positions=[];
	$(".leftSide").empty();
	if(!data || data==="error"){
		$(".leftSide").append("검색된 내용이 없습니다.")
	}else{
		$(".leftSide").append("검색 결과<br><br>")
		$(".leftSide").append(`<input type="text" id="apartSearch"></input>&nbsp;&nbsp;<button onclick="searchApartBtn()">아파트 검색</button><br><br>`);
		var dataList=data;
		for(var i=0;i<dataList.length;i++){
			// 0 1 2 3 4 5 6 7 8 9 10
			// rs.getString("aptName")+" "+rs.getString("buildYear")+"
			// "+rs.getString("jibun")+" "+rs.getString("lat")+"
			// "+rs.getString("lng")+" "+dealAmount+"
			// "+rs.getString("dealYear")+" "+rs.getString("dealMonth")+"
			// "+rs.getString("dealDay")+" "+rs.getString("area")+"
			// "+rs.getString("floor");
			
			var content=`아파트 명 : `+dataList[i].aptName+`<br>건축 연도 : `+dataList[i].buildYear+`<br>거래 가격 : `+dataList[i].dealAmount+`<br>거래 일자 : `+dataList[i].dealYear+`.`+dataList[i].dealMonth+`.`+dataList[i].dealDay+`<br>층 : `+dataList[i].floor+`<br><br><hr>`;
			$(".leftSide").append(content);
			var temp={title:dataList[i].aptName,
					latlng:new kakao.maps.LatLng(parseFloat(dataList[i].lat),parseFloat(dataList[i].lng))};
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
		console.log(positions.length);
		for (var i = 0; i < positions.length; i ++) {
		    // 마커 이미지의 이미지 크기 입니다
		    var imageSize = new kakao.maps.Size(24, 35); 
		    
			if(i==0){
				map.setCenter(positions[i].latlng);
				console.log("시작 좌표" + positions[i].latlng);
			}
		    // 마커 이미지를 생성합니다
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
		    // 마커를 생성합니다
		    marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage // 마커 이미지
		    });
			marker_list.push(marker);
		}
	}catch(error){
		var container = document.getElementById('map'); // 지도를 담을 영역의 DOM 레퍼런스
		var options = { // 지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표.
			level: 5 // 지도의 레벨(확대, 축소 정도)
		};
		
		map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
	}
}

function displayMarker(locPosition, message) {

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    }); 
    
    var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });
    
    // 인포윈도우를 마커위에 표시합니다
    infowindow.open(map, marker);
    
    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);
    
}  