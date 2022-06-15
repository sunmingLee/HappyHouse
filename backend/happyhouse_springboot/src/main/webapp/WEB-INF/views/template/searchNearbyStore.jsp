<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- js 파일 불러오기 -->
<script type="text/javascript" src="<%=root %>/js/searchNearbyStore.js"></script>
<link href="<%=root %>/css/searchNearbyStore.css" rel="stylesheet" type="text/css">

<div class="search_nav">
	<br><h2>주변 상가 검색하기</h2> <br><br>
	<select id="sido" onchange="findGugun()">
		<option value="" selected="selected">시/도 선택</option>
	</select>
	<select id="gugun" onchange="findDong()">
		<option value="" selected="selected">구/군 선택</option>
	</select>
	<select id="dong">
		<option value="" selected="selected">동/면/읍/리 선택</option>
	</select>
	<select id="field">
		<option value="" selected="selected">분야 선택</option>
		<option value="소매" >소매</option>
		<option value="학문-교육" >학문/교육</option>
		<option value="생활서비스" >생활서비스</option>
		<option value="부동산" >부동산</option>
		<option value="숙박" >숙박</option>
		<option value="음식" >음식</option>
		<option value="관광-여가-오락" >관광/여가/오락</option>
		<option value="스포츠" >스포츠</option>
	</select>
	<button onclick="searchNearbyStoreBtn()">검색 실행!</button>
</div>