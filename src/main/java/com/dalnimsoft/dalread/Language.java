package com.dalnimsoft.dalread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dalnimsoft.etc.Constants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public abstract class Language {
	
	protected static final Logger logger = LoggerFactory.getLogger(DalReadText.class);
	
	private static final String DRIVER = Constants.SERVER_DRIVER;//"com.mysql.jdbc.Driver";
	private static final String URL = Constants.SERVER_URL;//"jdbc:mysql://192.168.0.3/test";
	private static final String USER = Constants.SERVER_USER;//"root";
	private static final String PW = Constants.SERVER_PW;//"dal8121";

	
//	protected ISearchDic iSearchDic;
	Integer uid = 0;	
	Integer noOfWord = 0;
	String strOri = "";
//	String studyLang = Constants.LANG_EN;
//	String dispMeaningLang = Constants.LANG_KO;



	StringBuilder strResult = new StringBuilder();
	String tbl_DIC= "";
	String tbl_DIC_BOOKMARK= "";
	String tbl_USER_DIC= "";	
	String fld_Meaning = "";
	
//	String TblName_KNOW= "";
	
	JSONObject jsonWord = new JSONObject();
	JSONArray jsonArrayWords = new JSONArray();
	JSONArray jsonArrayWordFamily = new JSONArray();


	//	List<JSONObject> listWords = new ArrayList<JSONObject>();
	List<String> listWordsTemp = new ArrayList<String>();
	private List<String> listWordsAllFromString = new ArrayList<String>();
	private List<String> listUniqueWordsInString = new ArrayList<String>();
	Map<String, JSONObject> mapWords = new HashMap<String, JSONObject>();
	
	public JSONArray getJsonArrayWordFamily() {
		return jsonArrayWordFamily;
	}
	public void setJsonArrayWordFamily(JSONArray jsonArrayWordFamily) {
		this.jsonArrayWordFamily = jsonArrayWordFamily;
	}

	public List<String> getListWordsTemp() {
		return listWordsTemp;
	}
	public void setListWordsTemp(List<String> listWordsTemp) {
		this.listWordsTemp = listWordsTemp;
	}
//	public List<JSONObject> getListWords() {
//		return listWords;
//	}
//	public void setListWords(List<JSONObject> listWords) {
//		this.listWords = listWords;
//	}
	public Map<String, JSONObject> getMapWords() {
		return mapWords;
	}
	public void setMapWords(Map<String, JSONObject> mapWords) {
		this.mapWords = mapWords;
	}
	public JSONArray getJsonArrayWords() {
		return jsonArrayWords;
	}
	public void setJsonArrayWords(JSONArray jsonArrayWords) {
		this.jsonArrayWords = jsonArrayWords;
	}
	public String getStrOri() {
		return strOri;
	}
	public void setStrOri(String strOri) {
		this.strOri = strOri;
	}
	public String getStrResult() {
		return strResult.toString();
	}
	public void setStrResult(String strResult) {
		this.strResult = new StringBuilder(strResult);
	}
	public String getChar(Integer pos) {
		logger.info("getChar Language");
		return null;
	}
//	public void performSearchDic() {
//		iSearchDic.searchDic();
//	}
//	public boolean performIsRightChar(String strOneChar) {
//		return iSearchDic.isRightChar(strOneChar);
//	}
	
	public void setUID(Integer uid) {
		if (uid == null) {
			uid = Constants.UID_DEFAULT;
		}		
		this.uid = uid;
//		tbl_USER_DIC += "_" + uid;
//		logger.debug("tbl_USER_DIC : " + tbl_USER_DIC);
	}
	
//	public String getDispMeaningLang() {
//		return dispMeaningLang;
//	}
//	public void setDispMeaningLang(String dispMeaningLang) {
//		this.dispMeaningLang = dispMeaningLang;
//	}
//		return studyLang;
//	}
//	public void setStudyLang(String studyLang) {
//		this.studyLang = studyLang;
//	}
	
	
	public abstract void display();
	public void openDriver() {
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}			
	}
	public void readText() {
		// TODO Auto-generated method stub
		try {
			long start = System.currentTimeMillis();
			strResult = new StringBuilder();
			String strOriCopy = strOri;
			strOriCopy = strOriCopy.replaceAll("\r\n", "\r");
			strOriCopy = strOriCopy.replaceAll("\n\r", "\r");
			List<String> listFromStrOri = new ArrayList<String>();
			String strOneCharacter= "";
			logger.info("strText : " + this.strOri);
			Map<String, String> mapWord = new HashMap<String, String>();
			Integer pos = 0;
			Integer strOriMaxLen = strOriCopy.length();
			//문장을 돌면서 구둣점과 영어단어를 분리해서 저장한다. 유일한 영어단어 별도로 추출한다. 
			while(pos < strOriMaxLen) {
				if (strOriCopy.charAt(pos) == '\n') {
					strOneCharacter = "\r";
				} else if (strOriCopy.charAt(pos) == '\r') {
					strOneCharacter = "\r";
				} else {					
					strOneCharacter = Character.toString(strOriCopy.charAt(pos));
				}
				logger.trace("strOne : '" + strOneCharacter + "'");
//				if (performIsRightChar(strOneCharacter)) {
				if (isRightChar(strOneCharacter)) {			
					//해당언어에 해당되는 글자이면 단어를 찾는다.
					Integer lastPos = getWordPos(strOriCopy, strOneCharacter, pos, strOriMaxLen);
					String strWord = strOriCopy.substring(pos, lastPos);
					logger.info("strWord : -" + strWord + "-");
					if (lastPos == pos) {
						//단어를 사전에서 못 찾았으면 현 글자를 추가한다....(이걸 타지는 않는다.. 항상 lastPos가 1이 더 크다)
						listFromStrOri.add(strOneCharacter);
					} else {
						//단어이면... 추가한다. (영어는 사전에 있는거와 상관없이 영어이면 추가하고, 중국어는 사전에 있는지 체크해서 추가한다.
						pos = lastPos;
						listFromStrOri.add(strWord);
						String strWordLowercase = strWord.toLowerCase();
						if (!mapWord.containsKey(strWordLowercase)) {
							mapWord.put(strWordLowercase, "");
						}
					}
	//				logger.info("strWord : " + strWord);
				} else {
					listFromStrOri.add(strOneCharacter);					
//					logger.info("strOneCharacter : " + strOneCharacter);
					pos++;
				}
				
	//			logger.info("strResult : " + strResult);
				
			}
			
			//유일한 영어단어중 사전에 있는 단어만 별도로 추출한다.		
			Map<String, String> mapWordUnique  = uniqueWordsInDic(mapWord);		
			
			logger.info("before : " + mapWord.size() + ", unique : " + mapWordUnique.size());
//			logger.info("Before : " + mapWord.toString());
//			logger.info("After : " + mapWordUnique.toString());
			
			//분리한 영어단어를 다시 돌면서 HTML문장을 만든다.
			strResult = new StringBuilder();
			for (String string : listFromStrOri) {
				String strWord = string.toLowerCase();
				if (mapWordUnique.containsKey(strWord)) {
					strResult.append(mapWordUnique.get(strWord));
				} else {
					if (string.equals("\r")) {
						strResult.append("<br>");
					} else {
						strResult.append(strWord);
					}
				}
			}
			
//			logger.info("Before Sorting : " + listWordsTemp.toString());
			Collections.sort(listWordsTemp,  String.CASE_INSENSITIVE_ORDER);
//			logger.info("After Sorting : " + listWordsTemp.toString());
			long end = System.currentTimeMillis();
			logger.info( "실행 시간 readText : " + ( end - start )/1000.0 );
			 
		} catch(Exception e) {
  			
  			e.printStackTrace();
  			
  		}
	}
	
	public boolean isRightChar(String strOneChar) {
		
		return true;
	}
