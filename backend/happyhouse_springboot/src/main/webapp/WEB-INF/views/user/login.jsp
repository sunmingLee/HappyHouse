<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../template/header.jsp"%>

<link href="<%=root%>/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=root%>/js/login.js?ver=1"></script>
<div class="depth">
	<a href="<%=root%>/">๐๏ธ Home</a>
</div>
<div class="register_form_background">
	<div class="register_form">
		<form id="login_form" method="post" onsubmit ="return false" >
			<ul>
				<h2 style="padding-bottom: 15px;">๋ก๊ทธ์ธ</h2>
				<li style="margin-left: 35px;">์์ด๋&nbsp;<b style="color: red;">*</b>
					<input type="text" name="user_id" id="user_id" required>
				</li>
				<li>๋น๋ฐ๋ฒํธ&nbsp;<b style="color: red;">*</b> <input
					type="password" name="user_password" id="user_password" required>
					<div style="margin-top: 20px;" id="loginResult">${msg}</div>
				</li>
			</ul>
			<button style="margin-left: 222px;" onclick="login()">๋ก๊ทธ์ธ</button>
		</form>
	</div>
</div>

<%@ include file="../template/footer.jsp"%>