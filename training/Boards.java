//Controller
@RequestMapping(value = "boards_list.json")
	public @ResponseBody GridResultEntity getList(HttpSession session, @RequestParam Map<String,Object> map) {
		BoardsDAO dao = sqlSession.getMapper(BoardsDAO.class);
		
		int listCount = dao.selectBoardsListCount(map);
		
		List<Map<String, Object>> list = Collections.emptyList();
		if (listCount > 0) {
			list = dao.selectBoardsList(PagingUtil.convertParam(map));
			
		}
		System.out.println("list : " + list);
		return new GridResultEntity(listCount, list);
	}

//MyBatis
<select id="selectBoardsList" resultType="map">
		SELECT
			boards_id, 
			boards_subject,
			boards_content,
			boards_writer,
			boards_email,
			boards_rdate
		FROM 
			BOARDS
		<where>
			<if test="s_boards_subject != null and s_boards_subject != ''">
			AND boards_subject LIKE CONCAT('%',#{s_boards_subject},'%')
			</if>
			<if test="s_boards_writer != null and s_boards_writer != ''">
			AND boards_writer LIKE CONCAT('%',#{s_boards_writer},'%')
			</if>
		</where>
		ORDER BY boards_id
		<choose>
			<when test="recordstartindex==null">
				LIMIT 0, 10
			</when>
			<otherwise>
				LIMIT #{recordstartindex}, #{pagesize}
			</otherwise>
		</choose>
	</select>

//boards_list.js
'use strict';

_SL.nmspc("user").list = function() {

	var mCfg = {
		urlList : gCONTEXT_PATH + 'boards/boards_list.json',
		urlForm : gCONTEXT_PATH + 'boards/boards_form.html',
		formId : '#searchUserList',
		gridId : '#gridUserList'
	},

	m$ = {
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
        ...
	}
}

//boards_form.js
_SL.nmspc("user").form = function(){

	var
	// Config 정의
	mCfg = {
		formId : '#formComuser',
		urlSelect : gCONTEXT_PATH + "boards/boards.json",
		urlExist : gCONTEXT_PATH + "boards/boards_exist.json",
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
        ...
	}
}

// Validation
<input type=text name.. data-valid="<spring:message code="FLD.COM.0148"/>,required,alphanum"></td>
..


