<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../template/header.jsp"%>
<link href="<%=root%>/css/register_check.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=root%>/js/register_check.js?ver=1"></script>
<div class="depth">
	<a href="<%=root%>/PageMoveServlet?act=goIndex">🏚️ Home</a> > <a
		href="<%=root%>/PageMoveServlet?act=goUserInfo">회원정보</a>
</div>
<div class="register_form_background">
	<div class="register_form">
		<form id="register_form" method="post" onsubmit="return false">
			<input type="hidden" name="act" id="act" value="register_check">
			<ul>
				<h2 style="padding-bottom: 15px;">회원가입</h2>
				<li style="margin-left: 35px;">아이디&nbsp;<b style="color: red;">*</b>
					<input type="text" name="user_id" id="user_id" readonly>
				</li>
				<li>비밀번호&nbsp;<b style="color: red;">*</b> <input type="text"
					name="user_password" id="user_password" readonly>
				</li>
				<li style="margin-left: 52px;">이름&nbsp;<b style="color: red;">*</b>
					<input type="text" name="user_name" id="user_name" readonly>
				</li>
				<li style="margin-left: 35px;">이메일&nbsp;<b style="color: red;">*</b>
					<input type="email" name="user_email" id="user_email" readonly>
				</li>
				<li>전화번호&nbsp;<b style="color: red;">*</b> <input type="tel"
					name="user_tel" id="user_tel" readonly>
				</li>
			</ul>

			<button style='margin-left: 222px; width: 90px; height: 35px;'
				onclick='back()'>수정</button>
			<button style="margin-left: 50px; width: 90px; height: 35px;"
				regiCheckBtn" onclick="register(event)">확인</button>

		</form>
	</div>
</div>


<%@ include file="/WEB-INF/views/template/footer.jsp"%>