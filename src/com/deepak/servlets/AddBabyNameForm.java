package com.deepak.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddBabyNameForm
 */
@WebServlet("/AddBabyNameForm")
public class AddBabyNameForm extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside method");
		HttpSession session = request.getSession(false);
		System.out.println(session);
		if(session !=null ||session.getAttribute("adminlogin")== null)
		{
			System.out.println("Welcome Add baby name");
			if(session==null||session.getAttribute("adminlogin")==null){
				request.getRequestDispatcher("adminloginform.html").include(request, response);
			}else{
				request.getRequestDispatcher("addbabyname.html").include(request, response);
			}
		}
	}

}
