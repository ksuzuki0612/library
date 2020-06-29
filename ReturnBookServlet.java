import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.logging.Logger;
 
//@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
	Logger logger = Logger.getLogger(ReturnBookServlet.class.getName());
	SqlMethod sql =new SqlMethod();
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        	
            response.setContentType("text/html; charset=Shift_JIS");
            PrintWriter out = response.getWriter();
            
     	    out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
            out.println("<title>更新メニュー</title>");
            out.println("</head><body>");
            
            String ID = request.getParameter("employeeUi");
            String ISBN = request.getParameter("isbnUi");
            
            try{
            	int empID = Integer.parseInt(ID);
            
            	boolean employeeUiCheck = sql.idExistCheck(empID);
            	boolean isbnUiCheck = sql.isbnExistCheck(ISBN);
            
            	if(employeeUiCheck && isbnUiCheck){
                	int a = sql.returnBook(ISBN,empID);
                
                	if(a==0){
            	    	response.sendRedirect("successReturn.jsp");
                	}
                
                	if(a==3){
            	    	response.sendRedirect("failReturn.jsp");
                	}
            	}
            	else{
                	response.sendRedirect("failReturn.jsp");
            	}
        	}
        	catch(Exception e){
        		response.sendRedirect("failReturn.jsp");
        	}
    }
}





