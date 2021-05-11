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
