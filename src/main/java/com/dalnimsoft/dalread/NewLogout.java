package com.dalnimsoft.dalread;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;

import com.dalnimsoft.etc.Constants;

/**
 * Servlet implementation class NewLogout
 */
public class NewLogout extends HttpServlet {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NewLogout.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String currentURL = request.getParameter(Constants.KEY_CURRENT_URL);
		HttpSession session = request.getSession();
		session.removeAttribute(Constants.KEY_LOGIN_ID);
		session.removeAttribute(Constants.KEY_UID);
		session.removeAttribute(Constants.KEY_NAME);		
		response.sendRedirect(currentURL);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
