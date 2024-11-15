package controller.author;

import java.io.IOException;
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
 * Servlet implementation class DeleteAuthor
 */
@WebServlet("/delete-author")
public class DeleteAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteAuthor() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
        AuthorFunction<Author> m = new AuthorFunctionImpl(cp);
        
        String idString = req.getParameter("authorID");
        
        if (idString != null) {
        	int id = Integer.parseInt(idString);
        	
        	boolean check = m.deleteAuthorByID(id);
        	
        	if (check) {
                res.sendRedirect(req.getContextPath() + "/authors?message=deleteSuccess");
        	} else {
                res.sendRedirect(req.getContextPath() + "/authors?error=deleteNotSuccess");
        	}
        } else {
            res.sendRedirect(req.getContextPath() + "/members?error=invalidid");
        }
	}

}
