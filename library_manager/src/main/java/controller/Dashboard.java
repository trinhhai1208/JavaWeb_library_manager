package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookFunction;
import dao.BookFunctionImpl;
import dao.LoanFunction;
import dao.LoanFuntionImpl;
import dao.MemberDAOFunction;
import dao.MemberFunctionImpl;
import model.Loan;
import model.Member;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConnectionPool cp = new ConnectionPoolImpl();
        BookFunction bf = new BookFunctionImpl(cp);
        LoanFunction lf = new LoanFuntionImpl(cp);
        
        Integer totalMembers = bf.getTheNumberOfMembers();
        Integer totalBooks = bf.getTheNumberOfBooks();
        Integer loansOfThisMonth = lf.numberOfBookBorrowingsThisMonth();
        Integer loansOfPreviousMonth = lf.numberOfBookBorrowingsPreviousMonth();
        
        ArrayList<Member> membersVIP = bf.memberVIP();

        List<Integer> data = lf.takeTheBorrowedTurnOfTheMonths();
        
        
        
        req.setAttribute("totalMembers", totalMembers);
        req.setAttribute("totalBooks", totalBooks);
        req.setAttribute("loansOfPreviousMonth", loansOfPreviousMonth);
        req.setAttribute("loansOfThisMonth", loansOfThisMonth);
        req.setAttribute("membersVIP", membersVIP);
        req.setAttribute("data", data);
        
        req.getRequestDispatcher("index.jsp").forward(req, resp);

	}

}
