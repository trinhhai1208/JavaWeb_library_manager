package controller.member;

import java.io.IOException;

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

@WebServlet("/delete-member")
public class DeleteMember extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConnectionPool cp = new ConnectionPoolImpl();
        MemberDAOFunction<Member> m = new MemberFunctionImpl(cp);
        
        String idString = req.getParameter("memberID");
        
        if (idString != null) {
        	int id = Integer.parseInt(idString);
        	
        	boolean check = m.delMemberByID(id);
        	
        	if (check) {
                resp.sendRedirect(req.getContextPath() + "/members?message=deleteSuccess");
        	} else {
                resp.sendRedirect(req.getContextPath() + "/members?error=deleteNotSuccess");
        	}
        } else {
            resp.sendRedirect(req.getContextPath() + "/members?error=invalidid");
        }
        
        
	}
}
