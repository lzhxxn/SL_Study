<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="section-content no-search-cols">
	<form name="formPwdcheck" id="formPwdcheck">
		<input type="hidden" name="slKey" value="<c:out value="${_slKey}"/>">
		<input type="hidden" name="num" value="<c:out value="${num}"/>">
		
		<table class="table-group">
			<tr>
				<th scope="row"><span class="mark-required">아이디</span></th>
				<td><input type="text" name="user_id" class="form-input" maxlength="30" data-valid="<spring:message code="FLD.COM.0148"/>,required,alphanum"></td>
			</tr>
			<tr>
				<th scope="row"><span class="mark-required">이메일</span></th>
				<td><input type="text" name="mail_addr" class="form-input" data-valid="<spring:message code="FLD.COM.0151"/>,required,email"></td>
			</tr>
		</table>
		
		<div class="table-bottom">
			<button type="button" class="btn-basic btn-find" data-after-close="true">이메일인증</button>
		</div>
	</form>
	
	<form name="formEmailauth" id="formEmailauth" style="display:none;">
		<table class="table-group">
			<tr>
				<th scope="row"><span class="mark-required">인증번호</span></th>
				<td><input type="text" name="email_auth" placeholder="인증번호를 입력하세요" class="form-input"></td>
			</tr>
		</table>
	
		<div class="table-bottom">
			<button type="button" class="btn-basic btn-check" data-after-close="true">확인</button>
			<button type="button" class="btn-basic btn-cancel" data-layer-close="true"><spring:message code='BTN.COM.0014'/></button>
		</div>
	</form>
	
	<form name="formComuserPwd" id="formComuserPwd" style="display:none;">
		<input type="hidden" name="slKey" value="${_slKey}">
		<input type="hidden" name="user_id" value="${user_id}">
		<input type="hidden" name="user_nm" value="${user_nm}">
		<input type="hidden" name="tel_no" value="${tel_no}">
		<input type="hidden" name="mobile_no" value="${mobile_no}">
		<input type="hidden" name="pre_passwd" value="${passwd}">
		
		<div class="text-info" style="font-size:11px;">
			* 새로운 비밀번호를 입력해주세요.
		</div> 
		
		<table class="table-group">
			<tr>
				<th scope="row"><span class="mark-required" id="lbl_passwd">새 비밀번호</span></th>
				<td>
					<div class="ranges-group"><div class="range-10">
						<input type="password" name="passwd" id="input-id" class="form-input" maxlength="30" data-valid="새 비밀번호,required,password">
					</div></div>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="mark-required" id="lbl_passwd2">새 비밀번호 확인</span></th>
				<td>
					<div class="ranges-group"><div class="range-10">
						<input type="password" name="passwd2" id="input-id2" class="form-input" maxlength="30" data-valid="새 비밀번호 확인,required" >
					</div></div>
				</td>
			</tr>
		</table>
		
		<div class="text-info" id="msg-danger" style="font-size:11px; display:none;">
			설정하신 비밀번호와 일치하지 않습니다.
		</div> 
		
		<div class="table-bottom">
			<button type="button" class="btn-basic btn-save" data-after-close="true">저장</button>
			<button type="button" class="btn-basic btn-cancel" data-layer-close="true">취소</button>
		</div>
	</form>

</div>

<script src="<c:url value="/resources/app/login/pwdcheck_form.js" />"></script>
