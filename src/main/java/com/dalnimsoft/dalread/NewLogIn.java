package com.dalnimsoft.dalread;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;

import com.dalnimsoft.etc.Constants;

/**
 * Servlet implementation class NewLogIn
 */
public class NewLogIn extends HttpServlet {
	//TODO : 이건 왜 에러가 날까...
//	private static final Logger logger = LoggerFactory.getLogger(NewLogIn.class);
	
	private static final long serialVersionUID = 1L;
	private static final String DRIVER = Constants.SERVER_DRIVER;//"com.mysql.jdbc.Driver";
	private static final String URL = Constants.SERVER_URL;//"jdbc:mysql://192.168.0.3/test";
	private static final String USER = Constants.SERVER_USER;//"root";
	private static final String PW = Constants.SERVER_PW;//"dal8121";

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewLogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("ID"); //메일주소
		String password = request.getParameter("PASSWORD");
		String currentURL = request.getParameter("CURRENT_URL");
		
		try {
			HttpSession session = request.getSession();
			if (checkLoginInfo(id, password, session)) {
//				if (checkLoginInfo(id, getMD5Password(password), session)) {
				session.setAttribute("LOGIN_ID", id);
				Integer uid = (Integer)session.getAttribute("UID"); 
				Language lang = getLangCreator(uid);		
				lang.setUID(uid);				
				lang.createUserDicIfNotExist();
			} else {
				session.removeAttribute("LOGIN_ID");
				session.removeAttribute("UID");
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(currentURL);
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
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean checkLoginInfo(String id, String password, HttpSession session) throws ServletException, NoSuchAlgorithmException {
        boolean status = false;
        
		String md5Pwd = getMD5Password(password);		
		System.out.println("EMAIL : " + id);
		System.out.println("pass : " + password);
		System.out.println("Regist Pwd : "+md5Pwd);
		
		try {
  			Class.forName(DRIVER);
  		} catch (ClassNotFoundException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}		
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			Statement stmt = con.createStatement();
			String sql;
			
			//sql = "select EMAIL, PWD from test.TBL_MEMBER where EMAIL=\"" + name  +"\" and PWD=\"" + md5Pwd + "\";";
			sql = "select UID, EMAIL, PWD from test.TBL_MEMBER where EMAIL=\"" + id  +"\"";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int uidInTbl = rs.getInt("UID");
				String emailInTbl = rs.getString("EMAIL") + "";
				String pwdInTbl = rs.getString("PWD") + "";
				//TODO md5의 값이 DalTalk에서 넣은거와 다른거 같다...//일단 암호는 비교하지 말고 이메일만 있으면 들어가자
				if ((id.equals(emailInTbl)) && (md5Pwd.equals(pwdInTbl))) {
					if (id.equals(emailInTbl)) {
						session.setAttribute("UID", uidInTbl);
						status = true;
					}
				}
			}
			
	        rs.close();
	        stmt.close();
	        con.close();
		} catch(Exception e) {
			System.out.println("fail to open mysql");
			e.printStackTrace();
		}	
		
		
		

//        PreparedStatement pst = null;
//            pst = conn
//                    .prepareStatement("select * from TBL_MEMBER where NAME=? and PWD=?");
//            pst.setString(1, name);
//            pst.setString(2, pass);
//
//            rs = pst.executeQuery();
//            status = rs.next();
            
        return status;
	}
	
	public static String getMD5Password(String normalPwd) throws NoSuchAlgorithmException {
		StringBuffer retPassword = new StringBuffer();
		
		MessageDigest md5Msg = MessageDigest.getInstance("MD5");
		md5Msg.update(normalPwd.getBytes());
		byte[] md5Code = md5Msg.digest();
		for ( int i=0; i<md5Code.length; i++ ) {
			String tempChar = String.format("%02x", 0xff&(char)md5Code[i]);
			retPassword.append(tempChar);
		}
		
		return retPassword.toString();
	}
}
