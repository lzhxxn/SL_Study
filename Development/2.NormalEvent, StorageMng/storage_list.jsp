<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/resources/js/jq_plugin/jquery.form.js" />"></script>
<script>
	var gProcessingPath = "${processingPath}";
	var gstoragePath = "${storagePath}";
</script>
<div class="section-content no-search-cols">
	<div class="text-info align-right" style="margin-bottom: 5px;">
		<spring:message code='WRN.STOR.0006'/>
	</div> 
	<form id="searchStorageList">
	<table class="table-group" style="height: 350px;">
	<tr>
	<td><div id="storageTree" class="tree-container" style="height: 100%;"></div></td>
	</tr>
	</table>
 	<div class="table-bottom">
		<button type="button" class="btn-basic btn-modal-add" data-after-close="true"><spring:message code='BTN.COM.0017'/></button>
		<button type="button" class="btn-basic btn-modal-cancel" data-layer-close="true"><spring:message code='BTN.COM.0014'/></button>
	</div>
	</form>
</div>

<!--  jqxWindow -->
<div id="storage_dlg" style="display: none;">
	<div><spring:message code='TITLE.STOR.0001'/></div>
	<div>
		<form id="formStorage">
			<input type="hidden" name="slKey" value="${_slKey}">
			<input type="hidden" name="default_yn" value="">
			<input type="hidden" name="divide_keyword" value="">
			<table class="table-group">
				<tr>
					<th scope="row" data-ui="tooltip" data-text="storageId"><span class="mark-required"><spring:message code='FLD.STOR.0001'/></span></th>
					<td data-ui="tooltip" data-text="<spring:message code='INF.STOR.0001'/>">
						<input type="text" name="storage_id" class="form-input" data-valid="<spring:message code='FLD.STOR.0001'/>,required" maxlength="50">
					</td>
				</tr>
				<tr>
					<th scope="row" data-ui="tooltip" data-text="storageName"><span class="mark-required"><spring:message code='FLD.STOR.0002'/></span></th>
					<td data-ui="tooltip" data-text="<spring:message code='INF.STOR.0002' htmlEscape="true"/>">
						<input type="text" name="storage_nm" class="form-input" data-valid="<spring:message code='FLD.STOR.0002'/>,required" maxlength="50">
					</td>
				</tr>
				<tr>
					<th scope="row"><span class="mark-required"><spring:message code='FLD.STOR.0015'/></span></th>
					<td>
						<div class="ranges-group">
							<div class="range-4 form-label"><label data-ui="tooltip" data-text="<spring:message code='INF.STOR.0013'/>"><input type="radio" name="divide_yn" class="form-transform" value="Y"><span class='form-clone'></span>&nbsp;True</label></div>
							<div class="range-4 form-label"><label data-ui="tooltip" data-text="<spring:message code='INF.STOR.0013'/>"><input type="radio" name="divide_yn" class="form-transform" value="N" checked="checked"><span class='form-clone'></span>&nbsp;False</label></div>
						</div>
					</td>
				</tr>
				<tr class="tr-grp-divide">
					<th scope="row"><span class="mark-required"><spring:message code='FLD.STOR.0016'/></span></th>
					<td>
						<div class="ranges-group">
							<div class="range-4">
								<select name="divide_keyword_key" class="form-select" data-valid="<spring:message code='FLD.STOR.0016'/>,required" data-placeholder="<spring:message code='SEL.COM.0004'/>">
									<option value=""><spring:message code='SEL.COM.0004'/></option>
									<c:forEach var="map" items="${fields}">
									<option value="${map.key}"><c:out value="${map.value} [${map.key}]" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="range-1 form-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</div>
							<div class="range-5">
								<input type="text" name="divide_keyword_val" class="form-input" data-valid="<spring:message code='FLD.STOR.0016'/>,required" maxlength="400">
							</div>
						</div>
					</td>
				</tr>
				<tr class="tr-grp-divide">
					<th scope="row"><span class="mark-required"><spring:message code='FLD.STOR.0017'/></span></th>
					<td>
						<div class="ranges-group">
							<div class="range-4 form-label"><label data-ui="tooltip" data-text="<spring:message code='INF.STOR.0014'/>"><input type="radio" name="org_log_backup_yn" class="form-transform" value="Y" checked="checked"><span class='form-clone'></span>&nbsp;True</label></div>
							<div class="range-4 form-label"><label data-ui="tooltip" data-text="<spring:message code='INF.STOR.0014'/>"><input type="radio" name="org_log_backup_yn" class="form-transform" value="N"><span class='form-clone'></span>&nbsp;False</label></div>
						</div>
					</td>
				</tr>
				<tr class="tr-grp-divide">
					<th scope="row"><span class="mark-required">실시간 이벤트 활용여부</span></th>
					<td>
						<div class="ranges-group">
							<div class="range-4 form-label"><label data-ui="tooltip" data-text="분류한 로그를 실시간 이벤트에 활용할지 여부"><input type="radio" name="use_event_yn" class="form-transform" value="Y" checked="checked"><span class='form-clone'></span>&nbsp;True</label></div>
							<div class="range-4 form-label"><label data-ui="tooltip" data-text="분류한 로그를 실시간 이벤트에 활용할지 여부"><input type="radio" name="use_event_yn" class="form-transform" value="N"><span class='form-clone'></span>&nbsp;False</label></div>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row" data-ui="tooltip" data-text="srcPath"><span class="mark-required"><spring:message code='FLD.STOR.0003'/></span></th>
					<td data-ui="tooltip" data-text="<spring:message code='INF.STOR.0003'/>">
						<div class="ranges-group">
							<div class="range-6 form-label align-right"><c:out value='${processingPath}' /></div>
							<div class="range-4">
								<input type="text" name="src_path" class="form-input input-path" maxlength="150" data-valid="<spring:message code='FLD.STOR.0003'/>,required" data-name="<spring:message code='FLD.STOR.0003'/>">
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row" data-ui="tooltip" data-text="indexPath"><span class="mark-required"><spring:message code='FLD.STOR.0004'/>&nbsp;&nbsp;&nbsp;</span></th>
					<td class="td-index-path">
						<div class="ranges-group">
							<div class="range-6 form-label align-right"><c:out value='${storagePath}' /></div>
							<div class="range-4">
								<input type="text" name="index_path" class="form-input input-path" maxlength="150" data-valid="<spring:message code='FLD.STOR.0004'/>,required" data-name="<spring:message code='FLD.STOR.0004'/>">
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row" data-ui="tooltip" data-text="useDiskManager"><span class="mark-required"><spring:message code='FLD.STOR.0005'/></span></th>
					<td class="td-use-disk-manager">
						<div class="ranges-group">
							<div class="range-4 form-label"><label data-ui="tooltip" data-text="<spring:message code='INF.STOR.0004'/>"><input type="radio" name="use_disk_manager" class="form-transform" value="Y"><span class='form-clone'></span>&nbsp;True</label></div>
							<div class="range-4 form-label"><label data-ui="tooltip" data-text="<spring:message code='INF.STOR.0005'/>"><input type="radio" name="use_disk_manager" class="form-transform" value="N" checked="checked"><span class='form-clone'></span>&nbsp;False</label></div>
						</div>
					</td>
				</tr>
				<tr class="tr-disk-manager">
					<th scope="row" data-ui="tooltip" data-text="storePath"><span class="mark-required"><spring:message code='FLD.STOR.0006'/></span></th>
					<td class="td-store-path">
						<div class="ranges-group">
							<div class="range-6 form-label align-right"><c:out value='${storagePath}' /></div>
							<div class="range-4">
								<input type="text" name="store_path" class="form-input input-path" maxlength="150" data-valid="<spring:message code='FLD.STOR.0006'/>,required" data-name="<spring:message code='FLD.STOR.0006'/>">
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row" data-ui="tooltip" data-text="categoryField"><spring:message code='FLD.STOR.0007'/></th>
					<td data-ui="tooltip" data-text="<spring:message code='INF.STOR.0006'/>">
						<div class="form-select-outer">
							<select name="category_field" class="form-select">
								<option value=""><spring:message code='SEL.COM.0002'/></option>
								<c:forEach var="map" items="${fields}">
									<option value="${map.key}">${map.value}(${map.key})</option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr class="tr-category">
					<th scope="row" data-ui="tooltip" data-text="categorys"><span class="mark-required"><spring:message code='FLD.STOR.0008'/></span></th>
					<td data-ui="tooltip" data-text="<spring:message code='INF.STOR.0007'/>">
						<input type="text" name="categorys" class="form-input" maxlength="30" data-valid="<spring:message code='FLD.STOR.0008'/>,required">
					</td>
				</tr>
				<tr>
					<th scope="row" data-ui="tooltip" data-text="partitionField"><spring:message code='FLD.STOR.0009'/></th>
					<td data-ui="tooltip" data-text="<spring:message code='INF.STOR.0008'/>">
						<div class="form-select-outer div-mylog-partition-field">
							<div class="form-label">user_id</div>
						</div>
						<div class="form-select-outer div-other-partition-field">
							<select name="partition_field" class="form-select">
								<option value=""><spring:message code='SEL.COM.0002'/></option>
								<c:forEach var="map" items="${fields}">
									<option value="${map.key}">${map.value}(${map.key})</option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr class="tr-partition-format">
					<th scope="row" data-ui="tooltip" data-text="partitionFormat"><span class="mark-required"><spring:message code='FLD.STOR.0010'/></span></th>
					<td class="td-partition-format">
						<div class="ranges-group">
							<div class="range-2 form-label"><label><input type="radio" name="partition_format" class="form-transform" value="N" checked="checked"><span class='form-clone'></span>&nbsp;<spring:message code='LBL.STOR.0001'/></label></div>
							<div class="range-2 form-label"><label><input type="radio" name="partition_format" class="form-transform" value="yyyyMM"><span class='form-clone'></span>&nbsp;yyyyMM</label></div>
							<div class="range-2 form-label"><label><input type="radio" name="partition_format" class="form-transform" value="yyyyMMdd"><span class='form-clone'></span>&nbsp;yyyyMMdd</label></div>
							<div class="range-3 form-label">&nbsp;&nbsp;&nbsp;&nbsp;<label><input type="radio" name="partition_format" class="form-transform" value="yyyyMMddHH"><span class='form-clone'></span>&nbsp;yyyyMMddHH</label></div>
						</div>
					</td>
				</tr>
				<tr class="tr-use-data-move">
					<th scope="row" data-ui="tooltip" data-text="dataMove"><span class="mark-required"><spring:message code='FLD.STOR.0011'/></span></th>
					<td class="td-use-data-move">
						<div class="ranges-group">
							<div class="range-4 form-label"><label data-ui="tooltip" data-text="<spring:message code='INF.STOR.0011'/>"><input type="radio" name="use_data_move" class="form-transform" value="Y"><span class='form-clone'></span>&nbsp;True</label></div>
							<div class="range-4 form-label"><label data-ui="tooltip" data-text="<spring:message code='INF.STOR.0012'/>"><input type="radio" name="use_data_move" class="form-transform" value="N" checked="checked"><span class='form-clone'></span>&nbsp;False</label></div>
						</div>
					</td>
				</tr>
				<tr class="tr-use-move-path">
					<th scope="row" data-ui="tooltip" data-text="dataMovePath"><span class="mark-required"><spring:message code='FLD.STOR.0012'/></span></th>
					<td class="td-move-path">
						<div class="ranges-group">
							<div class="range-6 form-label align-right"><c:out value='${storagePath}' /></div>
							<div class="range-4">
								<input type="text" name="move_path" class="form-input input-path" maxlength="150" data-valid="<spring:message code='FLD.STOR.0012'/>,required" data-name="<spring:message code='FLD.STOR.0012'/>">
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row" data-ui="tooltip" data-text="keepDays"><span class="mark-required"><spring:message code='FLD.STOR.0013'/></span></th>
					<td data-ui="tooltip" data-text="<spring:message code='INF.STOR.0009'/>">
						<input type="text" name="keep_days" class="form-input" placeholder="180" maxlength="4" data-name="<spring:message code='FLD.STOR.0013'/>,number">
					</td>
				</tr>
				<tr>
					<th scope="row" data-ui="tooltip" data-text="writerCount"><span class="mark-required"><spring:message code='FLD.STOR.0014'/></span></th>
					<td data-ui="tooltip" data-text="<spring:message code='INF.STOR.0010'/>">
						<div class="forms-group">
							<select name="writer_count" class="form-select" data-size="4">
								<option value="0">0</option>
								<option value="1" selected="selected">1</option>
								<option value="2">2</option>
								<option value="4">4</option>
								<option value="6">6</option>
								<option value="8">8</option>
								<option value="10">10</option>
								<option value="12">12</option>
								<option value="14">14</option>
								<option value="16">16</option>
							</select>
						</div>
				</tr>
			</table>
			<div class="forms-bottom">
				<button type="button" class="btn-basic btn-win-save"><spring:message code='BTN.COM.0005'/></button>
				<button type="button" class="btn-basic btn-win-delete"><spring:message code='BTN.COM.0003'/></button>
				<button type="button" class="btn-basic btn-win-cancel" data-button-cancel="true"><spring:message code='BTN.COM.0014'/></button>
			</div>
		</form>
	</div>
</div>

<script src="<c:url value="/resources/app/management/collector/storage_list.js" />"></script>