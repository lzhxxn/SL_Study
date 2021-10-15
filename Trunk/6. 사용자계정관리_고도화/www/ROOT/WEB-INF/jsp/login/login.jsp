<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript" src = "<c:url value="/resources/js/jsbn.js" />"></script>
<script type="text/javascript" src = "<c:url value="/resources/js/rsa.js" />"></script>
<script type="text/javascript" src = "<c:url value="/resources/js/prng4.js" />"></script>
<script type="text/javascript" src = "<c:url value="/resources/js/rng.js" />"></script>

 <style type="text/css">
  .btn-idcheck {
    top: 521px;
	left: 1151px;
	width: 63px;
	height: 19px;
	text-align: left;
	font: normal normal normal 13px/19px Noto Sans KR;
	letter-spacing: 0px;
	color: #666666;
	opacity: 1;
  }
  .btn-pwdcheck {
	top: 521px;
	left: 1235px;
	width: 75px;
	height: 19px;
	text-align: left;
	font: normal normal normal 13px/19px Noto Sans KR;
	letter-spacing: 0px;
	color: #666666;
	opacity: 1;
  }
  .id|pwd {
	top: 0px;
	left: 0px;
	width: 1920px;
	height: 1080px;
	background: #F4F4F4 0% 0% no-repeat padding-box;
	opacity: 1;
  }
  .btn-apply {
	top: 19px;
	left: 440px;
	width: 48px;
	height: 19px;
	text-align: left;
	font: normal normal normal 13px/19px Noto Sans KR;
	letter-spacing: 0px;
	color: #BBBBBB;
	opacity: 1;  
  }
  .user-apply {
	content: '';
	display: inline-block;
	position: absolute;
	left: 420px;
	top: 6%;
	width: 15px;
	height: 15px;
	margin-top: -7px;
	vertical-align: middle;
	background-image: url("../../resources/imgs/login/icon_apply.png");
	opacity: 1;
  }
 </style>

<!-- 로그인 -->
<div class="section-login-left"></div>
<div class="section-login">
<div class="form-apply-user"><i class="user-apply" style="position:absolute;"></i><button type="button" class="btn-apply" style="position:absolute;">계정신청</button></div>
<form name="formLogin" method="post" autocomplete="off">
	<input type="hidden" name="slKey" value="${_slKey}">
	<p class="logo">Login</p>
	<p class="logo-info">Sign in to eyeCloudXOAR</p>
	<div class="form-login text"><input type="text" name="userId" value="${param.userId != null ? fn:escapeXml(param.userId) : ''}" data-valid="ID,required" autocomplete="off" placeholder="User ID" ></div>
	<div class="form-login pw"><input type="password" id="userPswd" name="userPswd" value="<c:out value="${param.userPswd}" />" data-valid="Password,required" autocomplete="off" placeholder="Password"></div>
	<div class="form-login-check">
		<label><input type="checkbox" name="checkSave" style="margin: 0px 135px 0px 150px;">Save ID</label>
		<button type="button" class="btn-idcheck" style="margin-left: 135px;" >아이디찾기</button>
		<a class="id|pwd" style="margin-right: 5px;">|</a>
		<button type="button" class="btn-pwdcheck">비밀번호찾기</button>
	</div>
	
	<div class="form-login-submit"><button type="button" class="btn-login">Login</button></div>
	<i class="loginCapsLock" style="display:none;"></i>
	<div id="capslock" class="capslock"> 
    &nbsp;<i class="icon-notification" style="font-size: 12px;padding-left: 9px; padding-top: 9px; color: red;"></i><b style="color:red; padding-left: 3px;">&lt;CapsLock&gt;</b>이 켜져있습니다.&nbsp;
	</div>
	<!-- <div id="capslockMsg" style="display:block; margin-left: 56px; width: 147px; height: 19px; background: black;">CapsLock이 켜져있습니다.</div> -->
</form>
</div>

<script type="text/javascript" src = "<c:url value="/resources/app/login/login.js" />"></script>

