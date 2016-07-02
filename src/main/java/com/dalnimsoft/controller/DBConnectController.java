package com.dalnimsoft.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dalnimsoft.dalread.DalReadText;
import com.dalnimsoft.dalread.Language;
import com.dalnimsoft.etc.Constants;
import com.dalnimsoft.service.LoginServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DBConnectController {
	@Autowired
	SqlSession sqlSession; 
	
	@Autowired
	LoginServiceImpl loginServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(DBConnectController.class);
	
	
	@RequestMapping(value = "/login")
	public String login( Model model, HttpServletRequest request) throws ServletException {
		logger.info("sample db");
		// TODO Auto-generated method stub
		String id = request.getParameter(Constants.KEY_ID); //메일주소
		String password = request.getParameter(Constants.KEY_PASSWORD);
		String currentURL = request.getParameter(Constants.KEY_CURRENT_URL);
		try {
			HttpSession session = request.getSession();
			if (loginServiceImpl.checkLoginInfo(id, password, session)) {
//				if (checkLoginInfo(id, getMD5Password(password), session)) {
				session.setAttribute(Constants.KEY_LOGIN_ID, id);
				Integer uid = (Integer)session.getAttribute(Constants.KEY_UID); 
				Language lang = loginServiceImpl.getLangCreator(uid);		
				lang.setUID(uid); //TO CHECK : 이건 필요없을거 같은데...				
				lang.createUserDicIfNotExist();
			} else {
				session.removeAttribute(Constants.KEY_LOGIN_ID);
				session.removeAttribute(Constants.KEY_UID);
				session.removeAttribute(Constants.KEY_NAME);		
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		response.sendRedirect(currentURL);
		System.out.println("login success");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout")
	public String logout( Model model, HttpServletRequest request) throws ServletException {		
		String currentURL = request.getParameter(Constants.KEY_CURRENT_URL);
		HttpSession session = request.getSession();
		session.removeAttribute(Constants.KEY_LOGIN_ID);
		session.removeAttribute(Constants.KEY_UID);
		session.removeAttribute(Constants.KEY_NAME);
//		response.sendRedirect(currentURL);
		logger.debug("logout success");
		
		return "redirect:/";
	}
	
	


	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/sample1", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("sample db");
		List<Map> arList =sqlSession.selectList("test.select", null);
		for (Map map : arList) {
			System.out.println(map.get("WORD"));
			System.out.println(map.get("WORDORI"));
		}
		
		/**
		 * `DIC_ENG`.`WORD`,
			`DIC_ENG`.`WORDORI`,
			`DIC_ENG`.`PRONOUNCE`,
			`DIC_ENG`.`MEANING_KO`,
			`DIC_ENG`.`MEANING_CH_S`,
			`DIC_ENG`.`MEANING_CH_T`,
			`DIC_ENG`.`MEANING_JP`,
			`DIC_ENG`.`WORDLEVEL`,
			`DIC_ENG`.`KNOW`
		 */
		return "home";
	}
	
}
