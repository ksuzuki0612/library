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


public class BorrowBookServlet extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{

        res.setContentType("text/html;charset=UTF8");
     	PrintWriter out = res.getWriter();
     	
     	out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
        out.println("<title>貸出承認</title>");
        out.println("</head><body>");
     	
        SqlMethod sql = new SqlMethod();

        try{
            String ISBN = req.getParameter("lendISBN");
            String id = req.getParameter("borrowempID");
            int empID = Integer.parseInt(id);
            String borrowFrom = req.getParameter("borrowStart");
            String borrowTill = req.getParameter("borrowEnd");
            boolean lendCheck = sql.borrowBookCheck(ISBN);
        	if(lendCheck == true){
        		boolean register = sql.borrowBook(ISBN, empID, borrowFrom, borrowTill);
            	if (register == true){
            		res.sendRedirect("borrowApproval.jsp");
            	} 
            	else {
            		res.sendRedirect("limitLend.jsp");
            	}
        	}
        	else{
        		res.sendRedirect("allLendOut.jsp");
        	}
        }
        catch(Exception e){
        	res.sendRedirect("noExist.jsp");
        }
       out.println("</body></html>");
    }
}
