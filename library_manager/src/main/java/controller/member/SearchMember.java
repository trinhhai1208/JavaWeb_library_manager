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
 * Servlet implementation class SearchMember
 */
@WebServlet("/search-member")
public class SearchMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
	    res.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		String name = req.getParameter("searchName");
		
		ConnectionPool cp = new ConnectionPoolImpl();
        MemberDAOFunction<Member> f = new MemberFunctionImpl(cp);
        
        ArrayList<Member> members = new ArrayList();
        if (name != null) {
        	members = f.getMemByName(name);
        }
        
        req.setAttribute("members", members);
        
		req.getRequestDispatcher("searchMember.jsp").forward(req, res);
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
