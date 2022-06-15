<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.happyhouse.model.UserDto"%>
<%@ include file="../template/header.jsp"%>
<link href="<%=root%>/css/register.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=root%>/js/user_info.js"></script>
<div class="depth">
	<a href="<%=root%>/PageMoveServlet?act=goIndex">🏚️ Home</a> > <a
		href="<%=root%>/PageMoveServlet?act=goUserInfo">회원정보</a>
</div>

<div class="register_form_background">
	<div class="register_form">
		<form id="register_form" method="post" onsubmit="return false">
			<ul>
				<h2 style="padding-bottom: 15px;">회원정보</h2>
				<li style="margin-left: 35px;">아이디&nbsp;<b style="color: red;">*</b>
					<input type="text" name="user_id" id="user_id"
					value="${userinfo.id}" readonly>
				</li>
				<li>비밀번호&nbsp;<b style="color: red;">*</b> <input type="text"
					name="user_password" id="user_password"
					placeholder="영문 숫자 포함 6자리 이상" value="${userinfo.user_password}">
				</li>
				<li style="margin-left: 52px;">이름&nbsp;<b style="color: red;">*</b>
					<input type="text" name="user_name" id="user_name"
					value="${userinfo.user_name}">
				</li>
				<li style="margin-left: 35px;">이메일&nbsp;<b style="color: red;">*</b>
					<input type="email" name="user_email" id="user_email"
					value="${userinfo.user_email}">
				</li>
				<li>전화번호&nbsp;<b style="color: red;">*</b> <input type="tel"
					name="user_tel" id="user_tel" placeholder="010XXXXXXXX"
					value="${userinfo.user_tel}">
				</li>
			</ul>
		</form>
		
		<button style='margin-left: 222px; width: 90px; height: 35px;'
			onclick='info_change()'>수정</button>
		<button style="margin-left: 20px; width: 90px; height: 35px;"
			onclick="check()">확인</button>
		<button
			style="margin-left: 120px; background-color: red; width: 90px; height: 35px"
			onclick="user_delete()">회원 탈퇴</button>
	</div>
</div>


<%@ include file="/WEB-INF/views/template/footer.jsp"%>