
###  🐬 회사 Framework 기반 게시판 만들기 MEMO
1. SVN 
- trunk : Main 개발서버 ( 모듈 별 )
- branches : 각 Project 개발 등 
```
 web - checkout 내려받기 
```

2. SecureFx 서버자원 -> Eclipse Connect
root - www/ROOT/WEB-INF/conf -> Overwrite All

3. Web Secure -> IC Licence Request -> 발급 ,  MAC 주소 (물리적 주소) ipconfig -all

4. 사용자 관리 - MVC CRUD
- 컬럼 추가
- xxx_update.do ( update ) 데이터 수정
- xxx_insert.do ( add ) 데이터 추가
- xxx_list.json 데이터 추출
- xxx_delete.do 데이터 삭제
```
 xx.json (data 주고 받을 때 사용) , xxx.do ( add / update )
```

5. 단축키 
- cursor + f3 (svc && dao 등 바로 이동한다)
- ctrl + h (file 내용으로 Search)
- ctrl + shift + r (file 이름으로 Search)

6. parameterType="map"
- id : 123
- => #{id} : 123 (key : value)

7. [Front] JSP - JS - [Back] Controller - Service - DAO - Mapper.xml





### _alert(context,options)
***
```
_alert("안녕하세요");

_alert("안녕하세요", {
	title : 'Hi',
	textConfirm : '네',
	onAgree : function(){
		// somethine do...
	}
});

```


### _confirm(context,options)
***
```
_confirm("수정하시겠습니까?", {
	title : '수정확인',
	textConfirm : '네',
	textCancel : '아니오',
	onAgree : function(){
		// somethine do...
	},
	onDisagree : function(){
		// somethine do...
	}
});
```



### $element.requestData(url, requestData, options)
***
```
$('body').requestData('dashboard_list.json'
, {dashboard_id : 123}
, {callback : funtion(){
		// something do...
});

// $element (jQuery object) 데이터 통신시 loader 표시할 객체, 주로 body 사용
// url (String) 요청 url 필수 값
// requestData (Object) 요청 데이터
// -----Options-----
// 타입, 싱크, 콜백, ..
```



### $element.togglePage(url, options)
***
```
$("#btn1").togglePage("./login_user_list.jsp");

$("#btn2").togglePage("./login_user_list.jsp", {
		onOpen : function(){
			// do something..
		},
		onClose : function(){
			// do something..
		}
});

// $element (jQuery object) 이벤트 핸들 버튼
// url (String) 요청 url 필수 값
// -----Options-----
// effect(selide | show), speed, ..
```



### ModalPopup
***
```
var modal = new ModalPopup('comcode_form.jsp', {
		width : 450,
		height : 440,
		draggable : true,
		onClose : function(){
			//do something..
		}
});

```

### $element.exModalPopup(url, options)
***
```
$('btn1').exModalPopup('comcode_form.jsp');

$('btn2').exModalPopup('comcode_form.jsp', {
		width : 600,
		height: 400,
		onClose : function(){
			//do something..
		}
});

// $element (jQuery object) 이벤트 핸들 버튼
// url (String) 요청 url 필수 값
// -----Options-----
// 모달 팝업 가로/세로 사이즈, draggable 여부, 레이어 열리고 실행되는 콜백 함수, 레이어 저장 후 닫히면 실행되는 콜백 함수, ..
```



### $element.addModalPage(url, options)
***
```
$('#btn1').addModalPage('comcode_form.jsp');

$('#btn2').addModalPage('comcode_form.jsp',{
		onLoad : function(){
			// do something..
		},
		onUnload : function(){
			// do something..
		}
});

// $element (jQuery object) 이벤트 핸들 버튼
// url (String) 요청 url 필수 값
// -----Options-----
// on(Un)Load(페이지 load되고 실행되는 콜백 함수), 통신 방식, ..
```


### loading
***
```
$element (jQuery object - Type) ($('body') - Default) loading UI가 표시될 요소
loading.show($element);
loading.hide($element);
```
