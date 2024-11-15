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
import model.Loan;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

@WebServlet("/overdue-book")
public class OverdueBook extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
		LoanFunction lf = new LoanFuntionImpl(cp);
		
		ArrayList<Loan> overdueBooks = lf.overdueBooks();
		req.setAttribute("overdueBooks", overdueBooks);
		req.getRequestDispatcher("manageOverdueBook.jsp").forward(req, resp);

	}
}
