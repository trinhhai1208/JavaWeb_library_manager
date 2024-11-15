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
 * Servlet implementation class DeleteCategory
 */
@WebServlet("/delete-category")
public class DeleteCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCategory() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
        CategoryFunction<Category> m = new CategoryFunctionImpl(cp);
        
        String idString = req.getParameter("categoryID");
        
        if (idString != null) {
        	int id = Integer.parseInt(idString);
        	
        	boolean check = m.deleteCategoryByID(id);
        	
        	if (check) {
                res.sendRedirect(req.getContextPath() + "/categories?message=deleteSuccess");
        	} else {
                res.sendRedirect(req.getContextPath() + "/categories?error=deleteNotSuccess");
        	}
        } else {
            res.sendRedirect(req.getContextPath() + "/categories?error=invalidid");
        }
	}

}
