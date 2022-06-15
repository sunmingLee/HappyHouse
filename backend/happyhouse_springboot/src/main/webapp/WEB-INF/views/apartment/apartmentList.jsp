<%@page import="com.happyhouse.model.ApartmentDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/template/header.jsp" %>
<%@ include file="/WEB-INF/views/template/searchApartment.jsp" %>
<%@ page import="java.util.List" %>
<link href="<%=root %>/css/apartmentList.css" rel="stylesheet" type="text/css">

<% List<ApartmentDto> apartList=(List<ApartmentDto>)session.getAttribute("apartmentList"); %>

<div class="content">
	<div class="leftSide">
		아파트 실거래를 검색하세요
	</div>
	<div class="rightSide">
		<div id="map" style="width:900px;height:700px;"></div>
	</div>
</div>
<div class="clear"></div>
<%@ include file="/WEB-INF/views/template/footer.jsp"%>