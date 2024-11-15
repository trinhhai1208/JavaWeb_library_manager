package controller.loan;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookFunction;
import dao.BookFunctionImpl;
import dao.LoanFunction;
import dao.LoanFuntionImpl;
import dao.MemberDAOFunction;
import dao.MemberFunctionImpl;
import model.Book;
import model.Loan;
import model.Member;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

@WebServlet("/manage-borrow-book")
public class BorrowBook extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConnectionPool cp = new ConnectionPoolImpl();
        
        MemberDAOFunction<Member> mf = new MemberFunctionImpl(cp);
        BookFunction bf = new BookFunctionImpl(cp);
        LoanFunction lf = new LoanFuntionImpl(cp);
        
        String keyword = req.getParameter("keyword");
        String searchType = req.getParameter("searchType");
        ArrayList<Book> books = null;

        if (keyword != null && searchType != null) {
            switch (searchType) {
                case "category":
                     books = bf.getByCategory(keyword);
                    break;
                case "author":
                     books = bf.getByAuthor(keyword);
                    break;
                case "title":
                    books = bf.getTByTitle(keyword);
                    break;
            }
        } else {
            books = bf.getListT(); // Default book list if no search criteria
        }
        
        ArrayList<Loan> loans = lf.getAllLoan();
        ArrayList<Member> members = mf.getListM();
        
        String member_name = req.getParameter("member_name");
        ArrayList<Member> memberByName = new ArrayList<>();
        if (member_name != null && !member_name.trim().isEmpty()) {
            memberByName = mf.getMemByName(member_name);
        }
        
        // xử lý người dùng được chọn
        HttpSession session = req.getSession();
        Integer selectedMemberId = null;
        Object objSelectedMemberId = session.getAttribute("selectedMemberId");
        
        if (objSelectedMemberId instanceof Integer) {
            selectedMemberId = (Integer) objSelectedMemberId;
        }
        
        if (selectedMemberId != null) {
            int memberId = selectedMemberId; 
            Member selectedMember = mf.getMemByID(memberId);

            req.setAttribute("selectedMember", selectedMember);
            session.setAttribute("selectedMember", selectedMember);
        } else {
            req.setAttribute("selectedMember", null);
        }
        
        // Xử lý danh sách sách được chọn
        ArrayList<Book> selectedBooks = (ArrayList<Book>) session.getAttribute("selectedBooks");
        if (selectedBooks == null) {
            selectedBooks = new ArrayList<>();
        }
        
        String message = (String) session.getAttribute("message");
        if (message != null) {
            req.setAttribute("message", message);
            session.removeAttribute("message");
        }
        
        req.setAttribute("selectedBooks", selectedBooks);
        req.setAttribute("loans", loans);
        req.setAttribute("books", books);
        req.setAttribute("members", members); // Sửa "memebers" thành "members"
        req.setAttribute("memberByName", memberByName);
        req.getRequestDispatcher("manageBorrowBook.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	ConnectionPool cp = new ConnectionPoolImpl();
        
        LoanFunction lf = new LoanFuntionImpl(cp);
    	
    	String selectedMemberIdStr = req.getParameter("selectedMemberId");
        String removeSelectedMember = req.getParameter("removeSelectedMember");
        HttpSession session = req.getSession();
        
        if (removeSelectedMember != null && removeSelectedMember.equals("true")) {
            
            session.removeAttribute("selectedMemberId");
        } else if (selectedMemberIdStr != null && !selectedMemberIdStr.isEmpty()) {
            try {
                Integer selectedMemberId = Integer.parseInt(selectedMemberIdStr);
                
                session.setAttribute("selectedMemberId", selectedMemberId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                
            }
        }
                
        // xử lý sách
        ArrayList<Book> selectedBooks = (ArrayList<Book>) session.getAttribute("selectedBooks");
        if (selectedBooks == null) {
            selectedBooks = new ArrayList<>();
        }

        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    String bookIdToAdd = req.getParameter("bookIdToAdd");
                    if (bookIdToAdd != null) {
                        try {
                            int bookIdToAddInteger = Integer.parseInt(bookIdToAdd);
                            BookFunction bf = new BookFunctionImpl(cp);
                            Book book = bf.getTById(bookIdToAddInteger);
                            if (book != null && !selectedBooks.contains(book)) {
                                selectedBooks.add(book);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                    break;
                case "remove":
                    String bookIdToRemove = req.getParameter("bookIdToRemove");
                    if (bookIdToRemove != null) {
                        try {
                            int bookIdToRemoveInteger = Integer.parseInt(bookIdToRemove);
                            selectedBooks.removeIf(book -> book.getBook_id() == bookIdToRemoveInteger);
                        } catch (NumberFormatException e) {
                        	
                        }
                    }
                    break;
                case "refresh":
                    session.removeAttribute("selectedMemberId");
                    selectedBooks.clear();
                    break;
                case "confirm":
                	int selectedMemberId = (Integer) session.getAttribute("selectedMemberId");
                    ArrayList<Book> selectedBooksToSave = (ArrayList<Book>) session.getAttribute("selectedBooks");
                    int numberOfUnpaidBooks = lf.checkTheNumberOfUnpaidBooks(selectedMemberId);
                    
                    if ((numberOfUnpaidBooks + selectedBooksToSave.size()) > 5) {
                        session.setAttribute("message", "Bạn không được mượn quá 5 cuốn sách.");
//                        session.setAttribute("selectedBooks", selectedBooks);
//                        resp.sendRedirect(req.getContextPath() + "/manage-borrow-book"); // Redirect to clear inputs
                    } else {
                        boolean success = true;
                        for (Book book : selectedBooksToSave) {
                            LocalDate currentDate = LocalDate.now();
                            Date bookBorrowTime = Date.valueOf(currentDate);
                            Date bookReturnTime = Date.valueOf(currentDate.plus(1, ChronoUnit.WEEKS));
                            Loan loan = new Loan(book.getAuthor_id(), selectedMemberId, bookBorrowTime, bookReturnTime, null);
                            
                            boolean added = lf.addLoan(loan);
                            if (!added) {
                                success = false;
                                break;
                            }
                        }
                        
                        if (success) {
                            session.setAttribute("message", "Thêm thành công.");
//                            session.setAttribute("selectedBooks", selectedBooks);
                            session.removeAttribute("selectedMemberId");
                            selectedBooks.clear();
//                            resp.sendRedirect(req.getContextPath() + "/manage-borrow-book"); // Redirect to clear inputs
                        } else {
                            session.setAttribute("message", "Thêm không thành công. Vui lòng thử lại sau.");
//                            session.setAttribute("selectedBooks", selectedBooks);
//                            resp.sendRedirect(req.getContextPath() + "/manage-borrow-book"); // Redirect to clear inputs
                        }
                    }
                    break;
            }
        }
        session.setAttribute("selectedBooks", selectedBooks);
        resp.sendRedirect(req.getContextPath() + "/manage-borrow-book");
    }
}
