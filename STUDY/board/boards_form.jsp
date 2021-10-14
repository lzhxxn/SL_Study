<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script>
	gSessionUserId = "${sessionScope.USER_ID}";	
    gMultipleManagerRoleYn = "${multipleManagerRoleYn}";
	gUserIpRestrictYn = "${userIpRestrictYn}"; 
</script>

<style>
.user-imgupload-text {
	top:45%;
	position:relative;
	left:15%;
	color:#E2E2E2;
}
</style>

<div class="section-content no-search-cols">

	<form name="formComuser" id="formComuser">
	
		<input type="hidden" name="slKey" value="${_slKey}">
		<table class="table-group">
		
				<input type="hidden" name="boards_id" value="${param.boards_id}" class="form-input" maxlength="30" />
				<tr>
				<th scope="row"><span class="mark-required">Subject</span></th>
				<td>
					<div class="ranges-group"><div class="range-10">
						<input type="text" name="boards_subject" class="form-input" maxlength="30" data-valid="Subject,required">
					</div></div>
				</td>
			</tr>
			<tr>
				<th scope="row">Content</th>
				<td colspan="2"><input type="text" name="boards_content" class="form-input" maxlength="300"></td>
			</tr>
			<tr>
				<th scope="row"><span class="mark-required">Writer</span></th>
				<td colspan="2"><input type="text" name="boards_writer" class="form-input" maxlength="300" data-valid="<spring:message code="FLD.COM.0148"/>,required,alphanum"></td>
			</tr>
			<tr>
				<th scope="row">Email</th>
				<td colspan="2"><input type="email" name="boards_email" class="form-input" maxlength="300" data-valid="<spring:message code="FLD.COM.0151"/>,email"></td>
			</tr>

		</table>
		
		<div class="table-bottom">
			<button type="button" class="btn-basic btn-save" data-after-close="true"><spring:message code="BTN.COM.0005"/></button>
			<button type="button" class="btn-basic btn-delete" data-after-close="true"><spring:message code="BTN.COM.0003"/></button>
			<button type="button" class="btn-basic btn-cancel" data-layer-close="true"><spring:message code="BTN.COM.0014"/></button>
		</div>

	</form>

</div>
<script type="text/javascript" src="<c:url value="/resources/js/jq_plugin/jquery.form.js" />" charset="utf-8"></script>
<script type="text/javascript" src = "<c:url value="/resources/js/jq_plugin/jquery.MultiFile.js" />"></script>
<script src="<c:url value="/resources/app/boards/boards_form.js" />"></script>
