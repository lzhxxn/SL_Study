<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script>
	var comUserRoleList = '${comUserRoleList}';
</script>
<div class="section-content no-search-cols">
	<form name="formUserInfo" id="formUserInfo" autocomplete="off">
		<input type="hidden" name="slKey" value="${_slKey}">
		<input type="hidden" name="p_user_id" value="${p_user_id }">
		<table class="table-group">
			<tr>
				<th scope="row"><spring:message code='FLD.USR.0001'/></th>
				<td><input type="text" name="user_id" class="form-input form-text" readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code='FLD.USR.0002'/></th>
				<td><input type="text" name="user_nm" class="form-input form-text" readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code='FLD.USR.0003'/></th>
				<td>
					<div class="ranges-group">
						<div class="range-6"><input type="password" name="passwd" class="form-input" maxlength="30" data-valid="<spring:message code='FLD.USR.0003'/>,password" maxlength="30" ></div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code='FLD.USR.0004'/></th>
				<td>
					<div class="ranges-group">
						<div class="range-6"><input type="password" name="re_passwd" class="form-input form-input" maxlength="30" data-valid="<spring:message code='FLD.USR.0004'/>,password" maxlength="30"></div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code='FLD.USR.0005'/></th>
				<td>
					<div class="ranges-group">
						<div class="range-6"><input type="text" name="tel_no" class="form-input"></div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code='FLD.USR.0006'/></th>
				<td>
					<div class="ranges-group">
						<div class="range-6"><input type="text" name="mobile_no" class="form-input"></div>
					</div>		
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code='FLD.USR.0007'/></th>
				<td>
					<div class="ranges-group">
						<div class="range-6"><input type="text" name="mail_addr" class="form-input"></div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Line Token</th>
				<td>
					<div class="ranges-group">
						<input type="text" name="line_token" class="form-input"></div>
				</td>
			<tr>
				<th scope="row"><spring:message code='FLD.USR.0008'/></th>
				<td><input type="text" name="role_nm" class="form-input form-text" readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code='FLD.USR.0009'/></th>
				<td><input type="text" name="group_nm" class="form-input form-text" readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code='FLD.USR.0011'/></th>
				<td>
					<div class="ranges-group">
						<div class="ranges-group" data-ui="tooltip" data-text="<spring:message code='INF.USR.0001'/>">
						<select name="login_menu_id" class="form-select">
							<c:forEach var="map" items="${loginMenuIds}" >
							<option value="${map.code_id}" data-value="${map.flag1}"><c:out value="${map.code_name}" /></option>
							</c:forEach>
						</select>
						</div>
					</div>
				</td>
			</tr>
	 		<tr>
				<th scope="row"><spring:message code='FLD.USR.0012'/></th>
				<td>
					<div class="ranges-group">
						<div class="range-4"><label><input type="radio" name="alarm_mode" value="all" checked="checked"><spring:message code='LBL.USR.0001'/></label></div>
						<div class="range-4"><label><input type="radio" name="alarm_mode" value="dashboard"><spring:message code='LBL.USR.0002'/></label></div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code='FLD.USR.0013'/></th>
				<td><input type="text" name="auth_ip" class="form-input form-text" readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code='FLD.USR.0014'/></th>
				<td><input type="text" name="description" class="form-input form-text" readonly="readonly"></td>
			</tr>
		</table>
		
		<div class="table-bottom">
			<button type="button" class="btn-basic btn-save" data-after-close="true"><spring:message code='BTN.COM.0005'/></button>
			<button type="button" class="btn-basic btn-cancel" data-layer-close="true"><spring:message code='BTN.COM.0014'/></button>
		</div>
	</form>
</div>

<script src="<c:url value='/resources/app/main/userInfo/main_simple_user_info.js' />"></script>
