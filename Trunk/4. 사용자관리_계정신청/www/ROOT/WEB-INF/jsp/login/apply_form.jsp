<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script>
	gSessionUserId = "${sessionScope.USER_ID}";
	gMultipleManagerRoleYn = "${multipleManagerRoleYn}";
	gUserIpRestrictYn = "${userIpRestrictYn}";
</script>

<style>
.user-imgupload-text {
	top: 45%;
	position: relative;
	left: 15%;
	color: #E2E2E2;
}
</style>

<div class="section-content no-search-cols">

	<form name="formComuser" id="formComuser">
		<input type="hidden" name="slKey" value="<c:out value="${_slKey}" />">
		<table class="table-group">
			<tr>
				<th scope="row"><span class="mark-required"><spring:message
							code="FLD.COM.0148" /></span></th>
				<td>
					<div class="ranges-group">
						<div class="range-10">
							<input type="text" name="user_id"
								value="<c:out value="${param.user_id}" />" class="form-input"
								maxlength="30"
								data-valid="<spring:message code="FLD.COM.0148"/>,required,alphanum">
						</div>
					</div>
				</td>
				<td rowspan="5" style="width: 148px; border: 1px solid #d8d8d8;"
					title="사진추가">
					<div style="width: 100%; height: 100%; cursor: pointer;"
						id="fileUpload">
						<div class="user-imgupload-text">사진을 등록해주세요</div>
					</div> <input type="file" name="file_upload" style="display: none;"
					accept=".jpg,.jpeg,.png">
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="mark-required"><spring:message
							code="FLD.COM.0131" /></span></th>
				<td>
					<div class="ranges-group">
						<div class="range-10">
							<input type="text" name="user_nm" class="form-input"
								maxlength="30"
								data-valid="<spring:message code="FLD.COM.0131"/>,required">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="mark-required"><spring:message
							code="FLD.COM.0152" /></span></th>
				<td>
					<div class="ranges-group">
						<div class="range-10">
							<input type="password" name="passwd" class="form-input"
								maxlength="30">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="mark-required"><spring:message
							code="FLD.COM.0153" /></span></th>
				<td>
					<div class="ranges-group">
						<div class="range-10">
							<input type="password" name="passwd2" class="form-input"
								maxlength="30">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="FLD.COM.0154" /></th>
				<td>
					<div class="ranges-group">
						<div class="range-10">
							<input type="tel" name="tel_no" class="form-input" maxlength="15"
								data-valid="<spring:message code="FLD.COM.0154"/>,phone">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="mark-required"><spring:message code="FLD.COM.0150" /></span></th>
				<td>
					<div class="ranges-group">
						<div class="range-10">
							<input type="tel" name="mobile_no" class="form-input"
								maxlength="15"
								data-valid="<spring:message code="FLD.COM.0150"/>,required,phone">
						</div>
					</div>
				</td>
				<td>
					<div class="ranges-group">
						<div class="range-10">사진은 최대 1MB까지 업로드 가능합니다.</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="mark-required"><spring:message code="FLD.COM.0151" /></span></th>
				<td colspan="2">
					<div class="ranges-group">
						<div class="range-6">
							<input type="email" name="mail_addr" class="form-input"
								maxlength="100"
								data-valid="<spring:message code="FLD.COM.0151"/>,required,email">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Line Token</th>
				<td colspan="2"><input type="text" name="line_token"
					class="form-input" maxlength="300"></td>
			</tr>
			<tr style="display:none">
				<th scope="row"><spring:message code="FLD.COM.0147" /></th>
				<td colspan="2">
					<div class="ranges-group" >
						<div class="range-6">
							<div class="form-select-outer">
								<select name="role_id" class="form-select" data-size="12">
									<c:forEach var="map" items="${comUserRoleList}">
										<c:if test="${multipleManagerRoleYn == 'Y'}">
											<option value="${map.role_id}"><c:out
													value="${map.role_nm}" /></option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="mark-required"><spring:message
							code="FLD.COM.0008" /> </span></th>
				<td colspan="2">
					<div class="ranges-group">
						<div class="range-6">
							<div class="form-select-outer">
								<select name="group_cd" class="form-select" data-size="12">
									<c:forEach var="map" items="${groupCds}">
										<option value="${map.key}"><c:out
												value="${map.value}" /></option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div> <%-- <a class="btn_sml" href="javascript:_SL.popupCodeList('${groupComCode}');"><span>등록</span></a> --%>
				</td>
			</tr>
			<tr>
				<th><spring:message code="FLD.COM.0149" /></th>
				<td colspan="2">
					<div class="ranges-group">
						<div class="range-8">
							<div class="form-select-outer">
								<select name="user_cust_list" class="form-select">
									<option value=""><spring:message code="SEL.COM.0002" /></option>
									<c:forEach var="map" items="${groupCds}">
										<option value="${map.key}"><c:out
												value="${map.value}" /></option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="range-2">
							<button type="button" class="btn-basic btn-mini" id="cust_add">
								<i class="ic icon-plus"></i>
							</button>
							<button type="button" class="btn-basic btn-mini" id="cust_del">
								<i class="ic icon-minus"></i>
							</button>
						</div>
					</div>
					<div class="ranges-group">
						<div class="range-10">
							<div class="form-select-outer">
								<select name="cust_list" size="4" multiple="multiple"
									class="form-select">
								</select>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="mark-required"><spring:message
							code="FLD.COM.0155" /></span></th>
				<td colspan="2">
					<div class="ranges-group">
						<div class="range-6">
							<div class="form-select-outer">
								<select name="login_menu_id" class="form-select"
									data-valid="<spring:message code="FLD.COM.0155"/>,required">
									<c:forEach var="map" items="${loginMenuIds}">
										<option value="${map.key}"><c:out
												value="${map.value}" /></option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr style="display:none">
				<th scope="row"><spring:message code="FLD.USR.0013" /></th>
				<td colspan="2"><c:if test="${userIpRestrictYn != 'Y'}">
						<div class="form-label">
							<label class="form-label-inline"><input type="radio"
								name="auth_ip_opt" value="0">
							<spring:message code="FLD.COM.0157" /> </label> <label
								class="form-label-inline"><input type="radio"
								name="auth_ip_opt" value="1">
							<spring:message code="FLD.COM.0156" /> </label>
						</div>
					</c:if>
					<div data-name="auth_ip_wrapper" class="ranges-group">
						<div class="range-5">
							<input type="text" name="auth_ip1" value=""
								data-valid="<spring:message code="FLD.USR.0013"/>,ip"
								class="form-input" placeholder="ipv4 or ipv6">
						</div>
						<c:if test="${multipleManagerRoleYn =='Y'}">
							<div class="range-5">
								<input type="text" name="auth_ip2" value=""
									data-valid="<spring:message code="FLD.USR.0013"/>,ip"
									class="form-input" placeholder="ipv4 or ipv6">
							</div>
						</c:if>
					</div></td>
			</tr>
			<tr style="display:none">
				<th scope="row"><span class="mark-required"><spring:message
							code="FLD.COM.0093" /></span></th>
				<td colspan="2">
					<div class="form-label">
						<label class="form-label-inline"><input type="radio"
							name="sync_yn" value="N" checked="checked">
						<spring:message code="LBL.USR.0004" /></label> <label
							class="form-label-inline"><input type="radio"
							name="sync_yn" value="Y">
						<spring:message code="LBL.USR.0005" /></label>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="FLD.COM.0053" /></th>
				<td colspan="2"><input type="text" name="description"
					class="form-input" maxlength="300"></td>
			</tr>
		</table>

		<div class="table-bottom">
			<button type="button" class="btn-basic btn-add"
				data-after-close="true">
				신청
			</button>
			<button type="button" class="btn-basic btn-cancel"
				data-layer-close="true">
				취소
			</button>
		</div>

	</form>

</div>
<script type="text/javascript"
	src="<c:url value="/resources/js/jq_plugin/jquery.form.js" />"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jq_plugin/jquery.MultiFile.js" />"></script>
<script
	src="<c:url value="/resources/app/login/apply_form.js" />"></script>
