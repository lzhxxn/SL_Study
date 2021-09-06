기존 코드
{ text: _SL.getMessage("FLD.COM.0227")+"(%)", datafield: 'file_press', width:'5%', cellsalign:'center',
	cellsrenderer: function(row, columnfield, value, defaulthtml, columnproperties, rowdata){
		return $(defaulthtml).html('<div data-ui="tooltip" data-text="원본ZIP/원본파일크기">'+value.toFixed(2)+'%</div>')[0].outerHTML;
	}
},
	
=> toFixed() 메소드는 숫자의 소수점을 제한하는 메소드이다. value값이 String 형태로 넘어와서 toFixed가 실행되지않고 무한로딩이 된 상황.
   Number 형태로 형변환 해주고, 0.0은 보기에 안좋기 때문에 0일경우 toFixed(1)로 처리하는 조건을 걸어줬다.


   
   

자바스크립트 중복 코드
{ text: _SL.getMessage("FLD.COM.0227")+"(%)", datafield: 'file_press', width:'5%', cellsalign:'center',
	cellsrenderer: function(row, columnfield, value, defaulthtml, columnproperties, rowdata){
		var filePress = Number(value);
		if (filePress == 0) return $(defaulthtml).html('<div data-ui="tooltip" data-text="원본ZIP/원본파일크기">'+ filePress +'%</div>')[0].outerHTML;
		return $(defaulthtml).html('<div data-ui="tooltip" data-text="원본ZIP/원본파일크기">'+ filePress.toFixed(1) +'%</div>')[0].outerHTML;
	}
},


1> 자바스크립트 중복 제거

자바스크립트 중복 코드
{ text: _SL.getMessage("FLD.COM.0227")+"(%)", datafield: 'file_press', width:'5%', cellsalign:'center',
	cellsrenderer: function(row, columnfield, value, defaulthtml, columnproperties, rowdata){
		var i = Number(value);
		if(value == null || value ==0){
			i = Number(value);
		else{
			i = Number(value).toFixed(1);
		}
		return $(defaulthtml).html('<div data-ui="tooltip" data-text="원본ZIP/원본파일크기">'+ i +'%</div>')[0].outerHTML;
},

	
2> 자바스크립트 중복 제거 2

자바스크립트 중복 코드
{ text: _SL.getMessage("FLD.COM.0227")+"(%)", datafield: 'file_press', width:'5%', cellsalign:'center',
	cellsrenderer: function(row, columnfield, value, defaulthtml, columnproperties, rowdata){
		var i = Number(value);
		if(value != 0){
			i = i.toFixed(1);
		}
		return $(defaulthtml).html('<div data-ui="tooltip" data-text="원본ZIP/원본파일크기">'+ i +'%</div>')[0].outerHTML;
},


3> 자바스크립트 중복 제거 연습
{ text: "현재ZIP", datafield: 'cur_file_size', cellsalign:'center', width:'6%',
	cellsrenderer: function(row, columnfield, value, defaulthtml, columnproperties, rowdata){
		//var curFileSize = rowdata.cur_file_size;
		//if(curFileSize == null) return $(defaulthtml).html('<div></div>')[0].outerHTML;
		//else return $(defaulthtml).html('<div>'+ bytesToSize(curFileSize) + '</div>')[0].outerHTML;
		
		var curFileSize = rowdata.cur_file_size;
		var i = 0;
		if(curFileSize != null){
			i = bytesToSize(curFileSize);
		}
		return $(defaulthtml).html('<div>'+ i +'</div>')[0].outerHTML;
	}
},
