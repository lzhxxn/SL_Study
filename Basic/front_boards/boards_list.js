'use strict';

_SL.nmspc("user").list = function() {

	var mCfg = { // Url, Id
		urlList : gCONTEXT_PATH + 'boards/boards_list.json',
		urlForm : gCONTEXT_PATH + 'boards/boards_form.html',
		formId : '#searchUserList',
		gridId : '#gridUserList'
	},

	m$ = { // jQuery 객체
		form : $(mCfg.formId),
		grid : $(mCfg.gridId)
	},

	ctrlCookie = new slui.cookies(),

	init = function() {
		ctrlCookie.init(mCfg.gridId);

		var $btnAdd = m$.grid.parent().siblings('.grid-bottom')
				.find('.btn-add');

		// 초기 화면 구성
		drawGrid(m$.grid);

		// 이벤트 설정
		m$.form.find('.form-submit').off().on('click', function() {
			m$.grid.jqxGrid('gotopage', 0);
			refresh();
		});

		// Keypress
		m$.form.find(".form-input").keypress(function(e) {

			if (e.keyCode == 13) {
				m$.grid.jqxGrid('gotopage', 0);
				refresh();
			}
		});

		$btnAdd.off().on('click', function() {
			viewDetail(mCfg.urlForm);
		});
	},

	drawGrid = function($grid) {
		var gridSource = {
			datatype : "json",
			datafields : [ {
				name : "boards_id",
				type : "int"
			}, {
				name : "boards_subject",
				type : "string"
			}, {
				name : "boards_content",
				type : "string"
			}, {
				name : "boards_writer",
				type : "string"
			}, {
				name : "boards_email",
				type : "string"
			}, {
				name : "boards_rdate",
				type : "string"
			} ],
			root : 'rows',
			beforeprocessing : function(data) {
				if (data != null) {
					gridSource.totalrecords = data.totalRows;
				}
			},

			cache : false,
			url : mCfg.urlList
		},

		dataadapter = new $.jqx.dataAdapter(gridSource, {
			beforeLoadComplete : function(rows) {
				for ( var i in rows) {
					rows[i].boards_rdate = _SL.formatLcDate(
							rows[i].boards_rdate, 'yyyyMMddHHmmss',
							_SL.formatDate.getLcPattern("ymdhms"));
				}
				return rows;
			},
			formatData : function(data) {
				var params = {}, param, flds = $(mCfg.formId).serializeArray();
				for (param in flds) {
					params[flds[param].name] = flds[param].value;
				}
				;
				$.extend(data, params);

				return data;
			},
			loadError : function(xhr, status, error) {
				alert(error);
			}
		});

		$grid
				.jqxGrid({
					keyboardnavigation : false,
					enablebrowserselection : true,
					source : dataadapter,
					width : '100%',
					pagesize : ctrlCookie.getValue(),
					virtualmode : true,
					rendergridrows : function(obj) {
						return obj.data;
					},
					columns : [
							{
								text : 'No',
								datafield : 'boards_id',
								width : 40
							},
							{
								text : 'Subject',
								datafield : 'boards_subject',
								cellsalign : 'center',
								width : '20%'
							},
							{
								text : 'Content',
								datafield : 'boards_content',
								cellsalign : 'center',
								width : '30%'
							},
							{
								text : 'Writer',
								datafield : 'boards_writer',
								width : '15%',
								cellsrenderer : function(row, column, value,
										defaulthtml, columnproperties, rowdata) {
									var img = '<img style="position: absolute; margin:-3px 0; width: 20px; height: 20px"'
									if (rowdata.user_img != ''
											&& rowdata.user_img != undefined) {
										img += ' src="' + rowdata.user_img
												+ '"'
									} else {
										img += ' src="/resources/imgs/threat/blackboard/default_user_icon.png"'
									}
									img += '>';

									$(defaulthtml).css('margin', '0px');
									return $(defaulthtml)
											.html(
													img
															+ '<button type="button" style="position: absolute; left: 27px;" class="btn-link">'
															+ value
															+ '</button>')[0].outerHTML;
								}
							}, {
								text : 'Email',
								datafield : 'boards_email',
								cellsalign : 'center',
								width : '16%'
							}, {
								text : 'Date',
								datafield : 'boards_rdate',
								cellsalign : 'center',
								width : '16%'
							} ]
				});

		$grid.on("cellclick", function(event) {
			if (event.args.datafield === 'boards_writer') {
				var boards_id = event.args.row.bounddata.boards_id;
				viewDetail(mCfg.urlForm + '?boards_id=' + boards_id);
			}
		});

		ctrlCookie.changeEvent();
	},

	refresh = function() {
		m$.grid.jqxGrid("updatebounddata");
	},

	viewDetail = function(url) {
		var modal = new ModalPopup(url, {
			height : 650,
			onClose : function() {
				refresh();
			}
		});
	};

	return {
		init : init
	};

}();

$(function() {
	slapp.user.list.init();

	// 개인정보마스킹
	$("#btnUserMasking").togglePage(gCONTEXT_PATH + "system/user_masking.html");

	// 비밀번호 관리
	$("#btnSettingPassword").click(function() {
		new ModalPopup(gCONTEXT_PATH + "system/password_mgr_form.html", {
			width : 700,
			height : 320,
			setScroll : true
		});
	});

	// 사용자정보연동
	$("#btnLinkUser").click(function() {
		new ModalPopup(gCONTEXT_PATH + "system/comuser_sync_form.html", {
			width : 800,
			height : 620,
			setScroll : true
		});
	});

	// 접속자현황
	$("#btnSettingConnecting").togglePage(
			gCONTEXT_PATH + "system/login_user_list.html");

	// 권한설정
	$("#btnSettingAuth").click(function() {
		new ModalPopup(gCONTEXT_PATH + "system/auth_form.html", {
			width : 400,
			height : 580,
			setScroll : true
		});
	});
});