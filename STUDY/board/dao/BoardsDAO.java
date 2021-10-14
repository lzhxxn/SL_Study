package com.seculayer.web.boards.dao;

import java.util.List;
import java.util.Map;

public interface BoardsDAO {
	//------------------------------------------------------------
	//  Boards List
	//------------------------------------------------------------
	public Map<String,Object> selectBoards(Map<String,Object> map);
	
	public int insertBoards(Map<String,Object> map);
	public int updateBoards(Map<String,Object> map);
	public int deleteBoards(Map<String,Object> map);
	
	public int updateBoardsDelYn(Map<String,Object> map);
	public int selectBoardsListCount(Map<String, Object> map);
	
	public List<Map<String, Object>> selectBoardsList(Map<String, Object> convertParam);
	
	//public int checkBoards(Map<String,Object> map);
	
}
