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
 * Servlet implementation class EditCategory
 */
@WebServlet("/edit-category")
public class EditCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
	    res.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		String categoryid = req.getParameter("categoryID");
		
		ConnectionPool cp = new ConnectionPoolImpl();
        CategoryFunction<Category> m = new CategoryFunctionImpl(cp);
        
        if (categoryid != null) {        	
        	int category_id = Integer.parseInt(categoryid);
        	Category category = m.getCategoryById(category_id);
        	req.setAttribute("category", category);
        }
        req.getRequestDispatcher("editCategory.jsp").forward(req, res);
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
	    res.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		ConnectionPool cp = new ConnectionPoolImpl();
        CategoryFunction<Category> f = new CategoryFunctionImpl(cp);
        int category_id = Integer.parseInt(req.getParameter("categoryID"));

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        
        Category m = new Category(category_id,name,description);

        
        boolean isSuccess = f.editCategory(m);
        
        if (isSuccess) {
            res.sendRedirect(req.getContextPath() + "/categories?message=updateSuccess");
        } else {
            // Nếu không thành công, xử lý lỗi ở đây
            // Ví dụ: có thể chuyển hướng về trang lỗi, hoặc hiển thị thông báo lỗi khác
            res.sendRedirect(req.getContextPath() + "/error.jsp");
        }
	}

}
