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


public class SearchAuthorServlet extends HttpServlet{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res)
            throws ServletException, IOException{

        res.setContentType("text/html;charset=UTF8");
     	PrintWriter out = res.getWriter();
     	
     	out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
        out.println("<title>書籍タイトル検索</title>");
        out.println("</head><body>");
     	
        SqlMethod sql = new SqlMethod();

        
        String author = req.getParameter("searchauthor");
        

        try{
            List<Book> book = sql.searchAuthor(author);
            if (book.isEmpty()){
                out.println("<a href=" + "searchMenu.jsp" + ">探している著者の書籍がありません。</a>");
            } else {
                for(Book t : book){
                    out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s",
                        "ISBN","Title","Publisher","Publishdate","Author","category","Inventory","Lent out"));
                    out.println(  String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s",
                        t.getISBN() ,t.getTitle() , t.getPublisher() , new SimpleDateFormat("yyyy/MM/dd").format(t.getPublishDate()),
                        t.getStringAuthors() , t.getField() , t.getInventory(), t.getBorrowedAmount() ));
                }
            }
        }
        catch(Exception e){
            out.println("<a href=" + "adminMenuUI.jsp" + ">データベースに繋ぐことが出来ません。</a>");
        }
       out.println("</body></html>");
    }
}
