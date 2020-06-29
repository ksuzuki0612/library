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


public class LoginServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)
            throws ServletException, IOException{

        res.setContentType("text/html;charset=UTF8");
     	PrintWriter out = res.getWriter();
     	
     	out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
        out.println("<title>ログイン</title>");
        out.println("</head><body>");
     	
        Login login = new Login();

        
        String strID = req.getParameter("empID");
        String password = req.getParameter("password");
        
        try{
            int intID = Integer.parseInt(strID);
           	int ID = login.loginCheck(intID, password);
            

            if (ID == 0) {
                res.sendRedirect("wrongPass.jsp");
                return;
            }

            boolean adminRight = login.checkRight(ID);

            if(adminRight == true){
                res.sendRedirect("choiceMenuAdmin.jsp"); 
            }
            else{
               res.sendRedirect("choiceMenuUser.jsp");
            }
        }
        catch(Exception e){
            res.sendRedirect("wrongPass.jsp");
        }
       out.println("</body></html>");
    }
}
