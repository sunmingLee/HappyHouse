<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/template/header.jsp" %>
<!-- js 파일 불러오기 -->
<script type="text/javascript" src="<%=root %>/js/curPosNearbyStore.js"></script>
<link href="<%=root %>/css/searchNearbyStore.css" rel="stylesheet" type="text/css">
<%@ page import="java.util.List" %>
<link href="<%=root %>/css/apartmentList.css" rel="stylesheet" type="text/css">
<div class="content">
	<div class="topList"></div>
	<div class="leftSide">
		주변 상가를 검색하세요<br><br>
		<img class="loading" src="../res/loading.gif" alt="이미지가 없습니다.">
	</div>
	<div class="rightSide">
		<div id="map" style="width:900px;height:700px;"></div>
	</div>
</div>
<div class="clear"></div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>