//	//예전에 영어 단어 별로 나누고 뜻을 다는건데 느려서 사용안함.
//	public void readTextOld() {
//		// TODO Auto-generated method stub
//		try {
//			long start = System.currentTimeMillis();
//			strResult = new StringBuilder();
//			String strOneCharacter= "";
//	//		logger.info("strText : " + this.strOri);
//			Integer pos = 0;
//			Integer strOriMaxLen = strOri.length();
//			while(pos < strOriMaxLen) {
//				strOneCharacter = Character.toString(strOri.charAt(pos));
////				logger.info("strOne : '" + strOneCharacter + "'");
//				if (performIsRightChar(strOneCharacter)) {
//					Integer lastPos = getWordEngPos(strOneCharacter, pos, strOriMaxLen);
//					strOneCharacter = strOri.substring(pos, lastPos);
//					strOneCharacter = addInfoAtWord(strOneCharacter);
//					pos = lastPos-1;
//				}
//				
//				strResult.append(strOneCharacter);
//	//			logger.info("strOne : " + strOne);
//	//			logger.info("strResult : " + strResult);
//				pos++;
//			}
////			logger.info("Before Sorting : " + listWordsTemp.toString());
//	//		Collections.sort(listWords, new Comparator<JSONObject>(){
//	//		      public int compare(JSONObject obj1, JSONObject obj2)
//	//		      {
//	//		            // TODO Auto-generated method stub
//	//		            return (int) obj1.get(("Word").compareToIgnoreCase((String) obj2.get("Word")));
//	//		      }
//	//		});
//			Collections.sort(listWordsTemp,  String.CASE_INSENSITIVE_ORDER);
////			logger.info("After Sorting : " + listWordsTemp.toString());
//			long end = System.currentTimeMillis();
//			System.out.println( "실행 시간 readTextOri : " + ( end - start )/1000.0 ); 
//		} catch(Exception e) {
//  			
//  			e.printStackTrace();
//  			
//  		}
//	}
	public Integer getWordPos(String strOriCopy, String strBefore, Integer pos, Integer strOriMaxLen) {
		return null;
	}

	public boolean searchWordInDic(String strOne) {
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
  			Statement stmt = con.createStatement();
  			String sql;
  			sql = "SELECT * from " + tbl_DIC + " WHERE Word=\"" + strOne.toLowerCase() +"\";";
  			ResultSet rs = stmt.executeQuery(sql);
  			int cnt = 0;
  			while (rs.next()) {
//  				String Word = rs.getString("Word") + "";
//  				String WordOri = rs.getString("WordOri") + "";
//  				String Pronounce = rs.getString("Pronounce") + "";
//  				String Meaning = rs.getString("Meaning") + "";
  				String Word = rs.getString(Constants.FLD_WORD) + "";
  				String WordOri = rs.getString(Constants.FLD_WORDORI) + "";
  				String Pronounce = rs.getString(Constants.FLD_PRONOUNCE) + "";
  				String Meaning = rs.getString(fld_Meaning) + "";
  				if (Pronounce.equals("null")) {
  					Pronounce = "";
  				}

  				logger.info("rs.getString(fld_Meaning) : " + rs.getString(fld_Meaning));
  				logger.info("Meaning : " + Meaning);
  				if (Meaning.equals("null")) {
  					Meaning = "";
  				}
  				if (Meaning.equals("")) {
  					Meaning = getOriMeaning(WordOri);
//  					logger.info("Meaning : " + Meaning);
  				}
//  				int WordLevel = rs.getInt("WORDLEVEL");
  				int WordLevel = rs.getInt(Constants.FLD_WORDLEVEL);
  				int Know = getKnow(Word); // rs.getInt("Know");
  				jsonWord = new JSONObject();
  				jsonWord.put("Word", strOne);
  				jsonWord.put("WordOri", WordOri);
  				jsonWord.put("Pronounce", Pronounce);
  				jsonWord.put("Meaning", Meaning);
  				jsonWord.put("Know", Know);
  				jsonWord.put("WordLevel", WordLevel);
  				cnt++;
  			}
            rs.close();
            stmt.close();
            con.close();
            if (cnt > 0) {
            	return true;
            } else {
            	return false;
            }
  		} catch(Exception e) {
  			logger.info("fail to open mysql");
  			e.printStackTrace();
  			return false;
  		}
	}
	public String addInfoAtWord(String strOne) {
		String strResult = "";
		String strOneLowercase = strOne.toLowerCase();
		
//		boolean blnExistWordInDic = false;
//		if (mapWords.containsKey(strOneLowercase)) {
//			blnExistWordInDic = true;
//		} else if (searchWordInDic(strOne)) {
//			blnExistWordInDic = true;
//		}
		
		if (searchWordInDic(strOne)) {
//		if (blnExistWordInDic) {			
			if (mapWords.containsKey(strOneLowercase)) {
				JSONObject jsonObj = mapWords.get(strOneLowercase);
//				listWords.remove(jsonObj);
				int frequency = (int) jsonObj.get("Frequency");				
				jsonObj.put("Frequency", ++frequency);
				mapWords.remove(strOneLowercase);
				mapWords.put(strOneLowercase, jsonObj);
				
//				listWords.add(jsonObj);
			} else {
				jsonWord.put("Frequency", 1);
				jsonArrayWords.add(jsonWord);
				mapWords.put(strOneLowercase, jsonWord);
//				listWords.add(jsonWord);
				listWordsTemp.add(strOneLowercase);
			}
			
		
			String strWordWithMeaning = jsonWord.getString("Word");			
			String Word = jsonWord.getString("Word");
			String Meaning = jsonWord.getString("Meaning");
			String WordOri = jsonWord.getString("WordOri");
			String Pronounce = jsonWord.getString("Pronounce");
			Integer Know = jsonWord.getInt("Know");
//			if (Meaning.length() ==0) {
//				Meaning = getOriMeaning(WordOri);
////				logger.info("Meaning : " + Meaning);
//			}
			
			if (Know != Constants.WORD_KNOWN) {
				//모르는 단어이면...
				if ((Meaning != null) && (!Meaning.equals(""))) {
					//뜻이 있으면...
					if ((Pronounce != null) && (!Pronounce.equals("") )) {
						//발음이 있으면...
						strWordWithMeaning = "<strong>" + Word + "[" + Pronounce + "," + Meaning + "]" + "</strong>";
					} else {
						strWordWithMeaning = "<strong>" + Word + "[" + Meaning + "]" + "</strong>";
					}
				} else {
					//뜻이 없으면...
					if ((Pronounce != null) && (!Pronounce.equals(""))) {
						//발음이 있으면...
						strWordWithMeaning = "<strong>" + Word + "[" + Pronounce + "]" + "</strong>";
					}
				}
			}
			
						      				
//            strResult = "<span id=\"dalread_" + Word + "_" + noOfWord.toString() + "\" class=\"dalread_" + Word + "\" data-word=\"" + Word + "\" data-wordori=\"" + WordOri + "\" data-pronounce=\"" + Pronounce + "\" data-know=\"" + Know + "\" data-meaning=\"" + Meaning + "\"><a href=\"#\" data-toggle=\"tooltip\" title=\"" + Meaning + "\">" + strWordWithMeaning + "</a></span>";
            strResult = "<span id=\"dalread_" + Word + "_" + noOfWord.toString() + "\" class=\"dalread_" + Word + "\" data-word=\"" + Word + "\" data-wordori=\"" + WordOri + "\" data-pronounce=\"" + Pronounce + "\" data-know=\"" + Know + "\" data-meaning=\"" + Meaning + "\"><a href=\"#\" class=\"dalread-text\" data-toggle=\"hover\" data-placement=\"bottom\">" + strWordWithMeaning + "</a></span>"; 
            jsonWord = new JSONObject();
		} else {
			strResult = "<span id=\"dalread_" + strOne + "_" + noOfWord.toString() + "\" class=\"dalread_" + strOne + "\" data-word=\"" + strOne + "\" data-wordori=\"" + strOne + "\" data-pronounce=\"" + "" + "\" data-know=\"0\"" + " data-meaning=\"" + "" + "\"><a href=\"#\" data-toggle=\"tooltip\" title=\"\">" + strOne + "</a></span>";
		}
		noOfWord++;
		return strResult;
	}
	
