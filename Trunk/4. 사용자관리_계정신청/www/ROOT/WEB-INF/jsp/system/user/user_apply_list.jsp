<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="section-search-cols">
	<h3><spring:message code="LBL.COM.0007"/></h3>

	<form id="searchUserList">
		<input type="hidden" name="slKey" value="<c:out value="${_slKey}"/>">
		<span class="form-label"><spring:message code="LBL.COM.0135"/></span>
		<div class="forms-group">
			<input type="text" name="s_user_id" value="<c:out value="${param.s_user_id}" />" class="form-input">
		</div>

		<span class="form-label"><spring:message code="FLD.COM.0131"/></span>
		<div class="forms-group">
			<input type="text" name="s_user_nm" value="<c:out value="${param.s_user_nm}" />" class="form-input">
		</div>

		<span class="form-label"><spring:message code="LBL.COM.0048"/></span>
		<div class="forms-group">
			<select name="s_group_cd" class="form-select">
				<option value=""><spring:message code="SEL.COM.0002"/></option>
				<c:forEach var="map" items="${groupCds}" >
					<option value="${map.key}" ${map.key == param.s_group_cd?"selected":""}><c:out value="${map.value}" /></option>
				</c:forEach>
			</select>
		</div>

		<button type="button" class="form-submit"><spring:message code="BTN.COM.0007"/></button>
	</form>
</div><!-- / .section-search-cols -->

<div class="section-content">

	<!-- grid -->
	<div class="grid-group">
		<div id="gridApplyList"></div>
	</div>

<%-- 	<div class="grid-bottom">
		<div class="put-right">
			<button type="button" class="btn-basic btn-add"><spring:message code="BTN.COM.0017"/></button>
		</div>
	</div> --%>
	<!-- / grid -->

</div><!-- / .section-content -->

<script src="<c:url value="/resources/app/system/user/user_apply_list.js" />"></script>
