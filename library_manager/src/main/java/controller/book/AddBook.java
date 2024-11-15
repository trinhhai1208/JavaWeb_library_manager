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

@WebServlet("/add-book")
public class AddBook extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        req.getRequestDispatcher("addBook.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConnectionPool cp = new ConnectionPoolImpl();
        BookFunction f = new BookFunctionImpl(cp);
        
        String category = req.getParameter("category");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String isbn = req.getParameter("isbn");
        int published_year = Integer.parseInt(req.getParameter("published_year"));
        int number_of_copies = Integer.parseInt(req.getParameter("number_of_copies"));
        
        int author_id = f.addAuthor(author);
        int category_id = f.addCategory(category);
        Book b = new Book(title, isbn, author_id, category_id, published_year, number_of_copies);
        boolean isSuccess = f.addT(b);
        
        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/add-book?message=addSuccess");
        } else {
            // Nếu không thành công, xử lý lỗi ở đây
            // Ví dụ: có thể chuyển hướng về trang lỗi, hoặc hiển thị thông báo lỗi khác
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
        
	}
}
