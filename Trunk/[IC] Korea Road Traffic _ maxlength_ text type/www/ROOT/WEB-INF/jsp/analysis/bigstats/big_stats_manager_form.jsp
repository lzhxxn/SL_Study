<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- <style>
	.file-opacity{opacity:0.5;}
	.margin-right-4{margin-right:4px;}
	.margin-left-4{margin-left:4px;}
	.chosen-container{padding-bottom: 2px;padding-right: 4px;}
	.chosen-drop{width:300px !important;margin-top:2px !important; border-top:1px solid #aaa !important;}

</style> -->
<style>
 .temp-width-01{max-width:1190px !important};
</style>
<script>
	var gFieldCaptions = ${fieldCaption};
	var gOrgFieldCaptions = ${fieldCaption};
	var gNetworkList = ${networkListJson != null ? networkListJson : "[]"};
</script>

<div class="section-content no-search-cols">
	<form name="formBigStatsManager" id="formBigStatsManager">
		<input type="hidden" name="slKey" value="${_slKey}">
		<input type="hidden" name="big_code" value="${param.big_code}">
		<input type="hidden" name="schedule_id"  >
		<input type="hidden" name="page_type" value="${param.page_type}">
		<input type="hidden" name="status"  >
		<input type="hidden" name="proc_pct"  >
		<input type="hidden" name="last_code_yn"  >		
		
		<input type="hidden" name="schStartTime" value="${schStartDay}${schStartHour}${schStartMin}">
		<input type="hidden" name="schEndTime" value="${schEndDay}${schEndHour}${schEndMin}">
		
		<input type="hidden" name="b_set_type" >
		<input type="hidden" name="file_id">

		<div class="title-group">
			<h4>기본 정보</h4>
		</div>
		
		<table class="table-group">
			<tr>
				<th scope="row"><span class="mark-required">분석명</span></th>
				<td>
					<div class="ranges-group">
						<div class="range-6">
							<input type="text" name="stats_nm" class="form-input" maxlength="100" data-valid="분석명,required">
						</div>
						<div class="range-2">
							<button type="button" class="btn-basic form-button btn-import">가져오기</button>
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<th scope="row">분석주기 및 실행시간</th>
				<td>
					<div class="forms-group ranges-group">
						<div class="form-select-outer" style="float:left;width:63px;">
							<select name="mixed_gen_cycle_type" class="form-select" data-size="13">
								<option value="1,0">한번</option>
								<option value="m,10">10분</option>
								<option value="m,20">20분</option>
								<option value="m,30">30분</option>
								<option value="h,1">1시간</option>
								<option value="h,2">2시간</option>
								<option value="h,3">3시간</option>
								<option value="h,4">4시간</option>
								<option value="h,6">6시간</option>
								<option value="h,12">12시간</option>
								<option value="d,0">매일</option>
								<option value="w,0">매주</option>
								<option value="M,0">매월</option>
							</select>
						</div>
						<div class="gen_span form-select-outer" style="float:left;width:105px;margin-left:5px;">
							<select name="gen_sch" class="form-select" data-size="12">
								<option value="">[At once]</option>
								<option value="60">After 1 Hour</option>
								<option value="120">After 2 Hour</option>
								<option value="180">After 3 Hour</option>
								<option value="360">After 6 Hour</option>
								<option value="720">After 12 Hour</option>
								<option value="1440">After 24 Hour</option>
								<option value="0" ${!empty dataList ? " selected":""}>[User Define]</option>
							</select>
						</div>

						<div class="gen_cal_day_span date" style="margin-left:5px;">
							<input value="${genStartDay}" type="text" name="genStartDay" class="form-input align-center" readonly="readonly" data-datepicker="true" autocomplete="off">
						</div>

						<div class="gen_day_span time form-select-outer" style="width:64px;margin-left:5px;">
							<select name="genStartDay2" class="form-select" data-size="12">
		<c:forEach var="day" begin="1" end="30">
								<fmt:formatNumber value="${day}" pattern="00" var="sday" />
								<option value="${sday}">${sday}</option>
		</c:forEach>			
							</select>
							
						</div>
						<div class="gen_day_span" style="float:left; margin-top:8px;">일</div>

						<div class="gen_week_span time form-select-outer" style="width:64px;margin-left:5px;">
							<select name="genStartWeek" class="form-select" data-size="12">
								<option value="2">월</option>
								<option value="3">화</option>
								<option value="4">수</option>
								<option value="5">목</option>
								<option value="6">금</option>
								<option value="7">토</option>
								<option value="1">일</option>
							</select>
						</div>
						<div class="gen_week_span" style="float:left; margin-top:8px;">요일</div>

						<div class="gen_hour_span time form-select-outer" style="width:64px;margin-left:5px;">
							<select name="genStartHour" class="form-select" data-size="12">
		<c:forEach var="hour" begin="0" end="23">
								<fmt:formatNumber value="${hour}" pattern="00" var="shour" />
								<option value="${shour}" <c:if test="${(shour == genStartHour)}"> selected </c:if>>${shour}</option>
		</c:forEach>
							</select>
						</div>
						<div class="gen_hour_span" style="float:left; margin-top:8px;">시</div>

						<div class="gen_min_span time form-select-outer" style="width:64px;margin-left:5px;">
							<select name="genStartMin" class="form-select" data-size="12">
		<c:forEach var="min" begin="0" end="59">
							<fmt:formatNumber value="${min}" pattern="00" var="smin" />
							<option value="${smin}" <c:if test="${(smin == genStartMin)}"> selected </c:if>>${smin}</option>
		</c:forEach>
							</select>
						</div>
						<div class="gen_min_span" style="float:left; margin-top:8px;">분</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<th scope="row">설명</th>
				<td>
					<div class="ranges-group">
						<div class="range-6">
						<textarea name="description" class="form-area form-block" rows="3" cols="100" class="form-area form-block"></textarea>
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
			<th scope="row">공유사용자</th>
				<td>
					<div class="ranges-group">
						<div class="range-6">
							<select name="share_user" class="form-select" data-ui="false">
								<option value="">[선택하세요]</option>
								<c:forEach var="map" items="${comUserList}" >
								<option value="${map.user_id}">${map.user_nm}[${map.user_id}]</option>
								</c:forEach>
							</select>
						</div>
						<div class="range-2">
			    			<button type="button" class="btn-basic btn-mini add_user"><i class="icon-plus"></i></button>
			    			<button type="button" class="btn-basic btn-mini del_user"><i class="icon-minus"></i></button>
						</div>
					</div>
					<div class="ranges-group">
						<div class="range-6">
							<select name="share_user_list" class="form-select" size="3" multiple></select>
						</div>
					</div>
				</td>
			</tr>
		</table>
		
		<div class="title-group">
			<h4>분석 데이터</h4>
		</div>
		
		<table class="table-group">
			<tr>
				<th scope="row" colspan="2" class="align-center">
					<span style="line-height:28px;">데이터 구조</span>
					<button type="button" class="btn-basic btn-mini btn-minus put-right btn-del-tree"><i class="icon-minus"></i></button>
					<button type="button" class="btn-basic btn-mini btn-plus put-right btn-add-tree" style="margin-right:4px;"><i class="icon-plus"></i></button>
				</th>
			</tr>
			
			<tr>
				<td colspan="2" class="dataset_tree_td" style="height:100px;">Tree</td>
			</tr>

			<tr class="set_type_tr">
				<th scope="row">데이터종류</th>
				<td>
					<div class="ranges-group">
						<div class="range-3">
							<div class="form-select-outer">
								<select name="set_type" class="form-select" data-valid="데이터종류,required">
									<option value="S">검색</option>
									<option value="F">파일</option>
									<option value="D">DB</option>
								</select>
							</div>
						</div>
					</div>
				</td>
			</tr>
 			
<c:if test="${fn:length(networkList) > 1}">
			<tr class="set_type_s">
				<th scope="row">검색 Network</th>
				<td colspan="2">
		<c:forEach var="item" items="${networkList}" varStatus="calCount">
					<label><input type="checkbox" name="client_group_cd" value="${item.code_id}">${item.code_name}</label>
		</c:forEach>
				</td>
			</tr>
</c:if>			
			
			<tr class="set_type_s time_set_tr">
				<th scope="row">검색기간</th>
				<td>
					<div class="ranges-group">
						<div class="form-select-outer" style="float:left;width:105px;">
							<select name="timeSet" class="form-select" data-scroll="false">
								<option value="0">[User Define]</option>
								<option value="60">last 1 Hour</option>
								<option value="360">last 6 Hour</option>
								<option value="720">last 12 Hour</option>
								<option value="1440">last 1 Day</option>
								<option value="10080">last 7 Day</option>
								<option value="21600">last 15 Day</option>
								<option value="43200">last 1 Month</option>
								<option value="129600">last 3 Month</option>
								<option value="259200">last 6 Month</option>
							</select>
						</div>
						<div style="float:left;width:74px;margin-left:5px;">
							<input value="${schStartDay}" type="text" name="schStartDay" class="form-input align-center" readonly="readonly" data-datepicker="true" autocomplete="off">
						</div>
						<div class="form-select-outer" style="float:left;width:47px;margin-left:4px;">
							<select name="schStartHour" class="form-select" data-size="12">
								<c:forEach var="hour" begin="0" end="23">
									<fmt:formatNumber value="${hour}" pattern="00" var="shour" />
									<option value="${shour}" <c:if test="${(shour == schStartHour)}"> selected </c:if>>${shour}</option>
								</c:forEach>
							</select>
						</div>
						<div style="float:left; margin-top:8px;">시</div>
						<div class="form-select-outer" style="float:left;width:47px;margin-left:5px;">
							<select name="schStartMin" class="form-select" data-size="12">
								<c:forEach var="min" begin="0" end="59">
									<fmt:formatNumber value="${min}" pattern="00" var="smin" />
									<option value="${smin}" <c:if test="${(smin == schStartMin)}"> selected </c:if>>${smin}</option>
								</c:forEach>
							</select>
						</div>
						<div style="float:left; margin-top:8px;">분 ~</div>
						<div style="float:left; width:75px; margin-left:5px;">
							<input value="${schEndDay}" type="text" name=schEndDay class="form-input form-block align-center" readonly="readonly" data-datepicker="true" autocomplete="off">
						</div>
						<div class="form-select-outer" style="float:left;width:47px;margin-left:4px;">
							<select name="schEndHour" class="form-select" data-size="12">
								<c:forEach var="hour" begin="0" end="23">
									<fmt:formatNumber value="${hour}" pattern="00" var="ehour" />
									<option value="${ehour}" <c:if test="${(ehour == schEndHour)}"> selected </c:if>>${ehour}</option>
								</c:forEach>
							</select>
						</div>
						<div style="float:left; margin-top:8px;">시</div>
						<div class="form-select-outer" style="float:left;width:47px;margin-left:5px;">
							<select name="schEndMin" class="form-select" data-size="12">
								<c:forEach var="min" begin="0" end="59">
									<fmt:formatNumber value="${min}" pattern="00" var="emin" />
									<option value="${emin}" <c:if test="${(emin == schEndMin)}"> selected </c:if>>${emin}</option>
								</c:forEach>
							</select>
						</div>
						<div style="float:left; margin-top:8px;">분</div>
					</div>
				</td>
			</tr>
			<tr class="set_type_s">
				<th scope="row">검색어</th>
				<td>
					<textarea name="sch_query" rows="7" cols="105" class="form-area form-block" data-valid="검색어,required"></textarea>
				</td>
			</tr>
			
			<tr class="set_type_f">
				<th scope="row">파일데이터명</th>
				<td>
					<div class="ranges-group">
						<div class="range-6">
							<input type="text" name="file_data_name" class="form-input" readonly="readonly" disabled="disabled" data-valid="파일,required">
						</div>
						<div class="range-2">
							<button type="button" class="btn-basic form-button btn-file-load">가져오기</button>
						</div>
					</div>
				</td>
			</tr>
			
			<tr class="set_type_f">
				<th scope="row">원본파일명</th>
				<td>
					<div class="ranges-group">
						<div class="range-6">
							<input type="text" name="org_file_name" class="form-input" readonly="readonly" disabled="disabled">
						</div>
					</div>
				</td>
			</tr>
			
			<tr class="set_type_d">
				<th scope="row">Query</th>
				<td>
					<div class="ranges-group">
						<div class="range-8">
							<textarea name="sql_query" class="form-area form-block" rows="7" cols="105" class="form-area" data-valid="Query,required"></textarea>
						</div>
						<div class="range-2">
							<button type="button" class="btn-reverse form-button btn-fld-chk"><i class="icon-file-zoom-checked"></i> 필드정보Check</button>
						</div>
					</div>
				</td>
			</tr>
			
			<tr class="join_tr">
				<th scope="row" colspan="2" class="align-center">데이터관계 설정</th>
			</tr>
			
			<tr class="join_tr">
				<th scope="row">JOIN<button type="button" class="btn-basic btn-mini btn-fld-join">AUTO</button></th>
				<td class="join_td">
					<div class="ranges-group join_div">
						<div class="range-4">
							DATA1.
							<select name="join_rel_field" data-ui="false" style="max-width:320px">
				 				<option value="*">[선택하세요]</option>
							</select>
						</div>
						<div class="range-1">
							<span class="form-select-outer">
								<select name="join_operator" class="form-select">
									<option value="IN">IN</option>
									<option value="NOT IN">NOT IN</option>
									<option value="=">INNER JOIN</option>
									<option value="=*">OUTER JOIN</option>
								</select>
							</span>
						</div>
						<div class="range-4">
							DATA2.
							<select name="join_field_nm" data-ui="false" style="max-width:320px">
								<option value="*">[선택하세요]</option>
							</select>
							<button type="button" class="btn-basic btn-mini btn-plus add_join"><i class="icon-plus"></i></button>
							<button type="button" class="btn-basic btn-mini btn-minus del_join"><i class="icon-minus"></i></button>
						</div>
					</div>
				</td>
			</tr>
		</table>
		
		<div class="title-group">
			<h4>분석 정보</h4>
		</div>

		<table class="grid-table-group">
			<thead>
				<tr>
					<th scope="col" width="210">분석항목명</th>
					<th scope="col" width="100">표시형태</th>
					<th scope="col">기준필드</th>
					<th scope="col" width="340">통계</th>
					<th scope="col" width="40"><button type="button" class="btn-basic btn-mini btn-add-item"><i class="icon-plus"></i></button></th>
				</tr>
			</thead>
			<tbody class="item_list_table">
				<tr class="item_tr">
					<td>
						<input name="item_nm" class="form-input form-block" type="text" data-valid='분석항목명,required'>
					</td>
					<td>
						<div class="form-select-outer">
							<select name="view_type" class="form-select">
								<option value="P">Pie</option>
								<option value="B">Bar</option>
								<option value="L">Line</option>
								<option value="T">TimeLine</option>
							</select>
						</div>
					</td>
					<td class="field_nm_td">
						<div class="field_tree_div ranges-group">
							<span class="range-2 form-select-outer">
								<select name="field_set_seq" class="form-select" aria-value="">
									<option value="">[선택]</option>
								</select>
							</span>
							<span class="range-4">
								<select name="field_nm" class="form-select" aria-value="" data-ui="false">
									<option value="*">[선택하세요]</option>
								</select>
							</span>
							<span class="range-2 form-select-outer">
								<select name="field_func" class="form-select">
									<option value="8">일</option>
									<option value="10">시</option>
									<option value="11">10분</option>
									<option value="12">1분</option>
								</select>
							</span>
							<span class="range-2">
								<button type="button" class="btn-basic btn-mini btn-add-field"><i class="icon-plus"></i></button><button type="button" class="btn-basic btn-mini btn-minus btn-del-field" ><i class="icon-minus"></i></button>
							</span>
						</div>
					</td>
					
					<td>
						<div class="ranges-group">
							<span class="range-2 form-select-outer">
								<select name="func" class="form-select">
									<option value="count">Count</option>
									<option value="sum">SUM</option>
									<option value="avg">AVG</option>
									<option value="max">MAX</option>
									<option value="min">MIN</option>
								</select>
							</span>
							<span class="range-3 form-select-outer func_field_span">
								<select name="func_field_set_seq" class="form-select" aria-value="">
									<option value="">[선택]</option>
								</select>
							</span>
							<span class="range-5 form-select-outer">
								<select name="func_field_nm" class="form-select" aria-value="" data-ui="false">
									<option value="*">[선택하세요]</option>
								</select>
							</span>
							<!-- <span class="func_field_span">
								<select name="func_field_set_seq" class="form-select" aria-value="" data-ui="false" style="width:75px;display:inline-block;">
									<option value="">[선택]</option>
								</select>
								<select name="func_field_nm" class="form-select" aria-value="" data-ui="false" style="width:170px;display:inline-block;">
									<option value="*">[선택하세요]</option>
								</select>
							</span> -->
						</div>
					</td>

			 		<td class="imgTd">
			 			<button type="button" class="btn-basic btn-mini btn-minus btn-del-item"><i class="icon-minus"></i></button>
			 		</td>
			 	</tr>
			</tbody>
		</table>

		<div class="table-bottom">
			<button type="button" class="btn-basic btn-save" data-after-close="true" data-type="stop" style="display:none;">정지</button>
			<button type="button" class="btn-basic btn-save" data-after-close="true" data-type="retry">재실행</button>
		
			<button type="button" class="btn-basic btn-save" data-after-close="true" data-type="save">저장</button>
			<button type="button" class="btn-basic btn-cancel" data-layer-close="true">취소</button>
		</div>
	</form>

</div>

<script src="<c:url value="/resources/app/analysis/bigstats/big_stats_manager_form.js" />"></script>