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


public class SearchAuthorServlet extends HttpServlet{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res)
            throws ServletException, IOException{

        res.setContentType("text/html;charset=UTF8");
     	PrintWriter out = res.getWriter();
     	
     	out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
        out.println("<title>書籍著者検索</title>");
        out.println("</head><body>");
     	
        SqlMethod sql = new SqlMethod();

        
        String author = req.getParameter("searchauthor");
        

        try{
            ArrayList<Book> book = sql.searchTitle(author);
            if (book.isEmpty()){
                out.println("<a href=" + "searchMenu.jsp" + ">探しているタイトルの書籍がありません。</a>");
            }else if(author.isEmpty()){
                out.println("<a href=" + "searchMenu.jsp" + ">タイトルを正しく入力してください。</a>");   
            } else {
                    req.setAttribute("book", book);
                    RequestDispatcher rd = req.getRequestDispatcher("searchResults.jsp");
                    rd.forward(req, res);
            }
        }
        catch(Exception e){
            out.println("<a href=" + "adminMenuUI.jsp" + ">データベースに繋ぐことが出来ません。</a>");
e.printStackTrace();
out.println(e);
        }
       out.println("</body></html>");
    }
}
