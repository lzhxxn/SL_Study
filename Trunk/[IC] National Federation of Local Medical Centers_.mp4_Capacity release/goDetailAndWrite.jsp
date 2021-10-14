<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="reqLocLang" value="${empty pageContext.response.locale.language ? 'ko' : pageContext.response.locale.language}" />
<style>
td button.icon-times{font-size:8px !important;vertical-align:middle;margin-bottom:3px;}
</style>
<script type="text/javascript" src = "<c:url value="/resources/js/jq_plugin/jquery.MultiFile.js" />"></script>
<script type="text/javascript" src = "<c:url value="/resources/editor/js/service/HuskyEZCreator.js" />" charset="utf-8"></script>
<script>
	var gCONTEXT_PATH = "<c:url value='/' />".split(";")[0];
	var reqLocLang = "${reqLocLang}";
	var gLocaleNm = "${pageContext.response.locale}".split("_")[1];
	
	switch(reqLocLang){
	 case "en": 
		 reqLocLang = "en_US";
		 break;
	 case "ko":
		 reqLocLang = "ko_KR";
		 break;
	 default :
		 break;
	}
</script>

<form name="detailAndWriteform" id="detailAndWriteform" method="post" enctype="multipart/form-data" style="padding:15px;">
	<input type="hidden" name="slKey" value="${_slKey}">
	<input type="hidden" name="writeChk" id="writeChk" value="">
	<input type="hidden" name="textareaStr" id="textareaStr" value=""/>
	<input type="hidden" name="grp_no" value=${list[0].grp_no }>
	<input type="hidden" name="StartChk" id="StartChk" value=${StartChk }>
	<input type="hidden" name="depth" id="depth" value=${list[0].order_no }>
	<input type="hidden" name="bbs_typ_cd" id="bbs_typ_cd" value=${bbs_typ_cd }>
	<input type="hidden" name="bbs_seq"    id="bbs_seq" value=${bbs_seq }>
	<input type="hidden" name="bf_seq"    id="bf_seq" value=""/>
	<input type="hidden" name="comment_no" id="comment_no" value="">
	<input type="hidden" name="fileDeleteList" id="fileDeleteList" value="">
	<input type="hidden" name="YnChk" id="YnChk" value="${YnChk}">
	<input type="hidden" name="role_id" id="role_id" value="${role_id}">
	<input type="hidden" name="loginId" id="loginId" value="${loginId}">
	
${searchHiddenParam}

