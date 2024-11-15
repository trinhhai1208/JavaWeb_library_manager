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

/**
 * Servlet implementation class DetailMember
 */
@WebServlet("/detail-member")
public class DetailMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String memid = req.getParameter("memberID");
		
		ConnectionPool cp = new ConnectionPoolImpl();
        MemberDAOFunction<Member> m = new MemberFunctionImpl(cp);
        Member member = new Member();

        if (memid != null) {        	
        	int member_id = Integer.parseInt(memid);
        		member = m.getMemByID(member_id);
        	req.setAttribute("member", member);
        	
        }
        req.getRequestDispatcher("detailMember.jsp").forward(req, res);
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
