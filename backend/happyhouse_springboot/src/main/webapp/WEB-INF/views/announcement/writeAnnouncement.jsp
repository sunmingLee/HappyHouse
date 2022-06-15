<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>

<%@ include file="/template/header.jsp" %>
<link href="<%=root %>/css/writeAnnouncement.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=root %>/js/writeAnnouncement.js"></script>

<div class="announcement"><h1>공지 사항 글 작성</h1></div>
<div class="writeAnnouncement">
	<form action="" method="get" id="registAnnouncement">
	<input type="hidden" name="act" value="writeAnnouncement">
	<input type="hidden" name="userId" value="admin">
		제목 : <input type="text" id="title" name="title"><br><br>
		내용<br><br>
		<textarea rows="10" cols="200" id="content" name="content"></textarea><br><br>
		<button onclick="registAnnouncemnet()">등록하기</button>
	</form>

</div>


<%@ include file="/template/footer.jsp" %>