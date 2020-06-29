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
            String allowISBN = request.getParameter("ISBN");
            String str = request.getParameter("inventory");
            boolean strCheck=  checkNull(str);
            boolean allowISBNCheck=  checkNull(allowISBN);
            if(strCheck && allowISBNCheck){
                int addInventory = Integer.parseInt(str);
                if(addInventory >= 0){
                    int s = sql.dbUpdataInventory( allowISBN,addInventory );
                    if(s==0){
                        out.println("<h1  class="+"errorFlame"+">更新したい本がありません。</h1>");
                    }
                    if(s==1){
                        out.println("<h1  class="+"flame16"+">在庫数は更新されました</h1>");
                    }
                    if(s==3){
                        out.println("<h1  class="+"errorFlame"+">エラーが発生しました</h1>");
                    }       
                }
                else{
                    out.println("<h1  class="+"errorFlame"+">初めからやり直してください。</h1>");
                }
            }
            else{
                out.println("<h1  class="+"flame16"+">初めからやり直してください。</h1>");
            }
            out.println("<h3><a href="+" adminMenuUI.jsp"+" class="+"btnChoice"+">管理者メニューに戻る</a></h3>");
            out.println("<h3><a class="+"btnChoice"+" href="+"updateBook.jsp"+" >登録変更メニューに戻る</a></h3>");
            out.println("</body></html>");
    }
    private boolean checkNull(String name) {
        if(name.isEmpty()){
            return false;
        }
        return true;
    }
}

