package com.dalnimsoft.etc;

public class Constants {

	/** 단어를 아는정도 */
	public static final Integer WORD_UNKNOWN = 1;
	public static final Integer WORD_KNOWN = 2;
	public static final Integer WORD_BOOKMARK = 3;
	
	
	/** DB 명 */
	public static final String DB_NAME = "test";
	
	/** 테이블 명 */
	public static final String TBL_DIC_CH_S = "DIC_CH_S";
	public static final String TBL_DIC_ENG = "DIC_ENG";
	public static final String TBL_DIC_JP = "DIC_JP";
	
	public static final String TBL_DIC_CH_S_BOOKMARK = "TBL_DIC_CH_S_BOOKMARK";
	public static final String TBL_DIC_ENG_BOOKMARK = "TBL_DIC_ENG_BOOKMARK";
	public static final String TBL_DIC_JP_BOOKMARK = "TBL_DIC_JP_BOOKMARK";	
	public static final String TBL_LANGUAGE = "TBL_LANGUAGE";
	public static final String TBL_MEMBER = "TBL_MEMBER";
	public static final String TBL_USER_DIC_CH_S_PREFIX = "TBL_USER_DIC_CH_S";
	public static final String TBL_USER_DIC_ENG_PREFIX = "TBL_USER_DIC_ENG";
	public static final String TBL_USER_DIC_JP_PREFIX = "TBL_USER_DIC_JP";

	/** 필드 명 */
	public static final String FLD_BOOKMARK = "BOOKMARK";
	public static final String FLD_EMAIL = "EMAIL";
	public static final String FLD_KNOW = "KNOW";
//	public static final String FLD_MEANING = "MEANING";
	public static final String FLD_LANG_DISPLAY = "LANG_DISPLAY";
	public static final String FLD_LANG_ENG = "LANG_ENG";		//언어명을 그나라 언어가 아니고 영어로 표시한것
	public static final String FLD_LANG_ID = "LANG_ID";
	public static final String FLD_LANG_STUDY = "LANG_STUDY";
	
	public static final String FLD_MEANING_CH_S = "MEANING_CH_S";
	public static final String FLD_MEANING_CH_T = "MEANING_CH_T";
	public static final String FLD_MEANING_ENG = "MEANING_ENG";
	public static final String FLD_MEANING_JP = "MEANING_JP";
	public static final String FLD_MEANING_KO = "MEANING_KO";
	public static final String FLD_NAME = "NAME";	
	public static final String FLD_PRONOUNCE = "PRONOUNCE";	
	public static final String FLD_PWD = "PWD";	
	public static final String FLD_WORD = "WORD";
	public static final String FLD_WORDORI = "WORDORI";
	public static final String FLD_WORDLEVEL = "WORDLEVEL";	
	public static final String FLD_UID = "UID";
	
	/** 언어들	 */	
	public static final String LANG_CH_S = "CHINESE_SIMPLIFIED";	
	public static final String LANG_CH_T = "CHINESE_TRADITIONAL";
	public static final String LANG_EN = "ENGLISH";
	public static final String LANG_JP = "JAPANESE";
	public static final String LANG_KO = "KOREAN";
	
	public static final Integer LANGCODE_CN_S = 1;
	public static final Integer LANGCODE_CN_T = 2;	
	public static final Integer LANGCODE_EN = 13;
	public static final Integer LANGCODE_JP = 4;
	public static final Integer LANGCODE_KO = 3;
	
	/** KEY값들 	 */	
	public static final String KEY_CURRENT_URL = "CURRENT_URL";
	public static final String KEY_ID = "ID";
	public static final String KEY_LOGIN_ID = "LOGIN_ID";
	public static final String KEY_NAME = "NAME";
	
	public static final String KEY_PASSWORD = "PASSWORD";
	public static final String KEY_UID = "UID";
	
	/** 각종 값들	 */
	public static final String STR_KNOW = "KNOW";
	public static final String STR_FREQUENCY = "FREQUENCY";
	public static final String STR_MEANING = "MEANING";
	public static final String STR_PRONOUNCE = "PRONOUNCE";
	public static final String STR_WORD = "WORD";
	public static final String STR_WORDORI = "WORDORI";
	
	
	
	public static final String WORDLISTTYPE_KNOWN = "1";
	public static final String WORDLISTTYPE_UNKNOWN = "2";
	public static final String WORDLISTTYPE_BOOKMARK = "3";
	
	/** 서버 경로 */
	public static final String SERVER_DRIVER = "com.mysql.jdbc.Driver";
//	public static final String SERVER_URL = "jdbc:mysql://192.168.0.3/test";
	public static final String SERVER_URL = "jdbc:mysql://121.130.57.175:3306/test";
	public static final String SERVER_USER = "root";
	public static final String SERVER_PW = "dal8121";

	/** 기타 값들 */
	public static final Integer WORD_LEVEL_DEFAULT = 3;
	public static final Integer UID_DEFAULT = 1505;
//	
//	/** 신규사용자 포인트 */
//	public static final Integer NEW_USER_CHARGE_POINT = 50;
//	
//	/** 운영서버 첨부파일 경로 */
//	public static final String REAL_FILE_PATH = "/home/ec2-user/web/fileStore/";
//	/** 로컬 첨부파일 경로 */
////	public static final String LOCAL_FILE_PATH = "/Users/kim/DEVLIB/workspace/conversation/web/fileStore/";
//	public static final String LOCAL_FILE_PATH = "/home/ec2-user/web/fileStore/";
//	
//	public static final String DOWNLOAD_FILE_PATH = "http://52.69.52.172/fileStore/";
//	
////	public static final String SQLITE_PATH = "/Users/kim/DEVLIB/workspace/daltalk_dev/WebContent/keystore/DalTalk.sqlite";
//	public static final String SQLITE_PATH = "/home/ec2-user/web/keystore/DalTalk.sqlite";
}
