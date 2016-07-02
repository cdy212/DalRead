package com.dalnimsoft.dalread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dalnimsoft.etc.Constants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//@Controller
@RestController
public class WordDic {
	private static final Logger logger = LoggerFactory.getLogger(WordDic.class);
	
	Language lang1;	
	
	@ResponseBody
	@RequestMapping("/updateWordLevel.ajax")
	public int updateWordLevel(Integer uid, Integer wordLevel) {
		Language lang = getLangCreator(uid);	
//		if (uid == null) {
//			uid = 0;
//		}		
		lang.deleteWordLevel();
		lang.setWordLevel(wordLevel);
		return lang.countOfKnownWord();
	}
	

//	@ResponseBody
	@RequestMapping("/test2")
	public int test2(Integer uid, Integer wordLevel) throws Exception {		
		List<String> stockList = new ArrayList<String>();
		stockList.add("Fan");
		stockList.add("dexter");
		stockList.add("abc");
		stockList.add("fruit");
		stockList.add("banana");
		stockList.add("bananais apple");
		

		String[] arr = new String[stockList.size()];
		arr = stockList.toArray(arr);
		
		MyStr myStr = new MyStr();
		myStr.sortByLen(arr);

        logger.info("The strings in the sorted order of length are: ");
        for (String item : arr) {
        	logger.info("item : " + item);
            
        }
   
//		Language lang = new LangEng(uid);	
////		if (uid == null) {
////			uid = 0;
////		}
//		
//		lang.test();
		return 222;
	}
	

	
	@RequestMapping("/getWordList")
	public JSONArray getWordList(Integer uid, String wordListType) {
		Language lang = getLangCreator(uid);				
				
		if (wordListType.equals("1")) {
			wordListType = Constants.WORDLISTTYPE_UNKNOWN;		
		} else if (wordListType.equals("2")) {
			wordListType = Constants.WORDLISTTYPE_KNOWN;
		} else {
			wordListType = Constants.WORDLISTTYPE_BOOKMARK;
		}  			
//		JSONArray jsonArrayWordFamily = lang.getWordFamily(word.toLowerCase());
//		logger.info("jsonArrayWordFamily : " + jsonArrayWordFamily);
		//QUESTION : jsp의 본문에서는 이걸 어떻게 받나? jQuery에서는 또 어떻게 받나? Alert도 안됨.
		return lang.getWordList(wordListType);

	}
    
	@RequestMapping("/getWordFamily")
	public JSONArray getWordFamily(Integer uid, String word) {
		Language lang = getLangCreator(uid);				
				
//		JSONArray jsonArrayWordFamily = lang.getWordFamily(word.toLowerCase());
//		logger.info("jsonArrayWordFamily : " + jsonArrayWordFamily);
		//QUESTION : jsp의 본문에서는 이걸 어떻게 받나? jQuery에서는 또 어떻게 받나? Alert도 안됨.
		return lang.getWordFamily(word.toLowerCase());

	}
	
	@ResponseBody
	@RequestMapping("/blnBookmarkedWord")
	public boolean blnBookmarkedWord(Integer uid, String word) {
		Language lang = getLangCreator(uid);
		
		return lang.blnBookmarkedWord(word);
	}
		
	@RequestMapping("/addToBookmark")
	public void addToBookmark(Integer uid, String word) {
		Language lang = getLangCreator(uid);				
		lang.addToBookmark(word);
		return;
	}

	@RequestMapping("/deleteFromBookmark")
	public void deleteFromBookmark(Integer uid, String word) {
		Language lang = getLangCreator(uid);				
		lang.deleteFromBookmark(word);
		return;
	}
	
	//뜻 표시하는 언어와 공부하는 언어는 설정.
	@ResponseBody
	@RequestMapping("/getDispMeaningLang")
	public String getDispMeaningLang(Integer uid) {
		Language lang = getLangCreator(uid);
		return lang.getDispMeaningLangFromTbl().toUpperCase();
	}
	@RequestMapping("/setDispMeaningLang")
	public void setDispMeaningLang(Integer uid, String dispMeaningLang) {
		Language lang = getLangCreator(uid);
		lang.setDispMeaningLangToTbl(dispMeaningLang);
	}

	@ResponseBody
	@RequestMapping("/getStudyLang")
	public String getStudyLang(Integer uid) {
		Language lang = getLangCreator(uid);
		return lang.getStudyLang().toUpperCase();
	}
	@RequestMapping("/setStudyLang")
	public void setStudyLang(Integer uid, String studyLang) {
		//공부하는 언어를 변경하는 경우는 서버 DB에서 공부어를 가져오지 않고 설정할 언어로 Language클래스를 만든다.		
		Language lang;
		if (studyLang.toUpperCase().equals(Constants.LANG_CH_S)) {
			lang = new LangCh_S(uid);
		} else if (studyLang.toUpperCase().equals(Constants.LANG_JP)) {
			lang = new LangJP(uid);
		} else {
			lang = new LangEng(uid);
		}
		lang.setStudyLangToTbl(studyLang);
	}
	
//	@RequestMapping("/createUserDic")
//	public void createUserDic(Integer uid) {
//		Language lang = new LangEng();			
//		if (uid == null) {
//			uid = 0;
//		}
//		lang.setUID(uid);				
//		lang.createUserDicIfNotExist();
//		return;
//	}
	
//	@RequestMapping("/deleteWordLevel")
//	public void deleteWordLevel() {
//		Language lang = new LangEng();		
//		lang.deleteWordLevel();		
//	}
	
