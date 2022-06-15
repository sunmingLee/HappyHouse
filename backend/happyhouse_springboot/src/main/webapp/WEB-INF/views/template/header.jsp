<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- JQuery CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>

<!-- 지도 API -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a367219b897c9cd2d9e3880107f26a55&libraries=services"></script>
<title>Happy House Project</title>
<% String root=request.getContextPath(); %>

<link href="<%=root %>/css/header.css" rel="stylesheet" type="text/css">
<link href="<%=root %>/css/footer.css?ver=1" rel="stylesheet"
	type="text/css">

<!-- js 파일 불러오기 -->
<!-- <script type="text/javascript" src="<%=root %>/js/searchApartment.js?ver=1"></script> -->
<script type="text/javascript" src="<%=root%>/js/header.js?ver=1"></script>

</head>
<body>

	<!-- 최 상단 메뉴 -->
	<div class="top_nav">
		<!-- 좌상단 로고 -->
		<div class="top_nav_logo">
			<a href="<%=root%>/"><img width="120px"
				src="data:image/png;base64,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"
				alt="이미지가 없습니다."></img></a>
		</div>
		<!-- 우상단 로그인, 회원가입 버튼 리스트 -->
		<c:if test="${empty userinfo }">
			<div class="top_nav_btn_list">
				<button id="loginBtn" onclick="go_login()">로그인</button>
				<button id="registerBtn" onclick="go_register()">회원가입</button>
			</div>
		</c:if>

		<c:if test="${!empty userinfo }">
			<div class="top_nav_btn_list">
				<button id="logoutBtn" onclick="logout()">로그아웃</button>
				<button id="userInfoBtn" onclick="go_user_info()">회원정보</button>
			</div>
		</c:if>
		<!-- 상단 메뉴 리스트 -->
		<div class="top_nav_menu_list">
			<ul>
				<li><a href="<%=root%>/announcements">공지 사항</a></li>
				<li><a href="<%=root%>/apartment">아파트 실거래</a></li>
				<li><a href="<%=root%>/nearbystore">주변 상가</a></li>
				<li><a href="<%=root%>/nearbystore/curpos">현 위치 상가 보기</a></li>
			</ul>
		</div>
		<div class="clear"></div>
		<hr>
	</div>