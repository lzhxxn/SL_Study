
�ڹٽ�ũ��Ʈ �ߺ� �ڵ�
{ text: _SL.getMessage("FLD.COM.0227")+"(%)", datafield: 'file_press', width:'5%', cellsalign:'center',
	cellsrenderer: function(row, columnfield, value, defaulthtml, columnproperties, rowdata){
		var filePress = Number(value);
		if (filePress == 0) return $(defaulthtml).html('<div data-ui="tooltip" data-text="����ZIP/��������ũ��">'+ filePress +'%</div>')[0].outerHTML;
		return $(defaulthtml).html('<div data-ui="tooltip" data-text="����ZIP/��������ũ��">'+ filePress.toFixed(1) +'%</div>')[0].outerHTML;
	}
},


1> �ڹٽ�ũ��Ʈ �ߺ� ����

�ڹٽ�ũ��Ʈ �ߺ� �ڵ�
{ text: _SL.getMessage("FLD.COM.0227")+"(%)", datafield: 'file_press', width:'5%', cellsalign:'center',
	cellsrenderer: function(row, columnfield, value, defaulthtml, columnproperties, rowdata){
		var i = Number(value);
		if(value == null || value ==0){
			i = Number(value);
		else{
			i = Number(value).toFixed(1);
		}
		return $(defaulthtml).html('<div data-ui="tooltip" data-text="����ZIP/��������ũ��">'+ i +'%</div>')[0].outerHTML;
},

	
2> �ڹٽ�ũ��Ʈ �ߺ� ���� 2

�ڹٽ�ũ��Ʈ �ߺ� �ڵ�
{ text: _SL.getMessage("FLD.COM.0227")+"(%)", datafield: 'file_press', width:'5%', cellsalign:'center',
	cellsrenderer: function(row, columnfield, value, defaulthtml, columnproperties, rowdata){
		var i = Number(value);
		if(value != 0){
			i = i.toFixed(1);
		}
		return $(defaulthtml).html('<div data-ui="tooltip" data-text="����ZIP/��������ũ��">'+ i +'%</div>')[0].outerHTML;
},


3> �ڹٽ�ũ��Ʈ �ߺ� ���� ����
{ text: "����ZIP", datafield: 'cur_file_size', cellsalign:'center', width:'6%',
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