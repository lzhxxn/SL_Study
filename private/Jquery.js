/*
- 🧩 javascript의 가장 대중적인 라이브러리인 **jQuery**가 부족해서 공부하기 

### 1. jQuery로 CSS 수정하기
***
- **.css(attribute, value) 함수**를 사용하면 해당 DOM의 CSS를 변경할 수 있다.

<!DOCTYPE html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  
 </head>
 <body>
 
   <h1 class="cls" style="background-color: pink">H1 tag</h1>
   <p id="ptag">hello world!!!</p>
   
   <button type="button" name="button" id="changeSize">Bigger!</button>
   <button type="button" name="button" id="changeBg">Background change!</button>
   <button type="button" name="button" id="changeHeight">Taller!</button>
   <button type="button" name="button" id="changeWidth">More Slim!</button>
   
   <script type="text/javascript">
    $(document).ready(function() {
     $('h1.cls').click(function() {
      $(this).css("background-color", "lightgreen");
    });
    
     $('#changeSize').click(function() {
      $('p#ptag').css("font-size", "30px");
    });
    
     $('#changeBg').click(function() {
      $('p#ptag').css("background-color", "lightgreen");
    });
    
     $('#changeHeight').click(function() {
      $('p#ptag').css("height", "50px");
    });
    
     $('#changeWidth').click(function() {
      $('p#ptag').css("width", "200px");
    });
  });
  </script>
 </body>
</html>
```
- h1 태그를 클릭하면 배경화면이 바뀐다.
- 각 버튼을 누르면 정해진 css가 변경된다.

### 2. fade 액션
***
- jQuery를 사용하여 요소에 간단한 fade 액션을 줄수 있습니다.
- fade in, fade out 같은 애니메이션을 적용할 수 있습니다

```
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  </head>
  <body>

    <!-- fade in, fade out, toggle -->
    <div id="div1" style="width: 50%; height: 80px; background-color: skyblue;"></div>
    <div id="div2" style="width: 50%; height: 80px; background-color: lightgreen;"></div>
    <div id="div3" style="width: 50%; height: 80px; background-color: pink;"></div>
    
    <br>
    <button type="button" id="fadein">fade in</button>
    <button type="button" id="fadeout">fade out</button>
    <button type="button" id="fadetoggle">fade toggle</button>
    
    <script type="text/javascript">
     $(document).ready(function() {
     
     // fade in, fade out, toggle
     $('#fadein').click(function() {
      $('#div1').fadeIn(1000);
      $('#div2').fadeIn(2000);
      $('#div3').fadeIn(3000);
    });
    
    $('#fadeout').click(function() {
      $('#div1').fadeOut(1000);
      $('#div2').fadeOut(2000);
      $('#div3').fadeOut(3000);
        });

    $('#fadetoggle').click(function() {
      $('#div1').fadeToggle(1000);
      $('#div2').fadeToggle(2000);
      $('#div3').fadeToggle(3000);
    });
  });
    </script>
  </body>
</html>
 ```
 ### 3.  input 태그 foncus 액션
 ***
 - jQuery를 사용하면 input 태그에 focus 됐을 때, 행동을 정의할 수 있습니다.

```
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>input focus</title>
  </head>
  <body>
    <span>ID : </span>
    <input type="text" name="id" value="">

    <span>PWD : </span>
    <input type="password" name="pwd" value="">

    <script type="text/javascript">
      // focus 됐을 때 border와 배경화면 색을 변경합니다.
      $('input').focus(function () {
        $(this).css('border', '1px solid skyblue');
        $(this).css('background-color', 'yellow');
      });

      // focus가 out됐을 때 border와 배경화면 색을 변경합니다.
      $('input').focusout(function () {
        $(this).css('border', '1px solid red');
        $(this).css('background-color', 'white');
      });
    </script>
  </body>
</html>
```
 
