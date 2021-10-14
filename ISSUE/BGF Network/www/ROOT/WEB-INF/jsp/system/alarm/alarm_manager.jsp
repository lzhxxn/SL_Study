<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="section-content no-search-cols layout-full-table">

	<div class="title-group" style="margin-bottom:0;">
		<h4><spring:message code="LBL.ALARM.0001"/></h4>
	</div>
	<form name="formAlarmManager" id="formAlarmManager">
		<input type="hidden" name="slKey" value="${_slKey}">
		<div class="table-container">
			<table class="table-group">
				<tr>
					<th scope="row"><spring:message code="LBL.ALARM.0002"/></th>
					<td>
						<div class="ranges-group">
							<div class="range-2"><label><input type="checkbox" name="mail_use_yn" value="Y" ${alarmMng.mail_use_yn eq 'Y' ? 'checked' : ''}> <spring:message code="LBL.ALARM.0003"/></label></div>
							<div class="range-2"><label><input type="checkbox" name="sms_use_yn" value="Y" ${alarmMng.sms_use_yn  eq 'Y' ? 'checked' : ''}> <spring:message code="LBL.ALARM.0004"/></label></div>
							<div class="range-2"><label><input type="checkbox" name="snd_use_yn" value="Y" ${alarmMng.snd_use_yn  eq 'Y' ? 'checked' : ''}> <spring:message code="LBL.ALARM.0005"/></label></div>
							<div class="range-2"><label><input type="checkbox" name="kakao_use_yn" value="Y" ${alarmMng.kakao_use_yn  eq 'Y' ? 'checked' : ''}>카카오톡</label></div>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row"><spring:message code="LBL.ALARM.0006"/></th>
					<td>
					<c:forEach var="code" items="${alarmCodes}">
						<div class="ranges-group">
							<c:choose>
								<c:when test="${code.key == '1'}">
									<c:set var="subConds" value="${riskLevelList}" />
								</c:when>
								<c:when test="${code.key == '2' || code.key == '3' || code.key == '5' || code.key == '10' || code.key == '11'}">
									<c:set var="subConds" value="${eventLevelList}" />
								</c:when>
								<c:otherwise>
									<c:set var="subConds" value="" />
								</c:otherwise>
							</c:choose>
			
							<c:choose>
								<c:when test="${!empty subConds}">
								<div class="range-2">
									<label><input type="checkbox" name="alarm_cd" id="alarm_cd" value="${code.key}" ${alarmConds[code.key] == null ? '' : 'checked'}> ${code.value}</label>
								</div>
								<div class="range-1">
									<select name="alarm_cond${code.key}" class="form-select">
										<c:forEach var="map" items="${subConds}" >
										<option value="${map.key}" ${map.key eq alarmConds[code.key] ? 'selected' : ''} >${map.value}</option>
										</c:forEach>
									</select>
								</div>
								<div class="range-2">
									&nbsp;<spring:message code="LBL.COM.0169"/>
								</div>
								</c:when>
								<c:otherwise>
								<div class="range-10">
									<label><input type="checkbox" name="alarm_cd" id="alarm_cd" value="${code.key}" ${alarmConds[code.key] == null ? '' : 'checked'}><spring:message code="LBL.COM.0170" arguments='${code.value}'/></label>
								</div>
								</c:otherwise>
							</c:choose>	
						</div>
					</c:forEach>
					</td>
				</tr>
			</table>
		</div>
		
		<div class="title-group"  style="margin-bottom:0;">
			<h4><spring:message code="LBL.ALARM.0007"/></h4>
		</div>
		<div class="table-container">
			<table class="table-group">
				<tr>
					<th scope="row"><spring:message code="LBL.ALARM.0007"/></th>
					<td class="box-area">
						<div class="ranges-group">
							<div class="range-6">
								<span class="form-select-outer">
								<select name="chk_user_list" class="form-select" multiple="multiple" size="10">
								<c:forEach var="map" items="${alarmUserList}" >
									<option value="${map.user_id}">
										<c:out value="${map.user_nm}"/>
										<c:out value="[${map.user_id != null ? map.user_id  : ''}]"/>
										<c:if test="${map.mobile_no != null && map.mobile_no != ''}">  
										<c:out value=", [${map.mobile_no}]"/>
										</c:if>
										<c:if test="${map.mail_addr != null && map.mail_addr != ''}">  
										<c:out value=", [${map.mail_addr}]"/>
										</c:if>
									</option>
								</c:forEach>
								</select>
								</span>
							</div>
							<div class="range-4 plus-minus-area">
								<button type="button" class="btn-basic btn-mini btn-plus"><i class="icon-plus"></i></button>
								<button type="button" class="btn-basic btn-mini btn-minus"><i class="icon-minus"></i></button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<div class="table-bottom">
			<button type="button" class="btn-basic btn-save"><spring:message code="BTN.COM.0005"/></button>
		</div>
	</form>

</div>

<script src="<c:url value="/resources/app/system/alarm/alarm_manager.js" />"></script>
