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

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet{
//	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	        String email = request.getParameter("email");
//	        try {
//	            // Placeholder for sending email logic
//	            boolean emailSent = EmailUtility.sendPasswordResetEmail(email);
//	            if (emailSent) {
//	                response.sendRedirect("forgot-password.jsp?success=true");
//	            } else {
//	                response.sendRedirect("forgot-password.jsp?error=true");
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            response.sendRedirect("forgot-password.jsp?error=true");
//	        }
//	    }
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy email từ form
        String email = request.getParameter("email");

        try {
            // Gọi hàm gửi email trong lớp EmailUtility
            boolean emailSent = EmailUtility.sendPasswordResetEmail(email);
            if (emailSent) {
                // Chuyển hướng với thông báo thành công
                response.sendRedirect("forgot-password.jsp?success=true");
            } else {
                // Chuyển hướng với thông báo lỗi
                response.sendRedirect("forgot-password.jsp?error=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Chuyển hướng với thông báo lỗi nếu xảy ra exception
            response.sendRedirect("forgot-password.jsp?error=true");
        }
    }
}


