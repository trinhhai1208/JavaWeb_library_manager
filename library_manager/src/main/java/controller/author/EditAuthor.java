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
 * Servlet implementation class EditAuthor
 */
@WebServlet("/edit-author")
public class EditAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAuthor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
	    res.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		String authorid = req.getParameter("authorID");
		
		ConnectionPool cp = new ConnectionPoolImpl();
        AuthorFunction<Author> m = new AuthorFunctionImpl(cp);
        
        if (authorid != null) {        	
        	int author_id = Integer.parseInt(authorid);
        	Author author = m.getAuthorById(author_id);
        	req.setAttribute("author", author);
        }
        req.getRequestDispatcher("editAuthor.jsp").forward(req, res);
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
	    res.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		ConnectionPool cp = new ConnectionPoolImpl();
        AuthorFunction<Author> f = new AuthorFunctionImpl(cp);
        int author_id = Integer.parseInt(req.getParameter("authorID"));

        String name = req.getParameter("name");
        String biography = req.getParameter("biography");

        
        Author m = new Author(author_id,name,biography);

        
        boolean isSuccess = f.editAuthor(m);
        
        if (isSuccess) {
            res.sendRedirect(req.getContextPath() + "/authors?message=updateSuccess");
        } else {
            // Nếu không thành công, xử lý lỗi ở đây
            // Ví dụ: có thể chuyển hướng về trang lỗi, hoặc hiển thị thông báo lỗi khác
            res.sendRedirect(req.getContextPath() + "/error.jsp");
        }
	}

}
