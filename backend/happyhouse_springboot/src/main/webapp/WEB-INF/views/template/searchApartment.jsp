<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- js 파일 불러오기 -->
<script type="text/javascript" src="<%=root %>/js/searchApartment.js?ver=1"></script>
    
<!-- root 이클립스상에선 오류뜨지만 header에 선언했기 때문에 정상작동 -->
<link href="<%= root %>/css/searchApartment.css" rel="stylesheet" type="text/css">

<div class="search_nav">
	<br><h2>아파트 실거래 검색하기</h2> <br><br>
	<select id="sido" onchange="findGugun()">
		<option value="" selected="selected">시/도 선택</option>
	</select>
	<select id="gugun" onchange="findDong()">
		<option value="" selected="selected">구/군 선택</option>
	</select>
	<select id="dong">
		<option value="" selected="selected">동/면/읍/리 선택</option>
	</select>
	<select id="year">
		<option value="" selected="selected">년도 선택</option>
	</select>
	<select id="order">
		<option value="" selected="selected">정렬 기준</option>
		<option value="거래가격" >낮은 거래 가격 순</option>
		<option value="건축연도" >최근 건축 연도 순</option>
		<option value="거리" >가까운 거리순</option>
	</select>
	<button onclick="searchApartDealBtn()">검색 실행!</button>
</div>