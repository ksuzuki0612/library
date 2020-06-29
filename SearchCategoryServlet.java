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


public class SearchCategoryServlet extends HttpServlet{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res)
            throws ServletException, IOException{

        res.setContentType("text/html;charset=UTF8");
     	PrintWriter out = res.getWriter();
     	
     	out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
        out.println("<title>書籍タイトル検索</title>");
        out.println("</head><body>");
     	
        SqlMethod sql = new SqlMethod();

        
        String category = req.getParameter("searchcategory");
        

        try{
             List<Book> book = sql.searchField(category);
            if (book.isEmpty()){
                res.sendRedirect("noBook.jsp");
            } else {
                for(Book t : book){
                    String ISBN = t.getISBN();
                    out.println(ISBN);
                    req.setAttribute("ISBN", ISBN);
                    String title = t.getTitle();
                    out.println(title);
                    req.setAttribute("title", title);
                    String publisher = t.getPublisher();
                    out.println(publisher);
                    req.setAttribute("publisher", publisher);
                    Date pubdate = t.getPublishDate();
                    out.println(pubdate);
                    req.setAttribute("pubdate", pubdate);
                    String author = t.getStringAuthors();
                    out.println(author);
                    req.setAttribute("author", author);
                    String field = t.getField();
                    out.println(field);
                    req.setAttribute("field", field);
                    int inventory = t.getInventory();
                    out.println(inventory);
                    req.setAttribute("inventory", inventory);
                    int borrowed = t.getBorrowedAmount();
                    out.println(borrowed);
                    req.setAttribute("borrowed", borrowed);
                    RequestDispatcher rd = req.getRequestDispatcher("searchResults.jsp");
                    rd.forward(req, res);

                    
                }
                
            }
        }
        catch(Exception e){
            res.sendRedirect("errorSearch.jsp");
        }
       out.println("</body></html>");
    }
}
