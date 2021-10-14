package com.seculayer.web.boards;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.seculayer.web.common.ComCode;
import com.seculayer.web.common.Constants;
import com.seculayer.web.common.MessageUtil;
import com.seculayer.web.common.util.MapUtil;
import com.seculayer.web.common.util.PagingUtil;
import com.seculayer.web.framework.entity.GridResultEntity;
import com.seculayer.web.framework.entity.JsonResultEntity;
import com.seculayer.web.framework.entity.ResultEntityFactory;
import com.seculayer.web.framework.util.TemplateAndPage;
import com.seculayer.web.system.dao.UserDAO;
import com.seculayer.web.boards.BoardsController;
import com.seculayer.web.boards.BoardsService;
import com.seculayer.web.boards.dao.BoardsDAO;

@Controller
@RequestMapping(value = "/boards/")
public class BoardsController {
	
	@Autowired private SqlSession sqlSession;
	@Autowired private HttpServletRequest req;
	@Autowired private BoardsService svc;
	@Autowired private ComCode comCode;
	@Autowired private Configuration config;
	@Autowired private ResultEntityFactory reFac;
	@Autowired private MessageUtil msgUtil;
	
	static Logger logger = Logger.getLogger(BoardsController.class);

	final static String Template = "/common/tpl/default_template";
	final static String PopupTemplate = "/common/tpl/popup_template";
	final static String PartialTemplate = "/common/tpl/partial_template";
	final static String VIEW_MAPPING_PATH = "/boards/";

	@RequestMapping(value = "boards_list.html")
	public ModelAndView listPage(HttpSession session, @RequestParam Map<String, Object> map) {
		
		ModelAndView mv = TemplateAndPage.getTemplateAndPage(map, VIEW_MAPPING_PATH, "boards_list", new Object[][] {
				{msgUtil.getMessage("TITLE.USR.0018"),"btnUserMasking"},
				{msgUtil.getMessage("TITLE.USR.0017"),"btnSettingPassword"},
				{msgUtil.getMessage("TITLE.USR.0011"),"btnLinkUser"},
				{msgUtil.getMessage("TITLE.USR.0002"),"btnSettingAuth", false},
				{msgUtil.getMessage("TITLE.USR.0003"),"btnSettingConnecting"},
		});
		BoardsDAO dao = sqlSession.getMapper(BoardsDAO.class);
		return mv;
	}
	
	@RequestMapping(value = "boards_form.html")
	public ModelAndView formPage(HttpSession session, @RequestParam Map<String, Object> map) {
		
		ModelAndView mv = TemplateAndPage.getTemplateAndPage(map, TemplateAndPage.PARTIAL_TEMPLATE, VIEW_MAPPING_PATH, "boards_form");
		mv.addObject("page_title", msgUtil.getMessage("TITLE.USR.0004"));
		
		BoardsDAO dao = sqlSession.getMapper(BoardsDAO.class);
		
		mv.addObject("loginMenuIds", comCode.getCodeMap("CS0044"));
		mv.addObject("userIpRestrictYn", config.getBoolean("user_ip_restrict") ? "Y" : "N");
		mv.addObject("multipleManagerRoleYn", config.getBoolean("multiple_manager_role") ? "Y" : "N");

		return mv;
	}
	
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
	
	@RequestMapping(value = "boards.json")
	public @ResponseBody JsonResultEntity get(@RequestBody Map<String, Object> map) {
		
		return reFac.getJsonResultEntity(svc.select(map));
	}
	
	/*
	 * @RequestMapping(value = "boards_exist.json") public @ResponseBody
	 * JsonResultEntity exist(@RequestBody Map<String, Object> map) {
	 * 
	 * BoardsDAO dao = sqlSession.getMapper(BoardsDAO.class);
	 * 
	 * int checkCount = dao.checkBoards(map);
	 * 
	 * return reFac.getJsonResultEntity(checkCount == 0); }
	 */
	
	
	@RequestMapping(value = "boards_insert.do")
	public @ResponseBody JsonResultEntity insert(HttpSession session, @RequestBody Map<String, Object> map) {
		
		map.put("proc_id", session.getAttribute(Constants.USER_ID));
		map.put("proc_ip", req.getRemoteAddr());

		svc.insert(map);
			
		return reFac.getJsonResultEntityFromResultCd("SUC.COM.0001");
	}

	@RequestMapping(value = "boards_update.do")
	public @ResponseBody JsonResultEntity update(HttpSession session, @RequestBody Map<String, Object> map) {
		map.put("proc_id", session.getAttribute(Constants.USER_ID));
		map.put("proc_ip", req.getRemoteAddr());
		
		svc.update(map);
		
		return reFac.getJsonResultEntityFromResultCd("SUC.COM.0002");
	}
	
	@RequestMapping(value = "boards_delete.do")
	public @ResponseBody JsonResultEntity delete(HttpSession session, @RequestBody Map<String, Object> map) {
		map.put("proc_id", session.getAttribute(Constants.USER_ID));
		map.put("proc_ip", req.getRemoteAddr());
		
		svc.delete(map);
		
		return reFac.getJsonResultEntityFromResultCd("SUC.COM.0003");
	}
	

}
