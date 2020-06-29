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


public class DeleteBookServlet extends HttpServlet{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res)
            throws ServletException, IOException{

        res.setContentType("text/html;charset=UTF8");
     	PrintWriter out = res.getWriter();
     	
     	out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
        out.println("<title>書籍削除</title>");
        out.println("</head><body>");
     	
        SqlMethod sql = new SqlMethod();

        
        String ISBN = req.getParameter("deleteISBN");
        

        try{
            boolean register = sql.deleteBook(ISBN);
            if (register == true){
                res.sendRedirect("successDelete.jsp");
            } else {
                res.sendRedirect("failDelete.jsp");
            }
        }
        catch(Exception e){
            res.sendRedirect("failDelete.jsp");
        }
       out.println("</body></html>");
    }
}
