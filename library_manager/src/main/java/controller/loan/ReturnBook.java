package controller.loan;

import java.io.IOException;

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

@WebServlet("/returnBook")
public class ReturnBook extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConnectionPool cp = new ConnectionPoolImpl();
		LoanFunction lf = new LoanFuntionImpl(cp);
        
        String idString = req.getParameter("loanId");
//        System.out.println(idString);
        
        if (idString != null) {
        	int id = Integer.parseInt(idString);
//        	System.out.println(id);
        	
        	boolean check = lf.returnBook(id);
        	
        	if (check) {
                resp.sendRedirect(req.getContextPath() + "/list-return-book?message=returnSuccess");
        	} else {
                resp.sendRedirect(req.getContextPath() + "/list-return-book?error=returnNotSuccess");
        	}
        } else {
            resp.sendRedirect(req.getContextPath() + "/list-return-book?error=invalidid");
        } 
	}
}
