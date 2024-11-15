package controller.category;

import java.io.IOException;

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
 * Servlet implementation class AddCategory
 */
@WebServlet("/add-category")
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
        req.getRequestDispatcher("addCategory.jsp").forward(req, res);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
	    res.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		ConnectionPool cp = new ConnectionPoolImpl();
        CategoryFunction<Category> m = new CategoryFunctionImpl(cp);
        String name = req.getParameter("name");
        String description = req.getParameter("description");


        
        Category a = new Category(name, description);
        boolean isSuccess = m.addCategory(a);
        
        if (isSuccess) {
            res.sendRedirect(req.getContextPath() + "/add-category?message=addSuccess");
        } else {
            // Nếu không thành công, xử lý lỗi ở đây
            // Ví dụ: có thể chuyển hướng về trang lỗi, hoặc hiển thị thông báo lỗi khác
            res.sendRedirect(req.getContextPath() + "/error.jsp");
        }
	}

}
