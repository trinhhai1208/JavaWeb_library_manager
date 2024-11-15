package controller.category;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryFunction;
import dao.CategoryFunctionImpl;
import model.Category;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/categories")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
        CategoryFunction<Category> m = new CategoryFunctionImpl(cp);
        ArrayList<Category> categories = m.getCategoryList(); // Lấy danh sách members từ database
        req.setAttribute("categories", categories);
        
        // Forward to JSP
        RequestDispatcher dispatcher = req.getRequestDispatcher("/listCategory.jsp");
        dispatcher.forward(req, res);
	}

}
