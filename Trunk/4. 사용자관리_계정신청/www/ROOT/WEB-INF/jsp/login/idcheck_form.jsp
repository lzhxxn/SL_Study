<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="section-content no-search-cols">
	<form name="formIdcheck" id="formIdcheck">
		<input type="hidden" name="slKey" value="<c:out value="${_slKey}"/>">
		<input type="hidden" name="b_collector_ip" id="b_collector_ip">
		
		<table class="table-group">
			<tr>
				<th scope="row"><span class="mark-required">이름</span></th>
				<td><input type="text" name="user_nm" class="form-input" maxlength="30" data-valid="<spring:message code="FLD.COM.0131"/>,required"></td>
			</tr>
			<tr>
				<th scope="row"><span class="mark-required">이메일</span></th>
				<td><input type="text" name="mail_addr" class="form-input" maxlength="30" data-valid="<spring:message code="FLD.COM.0151"/>,required,email"></td>
			</tr>
		</table>
	
		<div class="table-bottom">
			<button type="button" class="btn-basic btn-find" data-after-close="true">찾기</button>
			<button type="button" class="btn-basic btn-cancel" data-layer-close="true"><spring:message code='BTN.COM.0014'/></button>
		</div>
	
	</form>

</div>

<script src="<c:url value="/resources/app/login/idcheck_form.js" />"></script>
