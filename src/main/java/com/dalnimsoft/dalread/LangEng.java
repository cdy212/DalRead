package com.dalnimsoft.dalread;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalnimsoft.DAO.DIC_ENG_DAO;
import com.dalnimsoft.DAO.KnowDAO;
import com.dalnimsoft.dalread.SearchDicEng;
import com.dalnimsoft.domain.DIC_ENG_VO;
import com.dalnimsoft.etc.Constants;

@Component
public class LangEng extends Language{
	
	//아래는 inject로 해도 null로 들어옴.
	@Autowired
	private DIC_ENG_DAO dao;	
	@Autowired
	private KnowDAO knowDao;
	
	public LangEng() {
//		iSearchDic = new SearchDicEng();		
		tbl_DIC = Constants.TBL_DIC_ENG;
		tbl_DIC_BOOKMARK = Constants.TBL_DIC_ENG_BOOKMARK;
		tbl_USER_DIC = Constants.TBL_USER_DIC_ENG_PREFIX;
		fld_Meaning = super.getUserDispMeaningLangFldName();
		logger.info("fld_meaning : " + fld_Meaning);
//		TblName_KNOW = "test.KNOW_ENG";
	}
	
	public LangEng(Integer uid) {
		super.setUID(uid);
//		iSearchDic = new SearchDicEng();		
		tbl_DIC = Constants.TBL_DIC_ENG;
		tbl_DIC_BOOKMARK = Constants.TBL_DIC_ENG_BOOKMARK;
		tbl_USER_DIC = Constants.TBL_USER_DIC_ENG_PREFIX + "_" + this.uid;		
		fld_Meaning = super.getUserDispMeaningLangFldName();
		logger.info("fld_meaning : " + fld_Meaning);
//		TblName_KNOW = "test.KNOW_ENG";
	}

	
	@Override
	public String getChar(Integer pos) {
		// TODO Auto-generated method stub
		return super.getChar(pos);
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("display in Eng");
	}
	public Integer getWordPos(String strOriCopy, String strBefore, Integer pos, Integer strOriMaxLen) {
		String strWord = strBefore;
		while (pos< (strOriMaxLen-1)) {
			pos++;
			String strOne = Character.toString(strOriCopy.charAt(pos));
			logger.info("strOne : -" + strOne + "-");
//			if (!(performIsRightChar(strOne))) {
			if (!(isRightChar(strOne))) {
				return pos;
			}
			strWord += strOne;
		}
		return pos+1;
	}
	
	public boolean isRightChar(String strOneChar) {
		// TODO Auto-generated method stub
		if (strOneChar.matches("[a-z|A-Z]")) {
			return true;
		}
		return false;
	}
	
	public void test() throws Exception {
		logger.info("test");
		
		DIC_ENG_VO vo = new DIC_ENG_VO();
		System.out.println("selectWord");
		knowDao.getTime();
		vo = (DIC_ENG_VO)dao.selectWord("the");
//		System.out.println("VO : " + vo.getMEANING());
//		System.out.println("VO : " + vo.getPRONOUNCE());
	}
}
