<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.happyhouse.model.UserDto"%>
<%@ include file="../template/header.jsp"%>
<link href="<%=root%>/css/register.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=root%>/js/user_info.js"></script>
<div class="depth">
	<a href="<%=root%>/PageMoveServlet?act=goIndex">ποΈ Home</a> > <a
		href="<%=root%>/PageMoveServlet?act=goUserInfo">νμμ λ³΄</a>
</div>

<div class="register_form_background">
	<div class="register_form">
		<form id="register_form" method="post" onsubmit="return false">
			<ul>
				<h2 style="padding-bottom: 15px;">νμμ λ³΄</h2>
				<li style="margin-left: 35px;">μμ΄λ&nbsp;<b style="color: red;">*</b>
					<input type="text" name="user_id" id="user_id"
					value="${userinfo.id}" readonly>
				</li>
				<li>λΉλ°λ²νΈ&nbsp;<b style="color: red;">*</b> <input type="text"
					name="user_password" id="user_password"
					placeholder="μλ¬Έ μ«μ ν¬ν¨ 6μλ¦¬ μ΄μ" value="${userinfo.user_password}">
				</li>
				<li style="margin-left: 52px;">μ΄λ¦&nbsp;<b style="color: red;">*</b>
					<input type="text" name="user_name" id="user_name"
					value="${userinfo.user_name}">
				</li>
				<li style="margin-left: 35px;">μ΄λ©μΌ&nbsp;<b style="color: red;">*</b>
					<input type="email" name="user_email" id="user_email"
					value="${userinfo.user_email}">
				</li>
				<li>μ νλ²νΈ&nbsp;<b style="color: red;">*</b> <input type="tel"
					name="user_tel" id="user_tel" placeholder="010XXXXXXXX"
					value="${userinfo.user_tel}">
				</li>
			</ul>
		</form>
		
		<button style='margin-left: 222px; width: 90px; height: 35px;'
			onclick='info_change()'>μμ </button>
		<button style="margin-left: 20px; width: 90px; height: 35px;"
			onclick="check()">νμΈ</button>
		<button
			style="margin-left: 120px; background-color: red; width: 90px; height: 35px"
			onclick="user_delete()">νμ νν΄</button>
	</div>
</div>


<%@ include file="/WEB-INF/views/template/footer.jsp"%>