<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="section-search-cols">
	<h3><spring:message code="LBL.COM.0007"/></h3>

	<form id="searchUserList">
	
		<input type="hidden" name="slKey" value="${_slKey}">
		<span class="form-label">Subject</span>
		<div class="forms-group">
			<input type="text" name="s_boards_subject" value="<c:out value="${param.s_boards_subject}" />" class="form-input">
		</div>

		<span class="form-label">Writer</span>
		<div class="forms-group">
			<input type="text" name="s_boards_writer" value="<c:out value="${param.s_boards_writer}" />" class="form-input">
		</div>

		<button type="button" class="form-submit"><spring:message code="BTN.COM.0007"/></button>
	</form>
	
</div><!-- / .section-search-cols -->

<div class="section-content">

	<!-- grid -->
	<div class="grid-group">
		<div id="gridUserList"></div>
	</div>

	<div class="grid-bottom">
		<div class="put-right">
			<button type="button" class="btn-basic btn-add"><spring:message code="BTN.COM.0017"/></button>
		</div>
	</div>
	<!-- / grid -->

</div><!-- / .section-content -->

<script src="<c:url value="/resources/app/boards/boards_list.js" />"></script>