	@ResponseBody
	@RequestMapping("/countOfBookmarkWord.ajax")
	public String countOfBookmarkWord(Integer uid) {
		Language lang = getLangCreator(uid);		
		return String.valueOf(lang.countOfBookmarkWord());		
	}
	
	@ResponseBody
	@RequestMapping("/countOfUnknownWord.ajax")
	public String countOfUnknownWord(Integer uid) {
		Language lang = getLangCreator(uid);		
		return String.valueOf(lang.countOfUnknownWord());		
	}
	
	@ResponseBody
	@RequestMapping("/countOfKnownWord.ajax")
	public String countOfKnownWord(Integer uid) {
		Language lang = getLangCreator(uid);		
		return String.valueOf(lang.countOfKnownWord());		
	}

	@RequestMapping("/setWordKnown.ajax")
	public void setWordKnown(Integer uid, String word) {
		Language lang = getLangCreator(uid);				
		lang.setWordToKnownOrUnknown(word, Constants.WORD_KNOWN);
		return;
	}
	
	@RequestMapping("/setWordUnknown.ajax")
	public void setWordUnknown(Integer uid, String word) {
		Language lang = getLangCreator(uid);
		lang.setWordToKnownOrUnknown(word, Constants.WORD_UNKNOWN);
		return;
	}

	@RequestMapping("/updateWordMeaning.ajax")
	public void updateWordMeaning(Integer uid, String word, String meaning, String pronounce) {
//		Language lang = new LangEng(uid);	
		Language lang = getLangCreator(uid);
		lang.updateWordMeaning(word, meaning, pronounce);
		return;
	}
	
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
//	@RequestMapping("/addWordInDic")
//	public void addWordInDic(Integer uid, String word, String meaning, String pronounce) {
//		Language lang = new LangEng(uid);			
//		lang.updateWordMeaning(word, meaning, pronounce);
//		return;
//	}
	
	

	
//	@RequestMapping("/getWordFamily")
//	public @ResponseBody JSONArray getWordFamily(Integer uid, String word) {
//
//		Language lang = new LangEng();
//		if (uid == null) {
//			uid = 0;
//		}
//		lang.setUID(uid);				
//				
//		JSONArray jsonArrayWordFamily = lang.getWordFamily(word.toLowerCase());
//		logger.info("jsonArrayWordFamily : " + jsonArrayWordFamily);
//		//QUESTION : jsp의 본문에서는 이걸 어떻게 받나? jQuery에서는 또 어떻게 받나? Alert도 안됨.
//		return lang.getWordFamily(word.toLowerCase());
//	}

	
//	@RequestMapping("/getSomeWords")
//	public @ResponseBody JSONObject getSomeWords() {
//			JSONObject jsonWord = new JSONObject();
//			jsonWord.put("Word", "dalnim");
//			jsonWord.put("WordOri", "word");
//			return jsonWord;
//	}
//	
//	  @RequestMapping(value = "/time", method = RequestMethod.GET)
//	  public @ResponseBody String getTime(@RequestParam String name) {
//	    String result = "Time for " + name + " is " + new Date().toString();
//	    return result;
//	  }

//		@RequestMapping("/updateWordLevel.ajax")
//		public void updateWordLevel(Integer uid, Integer wordLevel) {
//			Language lang = new LangEng();
//			lang.setUID(uid);
//			lang.setWordLevel(wordLevel);
	//	
////			String tblName = "tbl_test1";
////			String fld_UID = "UID";
////			String fld_WORD = "WORD";
////			String fld_KNOW = "KNOW";
////			String fld_BOOKMARK = "BOOKMARK";
////			
////			String strCreateTbl = String.format("CREATE TABLE `%s` (\n"
////					+ "`%s` int(11) DEFAULT NULL,\n"
////					+ "`%s` varchar(45) DEFAULT NULL,\n"
////					+ "`%s` int(11) DEFAULT 1,\n"
////					+ "`%s` int(11) DEFAULT 0,\n"
////					+ "PRIMARY KEY (`%s`)\n"
////					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;"
////			         , tblName, fld_UID, fld_WORD, fld_KNOW, fld_BOOKMARK, fld_WORD
////			);
////			String strCreateIndex = String.format("CREATE INDEX idx_%s ON %s(%s);\n"
////					+ "CREATE INDEX idx_%s ON %s(%s);\n"							
////					, fld_KNOW, tblName, fld_KNOW, fld_BOOKMARK, tblName, fld_BOOKMARK
////			);
////			
////			
////					
////					
////			Language lang = new LangEng();
////			int max = 10000;
////			for (int i = 1; i < max; i++) {
////				logger.trace("trace i : " + i + "/" + max);
////				logger.debug("debug i : " + i + "/" + max);
////				logger.info("info i : " + i + "/" + max);
////				logger.warn("warn i : " + i + "/" + max);
////				logger.error("error i : " + i + "/" + max);
////				
////				lang.setUID(i);
////				lang.setWordLevel(14);
////			}		
//		}
}
