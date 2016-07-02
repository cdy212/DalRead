package com.dalnimsoft.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@Autowired
	SqlSession sqlSession; 
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/test2222")
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
