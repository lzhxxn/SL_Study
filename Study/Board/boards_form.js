//# sourceURL=comuser_form.js
'use strict';


_SL.nmspc("user").form = function(){

	var
	// Config 정의
	mCfg = {
		formId : '#formComuser',
		urlSelect : gCONTEXT_PATH + "boards/boards.json",
		//urlExist : gCONTEXT_PATH + "boards/boards_exist.json",
		urlDelete : gCONTEXT_PATH + "boards/boards_delete.do",
/*		urlFileUpload : gCONTEXT_PATH + "system/comuser_file_upload.do",
		urlPasswdExist : gCONTEXT_PATH + 'main/comuser_passwd_inform.json',*/
		add : {
			action : gCONTEXT_PATH + "boards/boards_insert.do",
			message : _SL.getMessage("CNF.COM.0006"),
			passwdValid : _SL.getMessage("FLD.COM.0152")+",required,password" 
		},
		update : {
			action : gCONTEXT_PATH + "boards/boards_update.do",
			message : _SL.getMessage("CNF.COM.0002"),
			passwdValid : _SL.getMessage("FLD.COM.0152")+",password" 
		}
	},
	
	// JQuery 객체 변수
	m$ = {
		form : $(mCfg.formId),
		userId      : $(mCfg.formId + ' [name=boards_id]'),
		userSb		: $(mCfg.formId + ' [name=boards_subject]'),
		userNm		: $(mCfg.formId + ' [name=boards_content]'),
		telNo		: $(mCfg.formId + ' [name=boards_writer]'),
		mobileNo	: $(mCfg.formId + ' [name=boards_email]'),
		
		slKeyId : $(mCfg.formId + ' [name=slKey]')
	},
	
	// 현재 상태 변수
	mState = {
		isNew : m$.userId.val() == "" ? true : false,
		mode : m$.userId.val() == "" ? mCfg.add : mCfg.update
	},
	
	// 기타 변수
	userValidator = {
			password : function (elem) {
				if ($(elem).val() == "") return;
				
				var userId = (typeof m$.form.find('[name=boards_id]').val() == "undefined") ? m$.form.find("[name='p_boards_id']").val() : m$.form.find('[name=boards_id]').val();
				var userNm = m$.boards_content.val();
				var telNo = m$.boards_writer.val();
				var mobileNo = m$.boards_email.val();
				

				$('body').requestData(mCfg.urlPasswdExist, _SL.serializeMap(m$.form), { async : false, callback : function(rsData, rsCd, rsMsg){

					if(rsData.isDup === false && mState.mode == mCfg.add){
						that.message = _SL.getMessage("WRN.USR.0009");
						that.bValid = false;
					}else if(rsData.isDup === false){
						that.message = _SL.getMessage("WRN.USR.0021");
						that.bValid = false;
					}else{
						var b_upper_case_yn = rsData.passwdInform.upper_case_yn;
						var b_lower_case_yn = rsData.passwdInform.lower_case_yn;
						var b_number_yn = rsData.passwdInform.number_yn;
						var b_special_character_yn = rsData.passwdInform.special_character_yn;
						var b_length = rsData.passwdInform.length;
						var b_continum = rsData.passwdInform.continum_yn;
						var b_contichar = rsData.passwdInform.contichar_yn;
						
						if (/[가-힣]/.test(passwd)) {
							that.message = _SL.getMessage("WRN.USR.0022");
							that.bValid = false;
							return;
						} 
						
						if(b_upper_case_yn==='Y'){
							if( !(/[A-Z]/.test(passwd)) ){
								that.message = _SL.getMessage("WRN.USR.0023");
								that.bValid = false;
								return;
							}
						}
						
						if(b_lower_case_yn==='Y'){
							if( !(/[a-z]/.test(passwd)) ){
								that.message = _SL.getMessage("WRN.USR.0024");
								that.bValid = false;
								return;
							}
						}
						
						if(b_number_yn==='Y'){
							if( !(/[\d]/.test(passwd)) ){
								that.message = _SL.getMessage("WRN.USR.0025");
								that.bValid = false;
								return;
							}
						}
						
						if(b_special_character_yn==='Y'){
							if( !(/[^A-Za-z0-9]/.test(passwd)) ){
								that.message = _SL.getMessage("WRN.USR.0026");
								that.bValid = false;
								return;
							}
						}
			
						if(b_length != 0 && passwd.length < b_length){
							that.message = _SL.getMessage("WRN.USR.0019") + b_length + _SL.getMessage("WRN.USR.0020");
							that.bValid = false;
							return;
						}

							for(var i=0; i < passwd.length; i++) {
								//var chr_pass_0 = 0;
								var chr_pass_1 = 0;
								var chr_pass_2 = 0;
				
								if(i >= 1) {
									if(isNaN(passwd.charAt(i-1)) || isNaN(passwd.charAt(i))){
										continue;
									}
//									if(!isNaN(passwd.charAt(i-2)))
//										chr_pass_0 = passwd.charCodeAt(i-2);
									if(!isNaN(passwd.charAt(i-1)))
										chr_pass_1 = passwd.charCodeAt(i-1);
									if(!isNaN(passwd.charAt(i)))
										chr_pass_2 = passwd.charCodeAt(i);
		
									//연속성(+) 카운드
									if(chr_pass_1 - chr_pass_2 == -1) {
										samePass_1++;
									}
									if(chr_pass_1 == chr_pass_2){
										samePass_1++;
									}
								}
							}
						
						
							for(var i=0; i < passwd.length; i++) {
								//var chr_pass_0=0;
								var chr_pass_1=0;
								var chr_pass_2=0;
								
								if(i >= 1) {
									if(!isNaN(passwd.charAt(i-1)) || !isNaN(passwd.charAt(i))){
										continue;
									}
//									if(isNaN(passwd.charAt(i-2)))
//										chr_pass_0 = passwd.charCodeAt(i-2);
									if(isNaN(passwd.charAt(i-1)))
										chr_pass_1 = passwd.charCodeAt(i-1);
									if(isNaN(passwd.charAt(i)))
										chr_pass_2 = passwd.charCodeAt(i);
		
									//연속성(+) 카운드
									if(chr_pass_1 - chr_pass_2 == -1) {
										charPass_1++;
									}
									
									if(chr_pass_1 == chr_pass_2){
										charPass_1++;
									}
								}
							}
						
						
						//연속성이 카운트가 0일때
						if((samePass_1 == 0 && telNo) || (charPass_1 == 0 && telNo)){
							//비밀번호체크(전화번호 동일성)
							if(arrTelNo.length > 1){
								$.each(arrTelNo, function(i, val) {
									if(passwd.indexOf(val) > -1){
										bEqPwdTel = true;
										return false;
									}
								});
							}else{
								if(passwd.indexOf(telNo) > -1){
									bEqPwdTel = true;
								}
							}
							
							//전화번호 동일성 체크 이상없을 때
							if(!bEqPwdTel && mobileNo){
								//비밀번호체크(휴대폰번호 동일성)
								if(arrMobileNo.length > 1){
									$.each(arrMobileNo, function(i, val) {
										if(passwd.indexOf(val) > -1){
											bEqPwdMobile = true;
											return false;
										}
									});
								}else{
									if(passwd.indexOf(mobileNo) > -1){
										bEqPwdMobile = true;
									}
								}
							}
						}

						if((b_continum !=0) && (samePass_1 >= b_continum)) {
							that.message = _SL.getMessage("WRN.USR.0027") + b_continum +_SL.getMessage("WRN.USR.0029");
							that.bValid = false;
							return;
						}
						if((b_contichar != 0 )&&(charPass_1 >= b_contichar)) {
							that.message = _SL.getMessage("WRN.USR.0028")+ b_contichar +_SL.getMessage("WRN.USR.0029");
							that.bValid = false;
							return;
						}
						if(bEqPwdTel) {
							that.message = _SL.getMessage("WRN.USR.0030");
							that.bValid = false;
							return;
						}
						if(bEqPwdMobile) {
							that.message = _SL.getMessage("WRN.USR.0031");
							that.bValid = false;
							return;
						}
						
						
						
					}
				}});
			}	
		},
	
	init = function(){
		
		// 이벤트 Binding
		bindEvent();
		
		// DOM 설정 Start
		if(mState.isNew) {
			m$.form.find(".btn-delete").hide();
		} // form의 하위요소중' btn-delete'를 숨겨라!
		else {
			m$.userId.addClass("form-text").attr("readonly", "readonly");
			
			if(m$.userId.val() == gSessionUserId) m$.form.find(".btn-delete").hide();
		}
		
		m$.form.find("[name=passwd]").attr("data-valid", mState.mode.passwdValid);
		
		if(gUserIpRestrictYn != 'Y') {
			if(m$.form.find('[name=auth_ip1]').val() == "" && m$.form.find('[name=auth_ip2]').val() == "")
				m$.form.find('[name=auth_ip_opt][value=0]').trigger("click");
			else 
				m$.form.find('[name=auth_ip_opt][value=1]').trigger("click");
		}
		// DOM 설정 End
		
		$.extend(true, _SL.validate.Validator, userValidator);
		
		// 데이타 조회
		if(!mState.isNew) select();
		// 만약 !mState.isNew "" ? false 를 반환하다면 select(); 함수를 실행시켜주세요.
		
		m$.form.find(".form-input").keypress(function(e) {
			if (e.keyCode == 13) {
				onSave();
			}
		});
	},
	
	bindEvent = function() {
		// SAVE
		m$.form.find('.btn-save').on('click', onSave);
		
		// DELETE
		m$.form.find('.btn-delete').on("click", onDelete);		
		
		// 기관 add
		m$.form.find("#cust_add").click(function(){			
			var val = m$.form.find("[name=user_cust_list] option:selected").val();
			var text = m$.form.find("[name=user_cust_list] option:selected").text();

			if(val == "") {
				_alert(_SL.getMessage("WRN.COM.0038"));
				return;
			}
			
			if( m$.form.find("[name=cust_list] option").is(
				function() {
					return this.value == val;
				})
			){
				_alert(_SL.getMessage("WRN.COM.0039"));
				return;
			}
			
			m$.form.find("[name=cust_list]").append($("<option>").val(val).text(text));	
		});
		
		// 기관 del
		m$.form.find("#cust_del").click(function(){
			m$.form.find("[name=cust_list] :selected").remove();
		});
		
		
		// 접근 IP opt
		if(gUserIpRestrictYn != 'Y') {
			m$.form.find("[name=auth_ip_opt]").click(function() {
				var v = $(this).val();
				
				if($(this).val() == "0") 
					m$.form.find('[data-name=auth_ip_wrapper]').hide();
				else 
					m$.form.find('[data-name=auth_ip_wrapper]').show();
			});
		}
		
		// 파일 추가
		m$.form.find('#fileUpload').click(function() {
			m$.form.find("[name=file_upload]").click();
		});
		
		m$.form.find("[name=file_upload]").on("change", previewImage);
	},
	
	select = function() {
		var
			id = m$.userId.val(),
			rqData = {'boards_id': id},
	
			callback = function(data){
				data.passwd = "";
				if(data.auth_ip && data.auth_ip != "") {
					var ips = data.auth_ip.split(",");
					for(var i = 0, l = ips.length; i < l; i++) {
						data["auth_ip" + (i+1)] = ips[i];
					}
				}
				
				$('#fileUpload').css('border','0px')
				if(data.user_img != '' && data.user_img != undefined) {
					$('#fileUpload').append('<img name="user_img" src="'+data.user_img+'" style="width: 140px; height: 165px; padding: 5px 0 0 5px;">')
				}else{
					$('#fileUpload').append('<img name="user_img" src="\\resources\\imgs\\threat\\blackboard\\default_user_icon.png" style="width: 140px; height: 165px; padding: 5px 0 0 5px;">')
				}
				$('.user-imgupload-text').remove()
//				} else {
//					$('#fileUpload').css('border','2px dashed #bbb').append('<div class="img_placeholder">사진을 추가해 주세요.</div>');
//				}
				
				_SL.setDataToForm(data, m$.form, {
					"cust_list" : {
						//field		: "cust_list",
						converter	: function(cvData, $fld) {
							_SL.appendToSelect(cvData, $fld, "cust_id", "cust_nm");
						}
					},
					"auth_ip" : {
						field	: "auth_ip1",
						converter	: function(cvData, $fld) {
							if(cvData != "") m$.form.find('[name=auth_ip_opt][value=1]').trigger("click");
						}
					}
				});

				slui.attach.setTransformSelect(mCfg.formId);
			};
			$('body').requestData(mCfg.urlSelect, rqData, {callback : callback});
	},
	
	onSave = function(){
		var afterClose = $(this).data('after-close') == true ? true : false;
		
/*		//step1 폼 데이터 벨리데이션 하기
		var pwd = m$.form.find("[name=passwd]").val();
		var rePwd = m$.form.find("[name=passwd2]").val();
		
		if(pwd != rePwd) {
			if(rePwd != "" && pwd == "") {
				_alert(_SL.getMessage("WRN.USR.0005"));
				m$.form.find("[name=passwd]").focus();
				return;
			} else if(pwd != "" && rePwd == "") {
				_alert(_SL.getMessage("WRN.USR.0006"));
				m$.form.find("[name=passwd2]").focus();
				return;
			}  else{
				_alert(_SL.getMessage("WRN.USR.0007"));
				m$.form.find("[name=passwd2]").val("").focus();
				return;
			}
		}
		
		if(m$.form.find("[name=auth_ip_opt][value=0]").is(":checked")) {
			m$.form.find("[name=auth_ip1]").val("");
			m$.form.find("[name=auth_ip2]").val("");
		}*/

		if (!_SL.validate()) return;
		
		/*if(!m$.form.find("[name=auth_ip_opt][value=0]").is(":checked")) {
			var authIp1 = m$.form.find("[name=auth_ip1]").val();
			var authIp2 = m$.form.find("[name=auth_ip2]").val();

			if(!authIp1 && !authIp2) {
				_alert(_SL.getMessage("WRN.USR.0008"));
				m$.form.find("[name=auth_ip1]").focus();
				
				return;
			}else if(authIp1 == "255.255.255.255" || authIp2 == "255.255.255.255"){
				_alert(_SL.getMessage("WRN.USR.0012"));
				if(authIp1 == "255.255.255.255") m$.form.find("[name=auth_ip1]").focus();
				else m$.form.find("[name=auth_ip2]").focus();
				
				return;
			}
		}*/
		
		var submit = function(){
			$('body').requestData(mState.mode.action, _SL.serializeMap(m$.form), {
				displayLoader : true,
				callback : function(rsData, rsCd, rsMsg){
					imageUpload()
					
					_alert(rsMsg, {
						onAgree : function(){
							onClose(true);
						}
					});
				}
			});
		}
		
		if(mState.isNew) {
			$('body').requestData(mCfg.urlExist, {boards_id:m$.userId.val()}, {
				callback : function(rsData){
					if(rsData == true)
						submit();
					else
						_alert(_SL.getMessage("WRN.USR.0009"));
				}
			});
		}
		else {
			_confirm(mState.mode.message,{
				onAgree : function(){
					submit();
				}
			});
		}
	},

	onDelete = function(){
		var afterClose = $(this).data('after-close') == true ? true : false;
		
		_confirm(_SL.getMessage("CNF.COM.0003"),{
			onAgree : function(){
				var userId = m$.userId.val();

				//삭제되는 아이디 값 전송
				$('body').requestData(mCfg.urlDelete, {boards_id: userId, slKey:m$.slKeyId.val()},
					{callback: function(rsData, rsCd, rsMsg){
						_alert(rsMsg, {
							onAgree : function() {
								onClose(afterClose);
							}
						});
					}}
				);
			}
		});
	},
	
	previewImage = function(e){
		var files = e.target.files[0];
		var file_length = files.name.length
		var $fileCheck = m$.form.find('[name=file_upload]');
		
		if(files.name.length > 100){
			_alert('파일명이 너무 깁니다!')
			$fileCheck.val('')
			return false;
		}
		
		if(!files.type.match("image.*")){
			_alert('이미지만 가능합니다!');
			$fileCheck.val('')
			return false;
		};
		
		var max_size = 1 * 1024 * 1024;
		if (files.size > max_size) {
			_alert('이미지는 1MB까지 업로드가 가능합니다.');
			$input_file.val('');
			return false;
		};
		
		var reader = new FileReader();
		reader.readAsDataURL(files);		
		
		reader.onload = function(){
			$('#fileUpload').empty();
			$('#fileUpload').css('border','0px');
			$('#fileUpload').append('<img name="user_img" src="'+reader.result+'" style="width: 140px; height: 165px; padding: 5px 0 0 5px;">');
		};
	},
	
	imageUpload = function(){
		m$.form.attr({
			enctype : 'multipart/form-data', 
			method : 'post',
			action : mCfg.urlFileUpload
		});
		
		m$.form.ajaxSubmit();
	},
	
	onClose = function(afterClose){
		if(afterClose){
			m$.form.find("[data-layer-close=true]").click();
		}
	};

	return {
		init: init
	};

}();

$(function(){
	slapp.user.form.init();
});