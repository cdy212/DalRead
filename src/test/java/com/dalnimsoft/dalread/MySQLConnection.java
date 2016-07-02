package com.dalnimsoft.dalread;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class MySQLConnection {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://192.168.0.26/test";
	private static final String USER = "root";
	private static final String PW = "dal8121";
	
	@Test
	public void testConnection() throws Exception {
		Class.forName(DRIVER);
		try ( Connection con = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println(con);
			System.out.println("succeed opening file");
			Statement stmt = con.createStatement();
			String sql;
			sql = "SELECT NAME, EMAIL from TBL_MEMBER";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String name = rs.getString("NAME");
				String email = rs.getString("EMAIL");
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
	      
		
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
