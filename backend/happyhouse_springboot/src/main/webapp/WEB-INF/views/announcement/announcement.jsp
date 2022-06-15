<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.happyhouse.model.*"%>

<%@ include file="/WEB-INF/views/template/header.jsp"%>
<link href="<%=root%>/css/announcementList.css" rel="stylesheet"
	type="text/css">
<%
AnnouncementDto announcementInfo = (AnnouncementDto) request.getAttribute("announcementInfo");
%>

<div class="announcement">
	<h1>공지 사항</h1>
</div>
<div class="announcementinfo">
	<div class="mainContent">
		<div class="title">
			<h2><%=announcementInfo.getAnnouncementNo()%>.&nbsp;&nbsp;<%=announcementInfo.getSubject()%></h2>
		</div>
		<div class="writeInfo">
			<button
				onclick="javascript:location.href='/announcements'">목록으로</button>
			<br>
			<br> 작성자 :
			<%=announcementInfo.getUserid()%><br> 작성일 :
			<%=announcementInfo.getRegtime()%></div>
		<div class="content"><%=announcementInfo.getSubject()%></div>
	</div>
</div>


<%@ include file="/WEB-INF/views/template/footer.jsp"%>
