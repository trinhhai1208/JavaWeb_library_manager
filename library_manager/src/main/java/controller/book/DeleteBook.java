package controller.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookFunctionImpl;
import dao.BookFunction;
import model.Book;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

@WebServlet("/delete-book")
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public DeleteBook(){
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConnectionPool cp = new ConnectionPoolImpl();
        BookFunction f = new BookFunctionImpl(cp);
        
        String idString = req.getParameter("bookId");
        
        if (idString != null) {
        	int id = Integer.parseInt(idString);
        	
        	boolean check = f.deleteT(id);
        	
        	if (check) {
                resp.sendRedirect(req.getContextPath() + "/books?message=deleteSuccess");
        	} else {
                resp.sendRedirect(req.getContextPath() + "/books?error=deleteNotSuccess");
        	}
        } else {
            resp.sendRedirect(req.getContextPath() + "/books?error=invalidid");
        }
        
        
	}
}
