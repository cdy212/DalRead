package com.dalnimsoft.dalread;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;

import com.dalnimsoft.etc.Constants; 

public class LoginDao {
	private static final String DRIVER = Constants.SERVER_DRIVER;//"com.mysql.jdbc.Driver";
	private static final String URL = Constants.SERVER_URL;//"jdbc:mysql://192.168.0.3/test";
	private static final String USER = Constants.SERVER_USER;//"root";
	private static final String PW = Constants.SERVER_PW;//"dal8121";

	
    public static boolean validate(String name, String pass) throws NoSuchAlgorithmException {        
        boolean status = false;
        
//		String md5Pwd = getMD5Password(pass);
//		System.out.println("EMAIL : " + name);
//		System.out.println("pass : " + pass);
//		System.out.println("Regist Pwd : "+md5Pwd);
//		
//		try {
//  			Class.forName(DRIVER);
//  		} catch (ClassNotFoundException e1) {
//  			// TODO Auto-generated catch block
//  			e1.printStackTrace();
//  		}		
//		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
//			Statement stmt = con.createStatement();
//			String sql;
//			//일단 암호는 비교하지 말고 이메일만 있으면 들어가자
//			//sql = "select EMAIL, PWD from test.TBL_MEMBER where EMAIL=\"" + name  +"\" and PWD=\"" + md5Pwd + "\";";
//			sql = "select EMAIL, PWD from test.TBL_MEMBER where EMAIL=\"" + name  +"\"";
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				String emailInTbl = rs.getString("EMAIL") + "";
////				String pwdInTbl = rs.getString("PWD") + "";
////				if ((name == emailInTbl) && (pass == pwdInTbl)) {
//				if (name.equals(emailInTbl)) {
//					status = true;
//					 HttpSession session=request.getSession();  
//				     session.setAttribute("name",name);  
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
//		
//		
//
////        PreparedStatement pst = null;
////            pst = conn
////                    .prepareStatement("select * from TBL_MEMBER where NAME=? and PWD=?");
////            pst.setString(1, name);
////            pst.setString(2, pass);
////
////            rs = pst.executeQuery();
////            status = rs.next();
            
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


