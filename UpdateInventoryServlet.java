import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.logging.Logger;
 
//@WebServlet("/UpdateInventoryServlet")//[1]
public class UpdateInventoryServlet extends HttpServlet {//[2]
	Logger logger = Logger.getLogger(UpdateInventoryServlet.class.getName());
	SqlMethod sql =new SqlMethod();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {//[3]
        	response.setContentType("text/html; charset=Shift_JIS");//[4]
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
            out.println(" <link rel= "+"stylesheet"+" href="+"updateD.css"+">");
            out.println("<title>在庫変更</title>");
            out.println("</head><body>");
            out.println("<h1  class="+"flame16"+">書籍在庫数の更新</h1>");
            out.println("<a class="+"btn-square"+" href="+"updateBook.jsp"+" >登録変更メニューに戻る</a>");
            String allowISBN = request.getParameter("ISBN");
            String str = request.getParameter("inventory");
            boolean strCheck=  checkNull(str);
            boolean allowISBNCheck=  checkNull(allowISBN);
            if(strCheck && allowISBNCheck){
                int addInventory = Integer.parseInt(str);
                int s = sql.dbUpdataInventory( allowISBN,addInventory );
                if(s==0){
                    out.println("<p>更新したい本がありません。</p>");
                }
                if(s==1){
                    out.println("<p>在庫数は更新されました。</p>");
    
                }
                if(s==3){
                    out.println("<p>在庫数は更新されました。</p>");
                }       
            }
            else{
                out.println("<p>初めからやり直してください。</p>");
            }
            out.println("</body></html>");
    }
    private boolean checkNull(String name) {
        if(name.isEmpty()){
            return false;
        }
        return true;
    }
}

