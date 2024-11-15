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

/**
 * Servlet implementation class EditMember
 */
@WebServlet("/edit-member")
public class EditMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
	    res.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		String memid = req.getParameter("memberID");
		
		ConnectionPool cp = new ConnectionPoolImpl();
        MemberDAOFunction<Member> m = new MemberFunctionImpl(cp);
        
        if (memid != null) {        	
        	int member_id = Integer.parseInt(memid);
        	Member member = m.getMemByID(member_id);
        	req.setAttribute("member", member);
        }
        req.getRequestDispatcher("editMember.jsp").forward(req, res);
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    req.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
	    res.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		ConnectionPool cp = new ConnectionPoolImpl();
        MemberDAOFunction<Member> f = new MemberFunctionImpl(cp);
        int member_id = Integer.parseInt(req.getParameter("memberID"));

        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        
        Member m = new Member(member_id,name,address,phone,email);

        
        boolean isSuccess = f.editMember(m);
        
        if (isSuccess) {
            res.sendRedirect(req.getContextPath() + "/members?message=updateSuccess");
        } else {
            // Nếu không thành công, xử lý lỗi ở đây
            // Ví dụ: có thể chuyển hướng về trang lỗi, hoặc hiển thị thông báo lỗi khác
            res.sendRedirect(req.getContextPath() + "/error.jsp");
        }
	}

}
