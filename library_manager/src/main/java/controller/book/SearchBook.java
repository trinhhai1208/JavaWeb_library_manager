package controller.book;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet("/search-book")
public class SearchBook extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = req.getParameter("searchTitle");
		
		ConnectionPool cp = new ConnectionPoolImpl();
        BookFunction f = new BookFunctionImpl(cp);
        
        ArrayList<Book> books = new ArrayList();
        if (title != null) {
        	books = f.getTByTitle(title);
        }
        
        req.setAttribute("books", books);
        
		req.getRequestDispatcher("searchBook.jsp").forward(req, resp);
	}
}
