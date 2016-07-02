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
import org.springframework.web.bind.annotation.ResponseBody;

import com.dalnimsoft.dalread.LangCh_S;
import com.dalnimsoft.dalread.LangCommon;
import com.dalnimsoft.dalread.LangEng;
import com.dalnimsoft.dalread.LangJP;
import com.dalnimsoft.dalread.Language;
import com.dalnimsoft.etc.Constants;

@Controller
public class MiscController {
	@Autowired
	SqlSession sqlSession; 
	
	
	private static final Logger logger = LoggerFactory.getLogger(MiscController.class);

	
	
	//현재 유저가 공부하고 있는것을 찾는다.
	private Language getLangCreator(Integer uid) {
		Language lang;
		Language langTemp = new LangCommon(uid);// new LangEng(uid);				
		String studyLang = langTemp.getStudyLang();
		
		if (studyLang.toUpperCase().equals(Constants.LANG_CH_S)) {
			lang = new LangCh_S(uid);
		} else if (studyLang.toUpperCase().equals(Constants.LANG_JP)) {
			lang = new LangJP(uid);
		} else {
			lang = new LangEng(uid);
		}
		return lang;
	}
}
