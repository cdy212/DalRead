package com.dalnimsoft.dalread;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dalnimsoft.DAO.DIC_ENG_DAO;
import com.dalnimsoft.DAO.KnowDAO;
import com.dalnimsoft.domain.DIC_ENG_VO;
import com.dalnimsoft.etc.Constants;
import com.dalnimsoft.dalread.Language;
import com.dalnimsoft.dalread.LangEng;
import com.dalnimsoft.dalread.LangCh_S;
/**
 * Servlet implementation class DalReadText
 */

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DalReadText extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DRIVER = Constants.SERVER_DRIVER;//"com.mysql.jdbc.Driver";
	private static final String URL = Constants.SERVER_URL;//"jdbc:mysql://192.168.0.3/test";
	private static final String USER = Constants.SERVER_USER;//"root";
	private static final String PW = Constants.SERVER_PW;//"dal8121";
	private static final Logger logger = LoggerFactory.getLogger(DalReadText.class);
	int uid = 0;
	String studyLang = Constants.LANG_EN;
	String dispMeaningLang = Constants.LANG_KO;
	
	@Inject	
	private DIC_ENG_DAO dao;
	@Inject
	private KnowDAO knowDao;
//	@Autowired
//	private DIC_ENG_VO dic_eng_vo;
//	@Autowired
//	private DIC_ENG_DAOImpl dic_eng_daoimpl;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DalReadText() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		res.setContentType("text/html;charset=utf-8");
		String strParam = req.getParameter("comment");
		try {
			req.setAttribute("commenttt", parseString(strParam));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		req.setAttribute("commenttt", strParam);
		RequestDispatcher rd =getServletContext().getRequestDispatcher("/WEB-INF/views/HTMLAddMeaning.jsp");
		rd.forward(req,res);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		try {
//			DIC_ENG_VO vo = new DIC_ENG_VO();
//			System.out.println("selectWord");
//			knowDao.getTime();
//			vo = (DIC_ENG_VO)dao.selectWord("the");
//			System.out.println("VO : " + vo.getMEANING());
//			System.out.println("VO : " + vo.getPRONOUNCE());
//			
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		res.setContentType("text/html;charset=utf-8");
		String strParam = req.getParameter("comment");
		String strUID = req.getParameter("UID");
		studyLang = req.getParameter("studyLang");
		dispMeaningLang = req.getParameter("dispMeaningLang");
		
		logger.debug("strUID : " + strUID);
		logger.debug("studyLang : " + studyLang);
		logger.debug("dispMeaningLang : " + dispMeaningLang);
		
		if (strUID.length() > 0) {
			uid = Integer.parseInt(strUID);
		} else {
			uid = Constants.UID_DEFAULT;
		}
		logger.debug("UID : " + uid);		
		
		Map<String, Object> returnVal = null;
		try {
			returnVal = parseString(strParam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.setAttribute("addMeaning", returnVal.get("addMeaning").toString());
		
		List wordLevelList = new ArrayList<>();
		wordLevelList.add("hi");
		wordLevelList.add("school");
		wordLevelList.add("math");
		wordLevelList.add("law");
		wordLevelList.add("accent");
		wordLevelList.add("mobile");
		wordLevelList.add("manuver");
		
		req.setAttribute("wordLevelList", wordLevelList);
		
		
		JSONArray jsonArray = (JSONArray)returnVal.get("wordList");
//		JSONArray jsonArray = null;
//		try {
//			jsonArray = getMemberInfo();
//			System.out.println("Succeed JSONArray");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("fail JSONArray");
//			e.printStackTrace();
//		}
//	    req.setAttribute("myDicList", jsonArray);
		
	    req.setAttribute("myDicList", jsonArray);	   
	    req.setAttribute("listWords", (List<JSONObject>)returnVal.get("listWords"));
	    req.setAttribute("listWordsTemp", returnVal.get("listWordsTemp"));
	    req.setAttribute("strOri", returnVal.get("strOri"));
	    req.setAttribute("mapWords", (Map)returnVal.get("mapWords"));
	    
		RequestDispatcher rd =getServletContext().getRequestDispatcher("/WEB-INF/views/HTMLAddMeaning.jsp");
		rd.forward(req,res);
	}
	
//	private String tempGetWordLevel1() throws Exception {
//		String strResult = "";
//		System.out.println("selectWordByWordLevel");
//		try {
//			List<DIC_ENG_VO> list = dao.selectWordByWordLevel(3);
//			System.out.println("selectWordByWordLevel");
//			for (DIC_ENG_VO vo : list) {
//				strResult += vo.getWORD() + ", " + vo.getPRONOUNCE() + ", " + vo.getMEANING() + "\r";
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return strResult;
//	}
//	private String parseString(String strOri) {
	private Map<String, Object> parseString(String strOri) throws Exception {
		Map<String,Object> returnVal = new HashMap<String,Object>();
		String strResult = "dalnim is best? Sure<br><br>\r";// + tempGetWordLevel1();
		JSONArray jsonArray = new JSONArray();
		
		
		Language lang;
		
		if (studyLang.toUpperCase().equals(Constants.LANG_CH_S)) {
			lang = new LangCh_S(uid);
		} else if (studyLang.toUpperCase().equals(Constants.LANG_JP)) {
			lang = new LangJP(uid);
		} else {
			lang = new LangEng(uid);
		}

		lang.setStrOri(strOri);
		
//		lang.readTextOri();
		lang.readText();
		
//		strResult = "<p>" + lang.getStrResult() + "</p>";
//		returnVal.put("addMeaning", strResult);
//		logger.info("lang.getStrOri()" + lang.getStrOri());
//		returnVal.put("strOri", "<p>" + lang.getStrOri() + "</p>");		
		returnVal.put("addMeaning", lang.getStrResult());		
		returnVal.put("strOri",lang.getStrOri());

		returnVal.put("wordList", lang.getJsonArrayWords());
//		logger.info("lang.getJsonArrayWords()" + lang.getJsonArrayWords());
//		returnVal.put("listWords", lang.getListWords());
		returnVal.put("listWordsTemp", lang.getListWordsTemp());
//		logger.info("lang.getListWordsTemp()" + lang.getListWordsTemp());
		returnVal.put("mapWords", lang.getMapWords());
//		logger.info("lang.getMapWords()" + lang.getMapWords());
		return returnVal;
//		return strResult;
	}
	
	private int getKnow(String Word) {
		int intResult = 0;
		System.out.println("Word : " + Word);
//		System.out.println("strToken : " + strToken);
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql;
			if (uid > 0) {
				sql = "SELECT * from KNOW_ENG WHERE Word=\"" + Word.toLowerCase() +"\" and UID=" + uid +";";
			} else {
				sql = "SELECT * from DIC_ENG WHERE Word=\"" + Word.toLowerCase() +"\";";
			}
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (uid > 0) {
					intResult = 3;
				} else {
					int Know = rs.getInt("Know");
					intResult = Know;
				}
			}
			
	        rs.close();
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			System.out.println("fail to open mysql");
			e.printStackTrace();
		}		            
		return intResult;
	}
	private String getOriMeaning(String WordOriParam) {
		String strResult = "";
		System.out.println("WordOriParam : " + WordOriParam);
//		System.out.println("strToken : " + strToken);
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql;
			sql = "SELECT * from DIC_ENG WHERE Word=\"" + WordOriParam.toLowerCase() +"\";";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String Word = rs.getString("Word") + "";
				String WordOri = rs.getString("WordOri") + "";
				String Pronounce = rs.getString("Pronounce") + "";
				String Meaning = rs.getString("Meaning") + "";
				strResult = Meaning;
			}
			
	        rs.close();
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			System.out.println("fail to open mysql");
			e.printStackTrace();
		}		            
		return strResult;
	}
	


			    
	
	private JSONArray getMemberInfo() throws Exception {
		JSONArray jsonArray = new JSONArray();

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println(con);
			System.out.println("succeed opening file");
			Statement stmt = con.createStatement();
			String sql;
			sql = "SELECT * from TBL_MEMBER";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String name = rs.getString("NAME");
				String email = rs.getString("EMAIL");
				int langNative = rs.getInt("LANG_NATIVE");
				JSONObject json = new JSONObject();
			    json.put("name", name);
			    json.put("email", email);
			    json.put("langNative", langNative);
			    json.put("know", "1");
			    json.put("bookmark", "0");
			    jsonArray.add(json);
			    
				System.out.println("NAME : " + name);
				System.out.println("EMAIL : " + email);
			}
          // Clean-up environment
          rs.close();
          stmt.close();
          con.close();
		} catch(Exception e) {
			System.out.println("fail to open mysql");
			e.printStackTrace();
		}
		
		JSONObject json1 = new JSONObject();
		json1.put("name", "moon");
	    json1.put("email", "mun");
	    json1.put("langNative", "달님");
	    json1.put("know", "3");
	    json1.put("bookmark", "1");
	    jsonArray.add(json1);
	    return jsonArray;
	    
	}

	@RequestMapping("/updateMeaning.do")
	public void updateMeaning(@RequestParam("word") String word) {
		logger.info("daA called....");
		logger.info("word : " + word);
		text = "word = " + word;
	}
	
	private String text = "text in DalRead";
	public String getText()
	{
		
		return text;
	}
	
}
