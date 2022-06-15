var map;
var lat;
var lon;
$(function() {
	// ajax로 db통해서 시도 가져오기
	var container = document.getElementById('map'); // 지도를 담을 영역의 DOM 레퍼런스

	var options = { // 지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표.
		level : 5
	// 지도의 레벨(확대, 축소 정도)
	};
	

	map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
	if (navigator.geolocation) {

		// GeoLocation을 이용해서 접속 위치를 얻어옵니다
		navigator.geolocation.getCurrentPosition(function(position) {

			lat = position.coords.latitude, // 위도
			lon = position.coords.longitude; // 경도
			console.log(lat, lon);
			var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될
			// 위치를
			// geolocation으로
			// 얻어온 좌표로
			// 생성합니다
			message = '<div style="padding:5px;">현재 위치</div>'; // 인포윈도우에
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder(); // 표시될
			searchAddrFromCoords(geocoder, locPosition, splitAddress); // 내용입니다

			// 마커와 인포윈도우를 표시합니다
			displayMarker(locPosition, message);

		});

	} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

		var locPosition = new kakao.maps.LatLng(33.450701, 126.570667), message = 'geolocation을 사용할수 없어요..'

		displayMarker(locPosition, message);
	}
	
})

function searchNearbyStoreBtn(sido, gugun, dong) {

	$(".loading").css("display", "block");
	$.ajax({
		type : "GET",
		url : `/nearbystore/list/${sido}/${gugun}/${dong}`,

		success : function(response) {
			// response 기반으로 시도 옵션 추가
			console.log("주변 상가 찾기 성공");
			console.log(response);
			makeNearbyStoreList(response);
		},
		error : function(response) {
			console.log("주변 상가 찾기 오류");
			makeNearbyStoreList("error");
		}
	})

}

// ContextPath 구하기
function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	var contextPath = location.href.substring(hostIndex, location.href.indexOf(
			'/', hostIndex + 1));
	return contextPath;
}

// 주변 상가 목록 출력, Data를 한 객체의 정보는 공백, 객체들은 :로 구분 예정
function makeNearbyStoreList(data) {
	var positions = [];
	$(".loading").css("display", "none");
	$(".leftSide").empty();

	if (!data || data === "error") {
		$(".leftSide").append("검색된 내용이 없습니다.<br><br>");
		$(".leftSide")
				.append(
						`<img class="loading" src="../res/loading.gif" alt="이미지가 없습니다.">`);
	} else {
		$(".leftSide").append("검색 결과<br><br>");
		$(".leftSide")
				.append(
						`<img class="loading" src="../res/loading.gif" alt="이미지가 없습니다.">`);
		var dataList = data;
		const myMap = new Map();
		dataList.distance = "";
		for (let i = 0; i < dataList.length; i++) {
			let calcLat = Math.abs(lat - dataList[i].lat);
			let calcLng = Math.abs(lon - dataList[i].lng);
			let res = calcLat * calcLat + calcLng * calcLng;
			res = Math.sqrt(res) * 10000;
			dataList[i].distance = res;
			if (myMap.has(dataList[i].storeMinorName)) {
				myMap.set(dataList[i].storeMinorName,myMap.get(dataList[i].storeMinorName)+1);
			} else {
				myMap.set(dataList[i].storeMinorName,1);
			}
		}
		
		dataList = dataList.sort((a,b) => a.distance-b.distance);
		console.log(myMap.entries());
		mapList = [...myMap.entries()].sort((a,b)=>b[1]-a[1]);
		console.log(mapList);
		for(item of mapList) {
			console.log(item);
			let content = "<div>" + "종류:" + item[0] + " : " + item[1]+"<br></div><hr>"
			console.log(content);
			$(".topList").append(content);
		}
		for (var i = 0; i < dataList.length; i++) {

			// rs.getString("storeName")+" "+rs.getString("storeMinorName")+"
			// "+rs.getString("jibunAddress")+" "+rs.getString("lng")+"
			// "+rs.getString("lat");
			var content = i != 0 ? "<div>" + "가게 분류 : " + dataList[i].storeMinorName
					+ "<br>가게 명 : " + dataList[i].storeName + "<br>가게 주소 : "
					+ dataList[i].jibunAddress + "<br></div><hr>" :"<div style='background-color:#F08080;'>" + "가게 분류 : " + dataList[i].storeMinorName
					+ "<br>가게 명 : " + dataList[i].storeName + "<br>가게 주소 : "
					+ dataList[i].jibunAddress + "<br></div><hr>" ;
			$(".leftSide").append(content);
			var temp = {
				title : dataList[i].storeName,
				latlng : new kakao.maps.LatLng(parseFloat(dataList[i].lat),
						parseFloat(dataList[i].lng)),
						content: "가게명: "+ dataList[i].storeName
			};

			positions.push(temp);
		}
		makeMap(positions)
	}
}

// 지도 JS 코드
function makeMap(positions) {
	try {
		positions.length;
		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
		// HTML5의 geolocation으로 사용할 수 있는지 확인합니다

		for (var i = 0; i < positions.length; i++) {
			// 마커 이미지의 이미지 크기 입니다
			var imageSize = new kakao.maps.Size(24, 35);

			if (i === 0) {
				map.setCenter(positions[i].latlng);
			}
			// 마커 이미지를 생성합니다
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
				map : map, // 마커를 표시할 지도
				position : positions[i].latlng, // 마커를 표시할 위치
				title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
				image : i==0 ? new kakao.maps.MarkerImage("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png", new kakao.maps.Size(48, 48)):markerImage
			// 마커 이미지
			});
			var infowindow = new kakao.maps.InfoWindow({
		        content: positions[i].content, // 인포윈도우에 표시할 내용
		      });
			// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		      // 이벤트 리스너로는 클로저를 만들어 등록합니다
		      // 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
		      kakao.maps.event.addListener(
		        marker,
		        "mouseover",
		        makeOverListener(map, marker, infowindow)
		      );
		      kakao.maps.event.addListener(
		        marker,
		        "mouseout",
		        makeOutListener(infowindow)
		      );
		    
		    
		}
	} catch (error) {
		console.log(error);
		$(".loading").css("display", "none");
		$(".leftSide").empty();
		$(".leftSide").append("검색 결과가 없습니다.");
		$(".leftSide")
				.append(
						`<img class="loading" src="../res/loading.gif" alt="이미지가 없습니다.">`);

		
	}
}

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다
function makeOverListener(map, marker, infowindow) {
  return function () {
    infowindow.open(map, marker);
  };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다
function makeOutListener(infowindow) {
  return function () {
    infowindow.close();
  };
}

// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function splitAddress(result, status) {
	if (status === kakao.maps.services.Status.OK) {

		for (var i = 0; i < result.length; i++) {
			// 행정동의 region_type 값은 'H' , 법정동은 'B'
			if (result[i].region_type === 'B') {
				console.log(result[i]);
				searchNearbyStoreBtn(result[i].region_1depth_name,
						result[i].region_2depth_name,
						result[i].region_3depth_name)
				break;
			}
		}
	}
}

function searchAddrFromCoords(geocoder, coords, callback) {

	// 좌표로 행정동 주소 정보를 요청합니다
	geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
}

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition, message) {

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
		map : map,
		position : locPosition
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