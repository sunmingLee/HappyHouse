<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../template/header.jsp"%>
<link href="<%=root%>/css/register_check.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=root%>/js/register_check.js?ver=1"></script>
<div class="depth">
	<a href="<%=root%>/PageMoveServlet?act=goIndex">ποΈ Home</a> > <a
		href="<%=root%>/PageMoveServlet?act=goUserInfo">νμμ λ³΄</a>
</div>
<div class="register_form_background">
	<div class="register_form">
		<form id="register_form" method="post" onsubmit="return false">
			<input type="hidden" name="act" id="act" value="register_check">
			<ul>
				<h2 style="padding-bottom: 15px;">νμκ°μ</h2>
				<li style="margin-left: 35px;">μμ΄λ&nbsp;<b style="color: red;">*</b>
					<input type="text" name="user_id" id="user_id" readonly>
				</li>
				<li>λΉλ°λ²νΈ&nbsp;<b style="color: red;">*</b> <input type="text"
					name="user_password" id="user_password" readonly>
				</li>
				<li style="margin-left: 52px;">μ΄λ¦&nbsp;<b style="color: red;">*</b>
					<input type="text" name="user_name" id="user_name" readonly>
				</li>
				<li style="margin-left: 35px;">μ΄λ©μΌ&nbsp;<b style="color: red;">*</b>
					<input type="email" name="user_email" id="user_email" readonly>
				</li>
				<li>μ νλ²νΈ&nbsp;<b style="color: red;">*</b> <input type="tel"
					name="user_tel" id="user_tel" readonly>
				</li>
			</ul>

			<button style='margin-left: 222px; width: 90px; height: 35px;'
				onclick='back()'>μμ </button>
			<button style="margin-left: 50px; width: 90px; height: 35px;"
				regiCheckBtn" onclick="register(event)">νμΈ</button>

		</form>
	</div>
</div>


<%@ include file="/WEB-INF/views/template/footer.jsp"%>