import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RegisterBookServlet extends HttpServlet{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res)
            throws ServletException, IOException{

        res.setContentType("text/html;charset=UTF8");
     	PrintWriter out = res.getWriter();
     	
     	out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
        out.println("<title>書籍登録</title>");
        out.println("</head><body>");
     	
        SqlMethod sql = new SqlMethod();

        
        String ISBN = req.getParameter("regISBN");
        String title = req.getParameter("regTitle");
        String publisher = req.getParameter("regPublisher");
        String publishDate = req.getParameter("regPubdate");
        String field = req.getParameter("regCategory");
        String authors = req.getParameter("regAuthor");
        String inv = req.getParameter("regInv");
        int inventory = Integer.parseInt(inv);
        int borrowedAmount = 0;

        try{
            boolean register = sql.registerBook(ISBN, title, publisher, publishDate, field, authors, inventory, borrowedAmount);
            if (register == true){
                out.println("<a href=" + "adminMenuUI.jsp" + ">書籍が登録されました。</a>");
            } else {
                out.println("<a href=" + "adminMenuUI.jsp" + ">書籍が登録出来ませんでした。</a>");
            }
        }
        catch(Exception e){
            out.println("<a href=" + "adminMenuUI.jsp" + ">データベースに繋ぐことが出来ません。</a>");
            out.println(e);
        }
       out.println("</body></html>");
    }
}
