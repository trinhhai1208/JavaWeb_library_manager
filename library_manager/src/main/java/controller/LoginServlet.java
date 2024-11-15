package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminFunction;
import dao.AdminFunctionImpl;
import util.ConnectionPool;
import util.ConnectionPoolImpl;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
        req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
		AdminFunction af = new AdminFunctionImpl(cp);
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		boolean success = af.login(username, password);
		
		if (success) {
			resp.sendRedirect("/library_manager/dashboard");
		} else {
			resp.sendRedirect("/library_manager/login.jsp?error=true");
		}
	}
}
