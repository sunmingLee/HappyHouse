<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../template/header.jsp"%>

<link href="<%=root%>/css/register.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=root%>/js/register.js?ver=1"></script>
<div class="depth">
	<a href="<%=root%>/PageMoveServlet?act=goIndex">ποΈ Home</a> > <a
		href="<%=root%>/PageMoveServlet?act=goRegister">νμκ°μ</a>
</div>
<div class="register_form_background">
	<div class="register_form">
		<form id="register_form" method="post" onsubmit="return false">
			<input type="hidden" name="act" id="act" value="register">
			<ul>
				<h2 style="padding-bottom: 15px;">νμκ°μ</h2>
				<li style="margin-left: 35px;">μμ΄λ&nbsp;<b style="color: red;">*</b>
					<input type="text" name="id" id="user_id" placeholder = "id" onkeyup="keyevent()"
					required>
					<div id="idresult"></div>
				</li>
				<li>λΉλ°λ²νΈ&nbsp;<b style="color: red;">*</b> <input
					type="password" name="user_password" id="user_password"
					placeholder="μλ¬Έ μ«μ ν¬ν¨ 6μλ¦¬ μ΄μ" required>
				</li>
				<li style="margin-left: 52px;">μ΄λ¦&nbsp;<b style="color: red;">*</b>
					<input type="text" name="user_name" id="user_name"
					placeholder="User Name" required>
				</li>
				<li style="margin-left: 35px;">μ΄λ©μΌ&nbsp;<b style="color: red;">*</b>
					<input type="email" name="user_email" id="user_email"
					placeholder="email" required>
				</li>
				<li>μ νλ²νΈ&nbsp;<b style="color: red;">*</b> <input type="tel"
					name="user_tel" id="user_tel" placeholder="010XXXXXXXX" required>
				</li>
			</ul>
			<button style="margin-left: 222px; width: 90px; height: 35px;"
				onclick="register_check(event)">λ±λ‘βοΈ</button>

		</form>
	</div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>