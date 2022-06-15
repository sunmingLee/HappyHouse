<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/template/header.jsp"%>
<link href="<%=root%>/css/announcementList.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="<%=root%>/js/announcement.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<div class="announcement">
	<h1>공지 사항</h1>
</div>
<c:if test="${userInfo.id eq 'admin'}">
	<div class="writeBtn">
		<a href="<%=root%>/PageMoveServlet?act=goWriteAnnouncement">공지 사항
			등록하기</a>
	</div>
</c:if>
<div class="announcementList">
	<div class="pageNumList">
		<button class="pageNum" value="1" onclick="announcementPage(1)">처음</button>
		<button class="pageNum" value="4" onclick="announcementPage(4)">4</button>
		<button class="pageNum" value="5" onclick="announcementPage(5)">5</button>
	</div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>