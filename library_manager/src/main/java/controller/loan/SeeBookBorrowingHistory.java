package controller.loan;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoanFunction;
import dao.LoanFuntionImpl;
import dao.MemberDAOFunction;
import dao.MemberFunctionImpl;
import model.Loan;
import model.Member;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

@WebServlet("/memberInfo")
public class SeeBookBorrowingHistory extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConnectionPool cp = new ConnectionPoolImpl();
		LoanFunction lf = new LoanFuntionImpl(cp);
		MemberDAOFunction<Member> mf = new MemberFunctionImpl(cp);
		String memberIdStr = req.getParameter("memberId");
		if (memberIdStr != null) {
			int member_id = Integer.parseInt(memberIdStr);
			
			Member member = mf.getMemByID(member_id);
			ArrayList<Loan> loans = lf.seeBookBorrowingHistory(member_id); 
			
			req.setAttribute("member", member);
			req.setAttribute("loans", loans);
		}
		
		req.getRequestDispatcher("seeBookBorrowingHistory.jsp").forward(req, resp);
	}
}
