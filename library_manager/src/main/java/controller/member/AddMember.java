package controller.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAOFunction;
import dao.MemberFunctionImpl;
import model.Member;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

@WebServlet("/add-member")
public class AddMember extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        req.getRequestDispatcher("addMember.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    req.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
	    resp.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		ConnectionPool cp = new ConnectionPoolImpl();
        MemberDAOFunction<Member> m = new MemberFunctionImpl(cp);
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        
        Member a = new Member(name, address, phone, email);
        boolean isSuccess = m.addMember(a);
        
        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/add-member?message=addSuccess");
        } else {
            // Nếu không thành công, xử lý lỗi ở đây
            // Ví dụ: có thể chuyển hướng về trang lỗi, hoặc hiển thị thông báo lỗi khác
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
        
	}
}
