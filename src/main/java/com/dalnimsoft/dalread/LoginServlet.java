package com.dalnimsoft.dalread;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dalnimsoft.dalread.LoginDao;

public class LoginServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  

        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        
        String n=request.getParameter("username");  
        String p=request.getParameter("userpass"); 
        
        HttpSession session = request.getSession(false);
        if(session!=null)
        session.setAttribute("name", n);

        try {
			if(LoginDao.validate(n, p)){  
			    RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/welcome.jsp");  
			    rd.forward(request,response);  
			}  
			else{  
			    out.print("<p style=\"color:red\">Sorry username or password error</p>");  
			    session.invalidate();
			    RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/index.jsp");  
			    rd.include(request,response);  
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			session.invalidate();
			e.printStackTrace();
		}  

        out.close();  
    }  
} 