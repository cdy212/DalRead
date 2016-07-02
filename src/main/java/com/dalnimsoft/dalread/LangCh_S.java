package com.dalnimsoft.dalread;

import java.lang.Character.UnicodeBlock;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class LangCh_S extends Language{
	
	//아래는 inject로 해도 null로 들어옴.
	@Autowired
	private DIC_ENG_DAO dao;	
	@Autowired
	private KnowDAO knowDao;
	public LangCh_S() {
		
	}
	public LangCh_S(Integer uid) {
		super.setUID(uid);
//		iSearchDic = new SearchDicJp();		
		tbl_DIC = Constants.TBL_DIC_CH_S;
		tbl_DIC_BOOKMARK = Constants.TBL_DIC_CH_S_BOOKMARK;
		tbl_USER_DIC = Constants.TBL_USER_DIC_CH_S_PREFIX + "_" + this.uid;		
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
	public Integer getWordPos(String strOriCopy, String strCharBeginning, Integer pos, Integer strOriMaxLen) {
		String strWord = strCharBeginning;
		boolean existstrCharBeginningInDic = searchWordInDic(strCharBeginning);
		Integer posOri = pos;

		while (pos< (strOriMaxLen-1)) {
			pos++;
			//문장에서 나온 두글자 이상으로 된 단어를 사전에서 가져와서 루프를 돌면서 사전내의 제일 긴단어가 문장내에 있는지 본다. 
//		Integer nextPos = pos + 1;
//		Integer next2Pos = pos + 2;
//		if (nextPos > strOriMaxLen)
//			nextPos = strOriMaxLen;
//		if (next2Pos > strOriMaxLen)
//			next2Pos = strOriMaxLen;
			String strOne = Character.toString(strOriCopy.charAt(pos));
//			String strOne = strOriCopy.substring(nextPos, next2Pos);
			strWord += strOne;
			List<String> wordList = getSortedByLenWordListStartWith(strWord);
			logger.info("wordList : " + wordList);
			for (String strWordInList : wordList) {
				Integer lenthOfstrWordInList = strWordInList.length();
				if ((posOri + lenthOfstrWordInList) < strOriMaxLen) {
					//사전에 찾은 단어의 길이가 문장내에 있을때(out of index에러 체크)
					String strWordToCompare = strOriCopy.substring(posOri, posOri + lenthOfstrWordInList);
					logger.info("strWordToCompare : " + strWordToCompare);
					if (strWordToCompare.toLowerCase().equals(strWordInList.toLowerCase())) {
						//사전에 있는 단어와 문장내에서 일치하는 단어가 있으면 나간다. 
						logger.info("posOri : " + posOri + ", posOri + lenthOfstrWordInList : " + (posOri + lenthOfstrWordInList));
						
						return posOri + lenthOfstrWordInList;
					}					
				}
			}
			
			//두글자이상의 단어로 찾았을때 없으면, 한글자가 사전에 존재하면 그걸 리턴해준다. 
			if (existstrCharBeginningInDic) {
				return posOri + 1;
			} else {
				//사전에 없으면
				return posOri + 1;
			}

		}
		logger.info("strWord : " + strWord);
		
		return posOri + 1;
	}
	
	public boolean isRightChar(String strOneChar) {
		Set<UnicodeBlock> japaneseUnicodeBlocks = new HashSet<UnicodeBlock>() {{
		    add(UnicodeBlock.CJK_COMPATIBILITY);
		    add(UnicodeBlock.CJK_COMPATIBILITY_FORMS);
		    add(UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS);
		    add(UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT);
		    add(UnicodeBlock.CJK_RADICALS_SUPPLEMENT);
		    add(UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION);
		    add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
		    add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
		    add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B);
		    add(UnicodeBlock.KANGXI_RADICALS);
		    add(UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS);
		    
		}};

		for (char c : strOneChar.toCharArray()) {
		    if (japaneseUnicodeBlocks.contains(UnicodeBlock.of(c))) {
		    	logger.debug(c + " is a Chinese character");
		    	return true;
		    } else {
		    	logger.debug(c + " is not a Chinese character");
		    	return false;
		    }
		}
        return false;
//		// TODO Auto-generated method stub
//		System.out.println(strOneChar);
//		if (strOneChar.matches("[\u4e00-\u9FCF]")) {
//			return true;
//		}
//		return false;
	}
}
