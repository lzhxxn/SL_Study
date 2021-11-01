<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="section-content no-search-cols">
	<form name="formLoginOtp" id="formLoginOtp">
		<input type="hidden" name="slKey" value="${_slKey}">
		<%-- <input type="hidden" name="userOtp" value="${user_otp}"> --%>
		
		<table class="table-group">
			<tr>
				<th scope="row"><span class="mark-required">OTP 인증번호</span></th>
				<td><input type="password" name="otp_pswd" id="opt_pswd" class="form-input" maxlength="30" placeholder="OTP PWD"></td>
			</tr>
		</table>
	
		<div class="table-bottom">
			<button type="button" class="btn-basic btn-auth" data-after-close="true">인증</button>
			<button type="button" class="btn-basic btn-cancel" data-layer-close="true"><spring:message code='BTN.COM.0014'/></button>
		</div>
	
	</form>

</div>

<script type="text/javascript" src ="<c:url value="/resources/app/login/login_otp.js" />"></script>