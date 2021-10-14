package com.seculayer.web.boards;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.seculayer.web.system.AuditService;
import com.seculayer.web.system.ComUserService;
import com.seculayer.web.system.dao.UserDAO;
import com.seculayer.web.common.ComCode;
import com.seculayer.web.common.MessageUtil;
import com.seculayer.web.common.util.ApplicationUtil;
import com.seculayer.web.common.util.DateTimeUtil;
import com.seculayer.web.common.util.IPUtil;
import com.seculayer.web.common.util.MapUtil;
import com.seculayer.web.common.util.StringUtil;
import com.seculayer.web.common.util.cipher.AES256;
import com.seculayer.web.boards.dao.BoardsDAO;

@Service
public class BoardsService {
	@Autowired SqlSession sqlSession;
	@Autowired Configuration config;
	@Autowired ComCode comCode;
	@Autowired AuditService auditSvc;
	@Autowired private MessageUtil msgUtil;
	
	static String LOG_ACCESS_CHECK_FIELD_KEY = "log_search.access_check_field";
	static String LOG_ACCESS_CHECK_FIELD_CHECK_VALUE = "asset_group_cd";
	static String SYNC_PROC_ID = "admin";
	static String SYNC_PROC_IP = "127.0.0.1";
	
	static String GROUP_CODE = "CS0011";		

	static Logger logger = Logger.getLogger(ComUserService.class);
	
	Map<String,Object> select(Map<String,Object> param) {
		BoardsDAO dao = sqlSession.getMapper(BoardsDAO.class);
		
		Map<String, Object> data = dao.selectBoards(param);
		
		return data;
	}
	@Transactional(rollbackFor=Exception.class)
	public int insert(Map<String, Object> param) {
		BoardsDAO dao = sqlSession.getMapper(BoardsDAO.class);
		
		int cnt = dao.insertBoards(param);
		
		// 감사정보
		Map<String,Object> auditInfo = new HashMap<String, Object>();
		auditInfo.put("user_ip", param.get("proc_ip"));
		auditInfo.put("log_cd", "1");
		auditInfo.put("user_id", param.get("proc_id"));
		auditInfo.put("log_title", msgUtil.getMessage("TITLE.USR.0008")); //"사용자 등록"
		StringBuffer remark = new StringBuffer();
		remark.append(msgUtil.getMessage("FLD.USR.0001") + "=[" + param.get("boards_id") + "], "); //아이디	
		remark.append(msgUtil.getMessage("FLD.USR.0002") + "=[" + param.get("boards_subject") + "], ");
		remark.append(msgUtil.getMessage("FLD.USR.0005") + "=[" + param.get("boards_content") + "], ");
		remark.append(msgUtil.getMessage("FLD.USR.0006") + "=[" + param.get("boards_writer") + "], ");
		remark.append(msgUtil.getMessage("FLD.USR.0007") + "=[" + param.get("boards_email") + "], ");
		remark.append(msgUtil.getMessage("FLD.USR.0008") + "=[" + param.get("board_rdate") + "], ");
		
		auditInfo.put("remark", remark.toString());
		auditSvc.insert(auditInfo);
		
		return cnt;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int update(Map<String, Object> param) {
		
	    BoardsDAO dao = sqlSession.getMapper(BoardsDAO.class);
		
		// 감사정보 (수정전 사용자정보)
		Map<String,Object> old = dao.selectBoards(param);
		
		int cnt = dao.updateBoards(param);

		
		// 감사정보
		Map<String,Object> auditInfo = new HashMap<String, Object>();
		auditInfo.put("user_ip", param.get("proc_ip"));
		auditInfo.put("log_cd", "2");
		auditInfo.put("boards_id", param.get("proc_id"));
		auditInfo.put("log_title", msgUtil.getMessage("TITLE.USR.0009")); //"사용자 수정"
		StringBuffer remark = new StringBuffer();
		remark.append(msgUtil.getMessage("FLD.USR.0002") + "=[" + old.get("boards_id") + "->" + param.get("boards_id") + "], ");
		remark.append(msgUtil.getMessage("FLD.USR.0005") + "=[" + old.get("boards_subject") + "->" + param.get("boards_subject") + "], ");
		remark.append(msgUtil.getMessage("FLD.USR.0006") + "=[" + old.get("boards_content") + "->" + param.get("boards_content") + "], ");
		remark.append(msgUtil.getMessage("FLD.USR.0007") + "=[" + old.get("boards_writer") + "->" + param.get("boards_writer") + "], ");
		remark.append(msgUtil.getMessage("FLD.USR.0008") + "=[" + old.get("boards_email") + "->" + param.get("boards_email") + "], ");
		remark.append(msgUtil.getMessage("FLD.COM.0021") + "=[" + old.get("board_rdate") + "->" + param.get("board_rdate") + "] " + msgUtil.getMessage("LBL.COM.0142") + " "); //변경
		
		auditInfo.put("remark", remark.toString());
		auditSvc.insert(auditInfo);
		
		return cnt;
	}
	
	int delete(Map<String, Object> param) {
		
		BoardsDAO dao = sqlSession.getMapper(BoardsDAO.class);
				
		return dao.deleteBoards(param);
	}
	
}
