import library.Book;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SearchTitleServlet extends HttpServlet{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res)
            throws ServletException, IOException{

        res.setContentType("text/html;charset=UTF8");
     	PrintWriter out = res.getWriter();
     	
     	out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
        out.println("<title>書籍タイトル検索</title>");
        out.println("</head><body>");
     	
        SqlMethod sql = new SqlMethod();
        
        String title = req.getParameter("searchtitle");
        
        try{
            ArrayList<Book> book = sql.searchTitle(title);
            if (book.isEmpty()){
                res.sendRedirect("noBook.jsp");
            }else if(title.isEmpty()){
                res.sendRedirect("errorSearch.jsp");
            } else {
                    req.setAttribute("book", book);
                    RequestDispatcher rd = req.getRequestDispatcher("searchResults.jsp");
                    rd.forward(req, res);
            }
        }
        catch(Exception e){
            res.sendRedirect("errorSearch.jsp");
        }
       out.println("</body></html>");
    }
}