<!-- wrap_set -->
			<!-- view -->
	<div id="s_area02">

	<div id="writeform">
	
		<table class="view-body">
				<tbody><tr>
					<th scope="row"><spring:message code='FLD.BRD.0004'/></th>
					<td>
						<div class="ranges-group">
							<div class="range-2">
								<div class="form-select-outer">
									<select class="form-select" name="bbs_subject_cd" id="bbs_subject_cd">
										<option><spring:message code='SEL.COM.0002'/></option>
										<c:forEach var="map" items="${bbsSubjectCdList}" >
											<option value="${map.key}" ${map.key == list[0].bbs_subject_cd ? "selected":""}>${map.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="range-8">
								<input type="text" class="form-input" id="bd_subject" name="bd_subject" data-valid="<spring:message code='FLD.BRD.0004'/>,required" maxlength="250">
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row"><spring:message code='FLD.BRD.0007'/></th>
					<td>
						<input type="file" name="bd_file" class="form-file multi-file" style="width:50%;" accept="doc|ppt|xls|dot|gul|hwp|asv|txt|pdf|xlsx|docx|csv|zip|gz|mp4">
					</td>
				</tr>
				
				<c:if test="${menuType == '1'}">
				<tr>
					<th scope="row"><spring:message code='FLD.BRD.0008'/></th>
					<td>
						<div class="ranges-group">
							<div class="range-1">
								<div class="form-select-outer">
									<select class="form-select" id="popup_check" name="popup_check">
										<option value="N">N</option>
										<option value="Y" ${list[0].popup_check =="Y" ? "selected" :""}>Y</option>
									</select>
								</div>
							</div>
							<div class="range-2" id="popup_timeSet">
								<div class="form-select-outer">
									<select id='timeSet'  class="form-select" data-scroll="false">
										<option value="0"><spring:message code='SEL.BRD.0003'/></option>
										<option value="1440">1<spring:message code='LBL.COM.0018'/></option>
										<option value="10080">7<spring:message code='LBL.COM.0018'/></option>
										<option value="20160">14<spring:message code='LBL.COM.0018'/></option>
										<option value="43200">1<spring:message code='LBL.COM.0075'/></option>
										<option value="129600">3<spring:message code='LBL.COM.0075'/></option>
										<option value="259200">6<spring:message code='LBL.COM.0075'/></option>
										<option value="525600">1<spring:message code='LBL.COM.0076'/></option>
									</select>
								</div>
							</div>
							<div class="range-1" id="popup_date">
								<input type="text" name='popup_expire_dt' id='popup_expire_dt' class="form-input align-center" placeholder="<spring:message code='FLD.BRD.0009'/>" readonly="readonly" data-datepicker="true" autocomplete="off">
							</div>
							<div class="range-5" id="popup_label">
								<div class="form-label text-point">&nbsp;<spring:message code='INF.BRD.0001'/></div>
							</div>
						</div>
					</td>
				</tr>
				</c:if>
				
				<tr>
					<td colspan="2">
						<textarea name="bd_cont" id="bd_cont" rows="10" cols="100" style="width:100%; height:412px; display: none;"></textarea>
					</td>
				</tr>
			</tbody>
			</table>
			
			<div class="grid-bottom">
				<div class="put-right">
					<button type="button" class="btn-basic write-save"><spring:message code='BTN.COM.0005'/></button>
					<button type="button" class="btn-basic write-cancel"><spring:message code='BTN.COM.0014'/></button>
				</div>
			</div>
	</div>
	
	</div>




	<div id="Detailform" >

		<div class="view-table-group">
			<div class="view-head">
				<c:if test="${not empty list[0].bbs_subject_nm}">
					[<c:out value="${list[0].bbs_subject_nm}"/>]
				</c:if>
				<span id="modifysubject"><c:out value="${list[0].bd_subject}"/></span>
			</div>

			<table class="view-body">
				<tbody><tr>
					<th scope="row"><spring:message code='FLD.BRD.0006'/></th>
					<td>
						<fmt:parseDate var="regDt" value="${list[0].reg_dt}" pattern="yyyyMMddHHmmss"/>
						<fmt:formatDate value="${regDt}" type="DATE" pattern="${ymdhmsFmt}" />
					</td>
				</tr>
				<tr>
					<th scope="row"><spring:message code='FLD.COM.0144'/></th>
					<td><span id="wirteUserName" data-value="${list[0].user_id}"><c:out value="${list[0].user_nm}" /></span></td>
				</tr>
				<tr>
					
					<th scope="row"><spring:message code='FLD.BRD.0007'/></th>
					
					
					<td colspan="3">	
						<c:forEach var="map" items="${file_list}" varStatus="calCount">
						<button type="button" class="btn-link filedown" data-name="filedown" data-fileSeq='${map.bf_seq}'>${map.org_name}</button><br>
						</c:forEach>
					</td>
						
				</tr>
				<tr>
					<td colspan="2">
						<textarea name="bd_cont_temp" id="bd_cont_temp" style="display: none;"><c:out value="${list[0].bd_cont}" /></textarea>
						${list[0].bd_cont}
						<div id="blankDiv" style="min-height: 200px;" ></div>
					</td>
				</tr>
				</tbody>
			</table>

			<div class="view-foot">
				<div class="tit"><spring:message code='FLD.BRD.0010'/>(<c:out value="${fn:length(list1)}"/>)</div>
				<div class="area-write">
					<textarea rows="2" cols="50" id="comet_cont" name="comet_cont" placeholder="<spring:message code='INF.BRD.0002'/>"></textarea>
					<span class="txt-cnt">(0/1000)</span>
					<button type="button" class="btn-black btn-submit"><spring:message code='BTN.COM.0001'/></button>
				</div>

				<%-- 댓글 있는 경우만 노출 --%>
				<div class="area-list">
					<ul>
						<c:choose>
						<c:when test="${empty list1}">
						</c:when>
						<c:otherwise>
							<c:forEach var="map" items="${list1}" varStatus="calCount">
								<li>
									<div class='sub_search_js sp-info'>${map.user_nm}&nbsp;${map.reg_dt}
										<c:if test="${map.YN =='YES' || role_id =='1'}">
											<button type="button" class="btn-basic btn-xs btn-delete" data-comment_no="${map.comment_no }"><spring:message code='BTN.COM.0003'/></button>
										</c:if>
									</div>
									<div class='data_div sp-text' ><c:out value="${map.comment_cont}"/></div>
								</li>	
							</c:forEach>
						</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
			<div class="grid-bottom">
				<div class="put-right">
					<button type="button" class="btn-basic btn-detail-write" id="btn-detail-write"><spring:message code='BTN.COM.0024'/></button>
					<c:if test="${delete =='Y' || role_id =='1'}">
						<button type="button" class="btn-basic btn-detail-modify" id="btn-detail-modify"><spring:message code='BTN.COM.0023'/></button>
						<button type="button" class="btn-basic btn-detail-delete" id="btn-detail-delete"><spring:message code='BTN.COM.0003'/></button>
					</c:if>
					<button type="button" class="btn-basic btn-detail-list" id="btn-detail-list"><spring:message code='BTN.COM.0004'/></button>
				</div>
			</div>
		</div>
	</div><!--  / #Detailform  -->
<!-- wrap_set -->
</form>
<script src="<c:url value="/resources/app/board/goDetailAndWrite.js" />"></script>