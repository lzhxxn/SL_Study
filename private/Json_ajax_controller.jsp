<html>
  <head>
  <meta charset="utf-8"/>
  <title>Ajax Test01</title>
  <script type="text/javascript" language="javascript"
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script type="text/javascript">
   /*
   $(document).ready(function(){
   });*/
   

// ((jQuery)) $("#seq") <= <input type="text" name="seq" id="seq"/>
// ((jQuery)) on("keyup", function(){ == keyup 할 때, function을 실행해라 !
// JSON { "name:"john" }
// JavaScript { name: "John" }


  $(function(){                                          
    //$(“#searchOk01").on("click", function(){  								 
    $("#seq").on("keyup", function(){           					
	$.ajax({																		   
	   url: "../ajax01/search01.do",
	   type: "GET",														
	   data: { seq: $("#seq").val()},
           success: function(responseData){	
	     //var jsObj = JSON.parse(responseData); 
             //JSON 데이터를 JSObj객체로 변환해주는 메서드 parse !!
             //jQuery 버젼을 올려서 필요 없음			 
             if(!responseData){
	        //alert("존재하지 않는 seq 임");
		return false;
	      }																		
	      var html= "";
	      html += "<form id='ajax'>";
              html += "번호 <input name='seq' value='"+responseData.seq+"'>";
	      html += "이름 <input name='name' value='"+responseData.name+"'>";
	      html += "주소 <input name='addr' value='"+responseData.addr+"'>";
	      html += "날짜 <input name='rdate' value='"+responseData.rdate+"'>";
	      html += "</form>";
	      $("#name").val("");
						  
	      $("#container").html(html);
	    }
	 });
      });
      
   $("#searchOk02").on("click", function(){
      $.ajax({
	url: "../ajax01/search02.do",
	type: "POST",
	data: { name: $("#name").val()},
	success: function(responseData){
         if(!responseData){
	   //alert("존재하지 않는 name 임");
	    return false;
         }
	 var html= "";
	 html += "<table border='1' width='50%'>";
	 html += "<tr>";
	 html += "<th>번호</th>";
	 html += "<th>이름</th>";
	 html += "<th>주소</th>";
	 html += "<th>날짜</th>";
	 html += "</tr>";
						
	if(responseData.length != 0){
	  for(var i=0; i<responseData.length; i++){
	      html += "<tr>";
	      html += "<td align='center'>"+responseData[i].seq+"</td>";
	      html += "<td align='center'>"+responseData[i].name+"</td>";
	      html += "<td align='center'>"+responseData[i].addr+"</td>";
	      html += "<td align='center'>"+responseData[i].rdate+"</td>";
	      html += "</tr>";
	   }
        }else{
	  html += "<tr>";
	  html += "<td colspan='4' align='center'>그런 이름을 가진 사람 없음</td>";
	  html += "</tr>";
	}
	html += "</table>";
	$("#seq").val("");
	$("#container").html(html);
        }
      });
    });
  });
 </script>
</head>
<body>
  <center>
  <h2>(방법1) response객체에 JSON문자열 담기</h2>
  번호: <input type="text" name="seq" id="seq"/>
       <input type="button" value="번호 검색" id="searchOk01"/><br/>
  이름: <input type="text" name="name" id="name"/>
       <input type="button" value="이름 검색" id="searchOk02"/><br/><br/>
       <div id="container"></div><br/><br/>
       <a href="../">인덱스</a><br/><br/>
       </center>
    </body>
</html>