//	private void addJsonWordWithFrequency() {
//		if (json)
//	}
	
	private int getKnow(String Word) {
		int intResult = 0;
//		logger.info("Word : " + Word);
//		logger.info("strToken : " + strToken);
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql;
//			if (uid > 0) {
				sql = "SELECT * from " + tbl_USER_DIC + " WHERE Word='" + Word.toLowerCase() +"';";
//			} else {
//				sql = "SELECT * from " + tbl_DIC + " WHERE Word=\"" + Word.toLowerCase() +"\";";
//			}
			
			boolean blnWordExist = false; 
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int Know = rs.getInt("Know");
				intResult = Know;
				blnWordExist = true;
//				if (uid > 0) {
//					intResult = 3;
//				} else {
//					int Know = rs.getInt("Know");
//					intResult = Know;
//				}
			}
			
			if (blnWordExist == false) {
				intResult = Constants.WORD_UNKNOWN;
			}
			
	        rs.close();
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}		            
		return intResult;
	}
	
	private Map<String, String> uniqueWordsInDic(Map<String, String> mapWord) {
		Map<String, String> mapWordUnique = new HashMap<String, String>();
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			StringBuilder inClause = new StringBuilder();
			boolean firstValue = true;
			for (int i=0; i < mapWord.size(); i++) {
			  inClause.append('?');
			  if ( i < (mapWord.size() - 1) ) {
			    inClause.append(',');
			  }
			}
			
			String sql;
//			if (uid > 0) {
//				sql = "SELECT * from " + tbl_USER_DIC + " WHERE UID = " + uid + " and " + Constants.FLD_WORD + " in (" + inClause.toString() + ')';
//			} else {
				sql = "SELECT * from " + tbl_DIC + " WHERE " + Constants.FLD_WORD + " in (" + inClause.toString() + ')';
//			}
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			int i = 1;
	        for( String key : mapWord.keySet() ){
	        	logger.info(String.format("키 : %s, 값 : %s", key, mapWord.get(key)) );
	            pstmt.setString(i++, key);
	        }
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String word = rs.getString("WORD").toLowerCase();
				if (word.equals("the")) {
					word = word;
					logger.info("mapWordUnique : " + mapWordUnique);
				}
				word = word.toLowerCase();
				if (!mapWordUnique.containsKey(word)) {
					mapWordUnique.put(word, addInfoAtWord(word));
					if (mapWordUnique.containsKey(word)) {
						
					} else {
						word = word;
						logger.info("something wrong : " );
					}
				}
				if (word.equals("the")) {
					word = word;
					logger.info("mapWordUnique after the: " + mapWordUnique);
				}
			}
			
	        rs.close();
	        pstmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail uniqueWordsInDic");
			e.printStackTrace();
		}		
		logger.info("mapWordUnique size" + mapWordUnique.size());
		return mapWordUnique;
	}
	
	private String getOriMeaning(String WordOriParam) {
		String strResult = "";
//		logger.info("WordOriParam : " + WordOriParam);
//		logger.info("strToken : " + strToken);
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql;
			sql = "SELECT * from " + tbl_DIC + " WHERE  " + Constants.FLD_WORD + " =\"" + WordOriParam.toLowerCase() +"\";";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String Meaning = rs.getString(fld_Meaning) + "";
				if (Meaning.equals("null")) {
					Meaning = "";
				}
				strResult = Meaning;
			}
			
	        rs.close();
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}		            
		return strResult;
	}
	
	public void createUserDicIfNotExist() {
//		if (uid ==0) {
//			return;
//		}
		
		if (checkUserDicExist() == true) {
			return;
		}
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql = "";
			
//			String tblName = tbl_USER_DIC;
//			String fld_UID = "UID";
//			String fld_WORD = "WORD";
//			String fld_KNOW = "KNOW";
//			String fld_BOOKMARK = "BOOKMARK";
			
			sql = String.format("CREATE TABLE IF NOT EXISTS `%s` (\n"
					+ "`%s` int(11) DEFAULT NULL,\n"
					+ "`%s` varchar(45) DEFAULT NULL,\n"
					+ "`%s` int(11) DEFAULT 1,\n"
					+ "`%s` int(11) DEFAULT 0,\n"
					+ "`%s` varchar(45) DEFAULT NULL,\n"
					+ "`%s` varchar(45) DEFAULT NULL,\n"
					+ "`%s` varchar(45) DEFAULT NULL,\n"
					+ "`%s` varchar(45) DEFAULT NULL,\n"
					+ "index (%s, %s),\n"
					+ "PRIMARY KEY (`%s`)\n"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;"
			         , tbl_USER_DIC, Constants.FLD_UID, Constants.FLD_WORD, Constants.FLD_KNOW,  Constants.FLD_BOOKMARK, Constants.FLD_MEANING_CH_S, Constants.FLD_MEANING_CH_T, Constants.FLD_MEANING_JP, Constants.FLD_MEANING_KO, Constants.FLD_KNOW,  Constants.FLD_BOOKMARK, Constants.FLD_WORD
			);
			stmt.executeUpdate(sql);						
			
//			sql = String.format("CREATE INDEX  IF NOT EXISTS idx_%s_%s ON %s(%s);\n"							
//					,  tbl_USER_DIC, Constants.FLD_KNOW, tbl_USER_DIC,  Constants.FLD_KNOW
//			);			
//			logger.debug("sql for index : " + sql);
//			stmt.executeUpdate(sql);
//			sql = String.format("CREATE INDEX  IF NOT EXISTS idx_%s_%s ON %s(%s);\n"							
//					, tbl_USER_DIC, Constants.FLD_BOOKMARK, tbl_USER_DIC,  Constants.FLD_BOOKMARK
//			);			
			
	        stmt.close();
	        con.close();
	        
	        setWordLevel(Constants.WORD_LEVEL_DEFAULT);
	        
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}
	}
	
	public boolean checkUserDicExist() {
		boolean blnUserDicExist = false;
//		if (uid ==0) {
//			return false;
//		}
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql = "SELECT COUNT(*) count FROM information_schema.tables WHERE table_schema = '" + Constants.DB_NAME + "' and TABLE_NAME = '" + tbl_USER_DIC + "';";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer count = rs.getInt("count");
				if (count > 0) {
					blnUserDicExist = true;
				}
			}
						
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}
		return blnUserDicExist;
	}
	public void setWordToKnownOrUnknown(String word, Integer knownWord) {
//		if (uid ==0) {
//			return;
//		}
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql = "";
//			if (knownWord == Constants.WORD_KNOWN) {
//				sql = "INSERT ignore into " + tbl_USER_DIC + " (UID, Word) SELECT " + uid + ", WORD FROM " + tbl_DIC + " WHERE " + Constants.FLD_WORD + " = \"" + word + "\";";
//			} else if (knownWord == Constants.WORD_UNKNOWN) {
//				sql = "DELETE FROM " + TblName_KNOW + " WHERE UID = " + uid + " and WORD = \"" + word + "\";";
//			}
			//일단 단어를 USER_DIC에 넣는다.
//			sql = "INSERT ignore into " + tbl_USER_DIC + " (UID, Word) SELECT " + uid + ", WORD FROM " + tbl_DIC + " WHERE " + Constants.FLD_WORD + " = \"" + word + "\";";
			sql = "INSERT ignore into " + tbl_USER_DIC + " (" + uid + ", \"" +  word + "\");";
			stmt.executeUpdate(sql);
			
			//넣은 단어에 대해서 안다 모른다를 설정해준다.
			sql = "UPDATE " + tbl_USER_DIC + " SET " + Constants.FLD_KNOW + "=" + knownWord + " WHERE " + Constants.FLD_WORD + " = \"" + word + "\";";
			stmt.executeUpdate(sql);
 
			
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}	
	}
	
	public void updateWordMeaning(String word, String meaning, String pronounce) {
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql = "";

//			PreparedStatement pstmt = con.prepareStatement(sql);
//
//			Integer noOfRows= pstmt.executeUpdate();
//	        pstmt.close();
//	        
	        
			sql = "UPDATE " + tbl_DIC + " SET " + fld_Meaning + "=\"" + meaning + "\", " + Constants.FLD_PRONOUNCE+ "='" + pronounce + "' WHERE " + Constants.FLD_WORD + " = \"" + word.toLowerCase() + "\";";
			Integer noOfRows = stmt.executeUpdate(sql); 
			if (noOfRows == 0) {
				addWordInDic(word, meaning, pronounce);
			}
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}	
	}
	
	public void addWordInDic(String word, String meaning, String pronounce) {
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql = "";
						
			//단어를 USER_DIC에 넣는다.			
			sql = "INSERT ignore into " + tbl_DIC  + " (" + Constants.FLD_WORD + ", " + Constants.FLD_WORDORI + ", " + Constants.FLD_PRONOUNCE + ", " + fld_Meaning + ") VALUES ( \"" + word.toLowerCase() + "\", \"" + word.toLowerCase() + "\", \"" + pronounce + "\", \"" + meaning + "\");";
			stmt.executeUpdate(sql);
 
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}	
	}
	
	
	public void setWordLevel(Integer wordLevel) {
//		if (uid == 0) {
//			return;
//		}
		
//		deleteWordLevel();
		
		// TODO Auto-generated method stub		
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
//			String sql = "INSERT ignore into " + TblName_KNOW + " (UID, Word) SELECT " + uid + ", WORD FROM DIC_ENG WHERE WORDLEVEL <= " + wordLevel + ";";
			String sql = "INSERT ignore into " + tbl_USER_DIC + " (UID, Word, KNOW) SELECT " + uid + ", " + Constants.FLD_WORD + ", " + Constants.WORD_KNOWN + " FROM " + tbl_DIC + " WHERE WORDLEVEL <= " + wordLevel + ";";			
			stmt.executeUpdate(sql);
			
//			sql = "UPDATE " + tbl_USER_DIC + " SET " + Constants.FLD_KNOW + " = " + Constants.WORD_KNOWN + "FROM ( SELECT " + Constants.FLD_WORD + " FROM " + tbl_DIC + ", " + tbl_USER_DIC + " WHERE WORDLEVEL <= " + wordLevel + ";";
			sql = "UPDATE " + tbl_USER_DIC + " a "
					+ " LEFT JOIN ( SELECT " + Constants.FLD_WORD + " FROM " + tbl_DIC + " WHERE " + Constants.FLD_WORDLEVEL + " <= " + wordLevel + ") b on a." + Constants.FLD_WORD + " = b." + Constants.FLD_WORD
					+ " SET a." + Constants.FLD_KNOW + "=" + Constants.WORD_KNOWN + " WHERE a." + Constants.FLD_WORD + " = b." + Constants.FLD_WORD + ";";
			
			stmt.executeUpdate(sql);
			
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}	
	}
	
	public int countOfKnownWord() {
		// TODO Auto-generated method stub
		int count = 0;		
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql;
			sql = "SELECT count(*) countWordLevel from " + tbl_USER_DIC + " WHERE " + Constants.FLD_KNOW + " = " + Constants.WORD_KNOWN + ";";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt("countWordLevel");
			}
			
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}
		return count;	
	}
	
	public int countOfUnknownWord() {
		// TODO Auto-generated method stub
		int count = 0;		
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql;
			sql = "SELECT count(*) countWordLevel from " + tbl_USER_DIC + " WHERE " + Constants.FLD_KNOW + " = " + Constants.WORD_UNKNOWN + ";";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt("countWordLevel");
			}
			
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}
		return count;	
	}
	
	public int countOfBookmarkWord() {
		// TODO Auto-generated method stub
		int count = 0;		
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql;			
			sql = "SELECT count(*) countOfBookmarkWord from " + tbl_DIC_BOOKMARK + " WHERE " + Constants.FLD_UID + "=" + uid + ";";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt("countOfBookmarkWord");
			}
			
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}
		return count;	
	}	
	
	public JSONArray getWordList(String wordListType) {
		JSONArray jsonArrayWordList = new JSONArray();
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
  			Statement stmt = con.createStatement();
  			String sql;
  			if (wordListType.equals(Constants.WORDLISTTYPE_UNKNOWN)) {
  				sql = "SELECT " + Constants.FLD_WORD + " from " + tbl_USER_DIC + " WHERE " + Constants.FLD_KNOW+ "=\"" + Constants.WORD_UNKNOWN +"\";";
  			} else if (wordListType.equals(Constants.WORDLISTTYPE_KNOWN)) {
  				sql = "SELECT " + Constants.FLD_WORD + " from " + tbl_USER_DIC + " WHERE " + Constants.FLD_KNOW+ "=\"" + Constants.WORD_KNOWN +"\";";
  			} else {
  				sql = "SELECT " + Constants.FLD_WORD + " from " + tbl_DIC_BOOKMARK + " WHERE " + Constants.FLD_UID + "=" + uid +";";
  			}  			
  			ResultSet rs = stmt.executeQuery(sql);
  			int cnt = 0; //Word 수를 100개 이하로 일단 줄인다... 나중에는 페이지별로 가져오게 해야 함.
  			while (rs.next()) {
  				String Word = rs.getString(Constants.FLD_WORD);
  				searchWordInDic(Word);
  				logger.info("jsonWord[" + Word + "] : " + jsonWord );
  				jsonArrayWordList.add(jsonWord);  				
  				cnt++;
  				if (cnt > 10) {
  					break;
  				}
  			}
            rs.close();
            stmt.close();
            con.close();

  		} catch(Exception e) {
  			logger.info("fail to open mysql");
  			e.printStackTrace();
  		}
		return jsonArrayWordList;
	}
	
	public JSONArray getWordFamily(String word) {
		String strWordOri = getWordOri(word);
		jsonArrayWordFamily = new JSONArray();
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
  			Statement stmt = con.createStatement();
  			String sql;
  			sql = "SELECT * from " + tbl_DIC + " WHERE " + Constants.FLD_WORDORI + "=\"" + strWordOri.toLowerCase() +"\";";
  			ResultSet rs = stmt.executeQuery(sql);
  			int cnt = 0; //Word Family가 100를 넘어가면 이상하다.
  			while (rs.next()) {
  				String Word = rs.getString("Word") + "";
  				String WordOri = rs.getString("WordOri") + "";
  				String Pronounce = rs.getString("Pronounce") + "";
  				String Meaning = rs.getString(fld_Meaning) + "";  				
  				logger.info("rs.getString(fld_Meaning) : " + rs.getString(fld_Meaning));
  				logger.info("Meaning : " + Meaning);
  				if (Meaning.equals("null")) {
  					Meaning = "";
  				}  				  				
  				int WordLevel = rs.getInt("WORDLEVEL");
  				int Know = getKnow(Word); // rs.getInt("Know");
  				jsonWord = new JSONObject();
  				jsonWord.put("Word", Word);
  				jsonWord.put("WordOri", WordOri);
  				jsonWord.put("Pronounce", Pronounce);
  				jsonWord.put("Meaning", Meaning);
  				jsonWord.put("Know", Know);
  				jsonWord.put("WordLevel", WordLevel);
  				jsonArrayWordFamily.add(jsonWord);
  				cnt++;
  				if (cnt > 100) {
  					break;
  				}
  			}
            rs.close();
            stmt.close();
            con.close();

  		} catch(Exception e) {
  			logger.info("fail to open mysql");
  			e.printStackTrace();
  		}
		return jsonArrayWordFamily;
	}
	
	public String getWordOri(String word) {
		String resultWordOri = word;
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
  			Statement stmt = con.createStatement();
  			String sql;
  			sql = "SELECT * from " + tbl_DIC + " WHERE " + Constants.FLD_WORD + "=\"" + word.toLowerCase() +"\";";
  			ResultSet rs = stmt.executeQuery(sql);  			
  			while (rs.next()) {
  				String WordOri = rs.getString("WordOri") + "";
  				if (WordOri.length() > 0) {
  					resultWordOri = WordOri;
  				}
  			}
            rs.close();
            stmt.close();
            con.close();

  		} catch(Exception e) {
  			logger.info("fail to open mysql");
  			e.printStackTrace();
  		}
		return resultWordOri;
	}
	
	
	public boolean blnBookmarkedWord(String word) {
		boolean blnBookmarkedWord = false;
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
  			Statement stmt = con.createStatement();
  			String sql;
  			sql = "SELECT * from " + tbl_DIC_BOOKMARK + " WHERE " + Constants.FLD_UID + "=" + uid + " and " + Constants.FLD_WORD + "=\"" + word.toLowerCase() +"\";";
  			ResultSet rs = stmt.executeQuery(sql);
  			
  			while (rs.next()) {
  				blnBookmarkedWord = true;
  				break;
  			}
            rs.close();
            stmt.close();
            con.close();

  		} catch(Exception e) {
  			logger.info("fail to open mysql");
  			e.printStackTrace();
  		}
		return blnBookmarkedWord;
	}
	
	public void addToBookmark(String word) {		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
  			Statement stmt = con.createStatement();
  			String sql;
  			sql = "INSERT ignore into " + tbl_DIC_BOOKMARK + " (" + Constants.FLD_UID + ", " + Constants.FLD_WORD + ") VALUES ( " + uid + ", \"" + word.toLowerCase() + "\");";
			stmt.executeUpdate(sql);
            stmt.close();
            con.close();

  		} catch(Exception e) {
  			logger.info("fail to open mysql");
  			e.printStackTrace();
  		}
		return;
	}
	
	public void deleteFromBookmark(String word) {		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
  			Statement stmt = con.createStatement();
  			String sql;
  			sql = "DELETE FROM " + tbl_DIC_BOOKMARK + " WHERE " + Constants.FLD_UID + " = " + uid + " and " + Constants.FLD_WORD + " = \"" + word + "\";";  			
			stmt.executeUpdate(sql);
            stmt.close();
            con.close();

  		} catch(Exception e) {
  			logger.info("fail to open mysql");
  			e.printStackTrace();
  		}
		return;
	}
	
	public void deleteWordLevel() {
		// TODO Auto-generated method stub
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
//			String sql = "DELETE FROM " + TblName_KNOW + " WHERE UID = " + uid + ";";
			String sql = "UPDATE " + tbl_USER_DIC + " SET " + Constants.FLD_KNOW + "=" + Constants.WORD_UNKNOWN + ";";			
			stmt.executeUpdate(sql);
			
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}	
	}
	
	public String getDispMeaningLangFromTbl() {
		return getLangFromTbl(Constants.FLD_LANG_DISPLAY);
//		String strLangDisplay = Constants.LANG_KO;
//		try {
//  			Class.forName(DRIVER);
//  		} catch (ClassNotFoundException e1) {
//  			// TODO Auto-generated catch block
//  			e1.printStackTrace();
//  		}		
//		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
//			Statement stmt = con.createStatement();
//			String sql = "SELECT * from " + Constants.TBL_MEMBER + " LEFT JOIN  " + Constants.TBL_LANGUAGE + " on " + Constants.TBL_MEMBER + "." + Constants.FLD_LANG_DISPLAY + "=" + Constants.TBL_LANGUAGE + "." + Constants.FLD_LANG_ID +" WHERE " +  Constants.TBL_MEMBER + "." + Constants.FLD_UID + "=" + uid + ";";
//  			ResultSet rs = stmt.executeQuery(sql);
//  			
//  			while (rs.next()) {
//  				strLangDisplay = rs.getString(Constants.FLD_LANG_ENG);
//  				break;
//  			}
//            rs.close();			
//	        stmt.close();
//	        con.close();
//		} catch(Exception e) {
//			logger.info("fail to open mysql");
//			e.printStackTrace();
//		}	
//		return strLangDisplay;
	}
	
	private String getLangFromTbl(String langFldName) {
		String strLang = Constants.LANG_KO;
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql = "SELECT * from " + Constants.TBL_MEMBER + " LEFT JOIN  " + Constants.TBL_LANGUAGE + " on " + Constants.TBL_MEMBER + "." + langFldName + "=" + Constants.TBL_LANGUAGE + "." + Constants.FLD_LANG_ID +" WHERE " +  Constants.TBL_MEMBER + "." + Constants.FLD_UID + "=" + uid + ";";
  			ResultSet rs = stmt.executeQuery(sql);
  			
  			while (rs.next()) {
  				strLang = rs.getString(Constants.FLD_LANG_ENG);
  				break;
  			}
            rs.close();			
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}	
		return strLang;
	}
	
	public String getStudyLang() {
		return getLangFromTbl(Constants.FLD_LANG_STUDY);
	}
	
	//공부하는 언어를 바꾼다.
	public void setStudyLangToTbl(String studyLang) {
		createUserDicIfNotExist();
		setLangToTbl(studyLang, Constants.FLD_LANG_STUDY);
	}
	public void setDispMeaningLangToTbl(String dispMeaningLang) {
		setLangToTbl(dispMeaningLang, Constants.FLD_LANG_DISPLAY);
//		try {
//  			Class.forName(DRIVER);
//  		} catch (ClassNotFoundException e1) {
//  			// TODO Auto-generated catch block
//  			e1.printStackTrace();
//  		}		
//		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
//			Statement stmt = con.createStatement();
//			String sql = "";
////			if (knownWord == Constants.WORD_KNOWN) {
////				sql = "INSERT ignore into " + tbl_USER_DIC + " (UID, Word) SELECT " + uid + ", WORD FROM " + tbl_DIC + " WHERE " + Constants.FLD_WORD + " = \"" + word + "\";";
////			} else if (knownWord == Constants.WORD_UNKNOWN) {
////				sql = "DELETE FROM " + TblName_KNOW + " WHERE UID = " + uid + " and WORD = \"" + word + "\";";
////			}
//			Integer langCode = getLangCodeFromLangName(dispMeaningLang);
//			
//			sql = "UPDATE " + Constants.TBL_MEMBER + " SET " + Constants.FLD_LANG_DISPLAY + "=" + langCode + " WHERE " + Constants.FLD_UID + " = \"" + uid + "\";";
//			stmt.executeUpdate(sql);
// 
//			
//	        stmt.close();
//	        con.close();
//		} catch(Exception e) {
//			logger.info("fail to open mysql");
//			e.printStackTrace();
//		}	
	}
	
	public void setLangToTbl(String dispMeaningLang, String langFldName) {
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql = "";
//			if (knownWord == Constants.WORD_KNOWN) {
//				sql = "INSERT ignore into " + tbl_USER_DIC + " (UID, Word) SELECT " + uid + ", WORD FROM " + tbl_DIC + " WHERE " + Constants.FLD_WORD + " = \"" + word + "\";";
//			} else if (knownWord == Constants.WORD_UNKNOWN) {
//				sql = "DELETE FROM " + TblName_KNOW + " WHERE UID = " + uid + " and WORD = \"" + word + "\";";
//			}
			Integer langCode = getLangCodeFromLangName(dispMeaningLang);
			
			sql = "UPDATE " + Constants.TBL_MEMBER + " SET " + langFldName + "=" + langCode + " WHERE " + Constants.FLD_UID + " = \"" + uid + "\";";
			stmt.executeUpdate(sql);
 
			
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}	
	}
	
	public Integer getLangCodeFromLangName(String langName) {
		Integer langCode = Constants.LANGCODE_KO;
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql = "SELECT * from " + Constants.TBL_LANGUAGE + " WHERE upper(" + Constants.FLD_LANG_ENG + ") = '" + langName.toUpperCase() + "';";
  			ResultSet rs = stmt.executeQuery(sql);
  			
  			while (rs.next()) {
  				langCode = rs.getInt(Constants.FLD_LANG_ID);
  				break;
  			}
            rs.close();		
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}
		return langCode;
	}
	public String getUserDispMeaningLangFldName() {
		String strLangInFldLangEng = getDispMeaningLangFromTbl();
		return convertFldLangEngToFldName(strLangInFldLangEng);
	}
	
	private String convertFldLangEngToFldName (String langNameInEnglish) {
		String strLangDisplay = Constants.FLD_MEANING_KO;
		switch (langNameInEnglish.toUpperCase()) {
			case	Constants.LANG_CH_S:
				strLangDisplay = Constants.FLD_MEANING_CH_S;
				break;
			case	Constants.LANG_CH_T:
				strLangDisplay = Constants.FLD_MEANING_CH_T;
				break;
			case	Constants.LANG_EN:
				strLangDisplay = Constants.FLD_MEANING_ENG;
				break;
			case	Constants.LANG_JP:
				strLangDisplay = Constants.FLD_MEANING_JP;
				break;
			default :
				strLangDisplay = Constants.FLD_MEANING_KO;
				break;  					
		}
		return strLangDisplay;
	}
	
	public List<String> getWordListStartWith(String startChar) {
		List<String> wordList = new ArrayList<String>();
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql = "SELECT * from " + tbl_DIC + " WHERE " + Constants.FLD_WORD + " like '" + startChar.toLowerCase() + "%';";
  			ResultSet rs = stmt.executeQuery(sql);
  			
  			while (rs.next()) {
  				String word = rs.getString(Constants.FLD_WORD);
  				wordList.add(word);
  			}
            rs.close();		
	        stmt.close();
	        con.close();
	        
	        
		} catch(Exception e) {
			logger.info("fail to open mysql");
			e.printStackTrace();
		}
		return wordList;
	}
	
	public List<String> getSortedByLenWordListStartWith (String startChar) {
		List<String> wordList = getWordListStartWith(startChar);
		logger.info("wordList : " + wordList);
		MyStr myStr = new MyStr();
		List<String> wordListSorted = myStr.sortArraryByLen(wordList);
		logger.info("wordListSorted : " + wordListSorted);
		return wordListSorted;
	}
	
	public void test() throws Exception {
	}
	
}
