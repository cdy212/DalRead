package com.dalnimsoft.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dalnimsoft.etc.Constants;

@Service
public class MiscServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(MiscServiceImpl.class);
	
	@Autowired
	SqlSession sqlSession; 
	
	
	
	//유저가 있는지 확인하고 있으면 관련정보를 세션에 넣는다.
	public boolean checkLoginInfo(String id, String password, HttpSession session) throws ServletException, NoSuchAlgorithmException {
        boolean status = false;
        
		String md5Pwd = getMD5Password(password);		
		System.out.println("EMAIL : " + id);
		System.out.println("pass : " + password);
		System.out.println("Regist Pwd : "+md5Pwd);
		
		List<Map<String,Object>> arlUserList = sqlSession.selectList("member.loginChk",id);
		String emailInTbl = "";
		String pwdInTbl = "";
		String nameInTbl = ""; 
		int uidInTbl = Constants.UID_DEFAULT;
		
		if(arlUserList.size()>1){
			new Exception("no user id : " + id);
		}
		
		for (Map<String, Object> map : arlUserList) {			

			
			uidInTbl = (int) map.get("UID");
			emailInTbl = (String) map.get("EMAIL");
			pwdInTbl = (String) map.get(Constants.FLD_PWD);
			nameInTbl = (String) map.get(Constants.FLD_NAME);
		}
		
		if ((id.equals(emailInTbl)) && (md5Pwd.equals(pwdInTbl))) {
			if (id.equals(emailInTbl)) {
				session.setAttribute(Constants.KEY_UID, uidInTbl);
				session.setAttribute(Constants.KEY_NAME, nameInTbl);
				status = true;
			}
		}
			
//		try {
//  			Class.forName(DRIVER);
//  		} catch (ClassNotFoundException e1) {
//  			// TODO Auto-generated catch block
//  			e1.printStackTrace();
//  		}		
//		
//		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
//			Statement stmt = con.createStatement();
//			String sql;
//			
//			//sql = "select EMAIL, PWD from test.TBL_MEMBER where EMAIL=\"" + name  +"\" and PWD=\"" + md5Pwd + "\";";
//			sql = "select UID, EMAIL, PWD from test.TBL_MEMBER where EMAIL=\"" + id  +"\"";
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				int uidInTbl = rs.getInt("UID");
//				String emailInTbl = rs.getString("EMAIL") + "";
//				String pwdInTbl = rs.getString("PWD") + "";
//				//TODO md5의 값이 DalTalk에서 넣은거와 다른거 같다...//일단 암호는 비교하지 말고 이메일만 있으면 들어가자
//				if ((id.equals(emailInTbl)) && (md5Pwd.equals(pwdInTbl))) {
//					if (id.equals(emailInTbl)) {
//						session.setAttribute("UID", uidInTbl);
//						status = true;
//					}
//				}
//			}
//			
//	        rs.close();
//	        stmt.close();
//	        con.close();
//		} catch(Exception e) {
//			System.out.println("fail to open mysql");
//			e.printStackTrace();
//		}	
//            
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
