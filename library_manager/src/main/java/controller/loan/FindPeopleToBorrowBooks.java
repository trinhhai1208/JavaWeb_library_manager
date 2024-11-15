package controller.loan;

import java.io.IOException;
import java.util.ArrayList;

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
import model.Book;
import model.Loan;
import model.Member;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

@WebServlet("/bookInfo")
public class FindPeopleToBorrowBooks extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConnectionPool cp = new ConnectionPoolImpl();
		LoanFunction lf = new LoanFuntionImpl(cp);
//		MemberDAOFunction<Member> mf = new MemberFunctionImpl(cp);
		BookFunction bf = new BookFunctionImpl(cp);
		
		String bookIdStr = req.getParameter("bookId");
		if (bookIdStr != null) {
			int book_id = Integer.parseInt(bookIdStr);
			
			Book book = bf.getTById(book_id);
			ArrayList<Loan> loans = lf.findPeopleToBorrowBooks(book_id); 
			
			req.setAttribute("book", book);
			req.setAttribute("loans", loans);
		}
		
		req.getRequestDispatcher("findPeopleToBorrowBooks.jsp").forward(req, resp);
	}

}
