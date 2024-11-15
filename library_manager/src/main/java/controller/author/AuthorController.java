package controller.author;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuthorFunction;
import dao.AuthorFunctionImpl;
import model.Author;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

/**
 * Servlet implementation class AuthorController
 */
@WebServlet("/authors")
public class AuthorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
        AuthorFunction<Author> m = new AuthorFunctionImpl(cp);
        ArrayList<Author> authors = m.getAuthorList(); // Lấy danh sách members từ database
        req.setAttribute("authors", authors);
        
        // Forward to JSP
        RequestDispatcher dispatcher = req.getRequestDispatcher("/listAuthor.jsp");
        dispatcher.forward(req, res);
	}

}
