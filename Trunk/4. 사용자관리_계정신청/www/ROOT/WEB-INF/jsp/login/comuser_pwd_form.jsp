<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="section-content no-search-cols">
	<form name="formComuserPwd" id="formComuserPwd">
		<input type="hidden" name="slKey" value="${_slKey}">
		<input type="hidden" name="user_id" value="${user_id}">
		<input type="hidden" name="user_nm" value="${user_nm}">
		<input type="hidden" name="tel_no" value="${tel_no}">
		<input type="hidden" name="mobile_no" value="${mobile_no}">
		<input type="hidden" name="pre_passwd" value="${passwd}">
		
		<div class="text-info" style="font-size:11px;">
			* ${msg}
		</div> 
		
		<table class="table-group">
			<tr>
				<th scope="row"><span class="mark-required" id="lbl_passwd">새 비밀번호</span></th>
				<td>
					<div class="ranges-group"><div class="range-10">
						<input type="password" name="passwd" class="form-input" maxlength="30" data-valid="새 비밀번호,required,password">
					</div></div>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="mark-required" id="lbl_passwd2">새 비밀번호 확인</span></th>
				<td>
					<div class="ranges-group"><div class="range-10">
						<input type="password" name="passwd2" class="form-input" maxlength="30" data-valid="새 비밀번호 확인,required">
					</div></div>
				</td>
			</tr>
		</table>
	
		<div class="table-bottom">
			<button type="button" class="btn-basic btn-save" data-after-close="true">저장</button>
			<button type="button" class="btn-basic btn-cancel" data-layer-close="true">취소</button>
		</div>
	</form>
</div>

<script src="<c:url value="/resources/app/login/comuser_pwd_form.js" />"></script>