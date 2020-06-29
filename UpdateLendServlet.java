import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.logging.Logger;
 
@WebServlet("/UpdateLendServlet")
public class UpdateLendServlet extends HttpServlet {
	Logger logger = Logger.getLogger(UpdateLendServlet.class.getName());
	SqlMethod sql =new SqlMethod();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        	response.setContentType("text/html; charset=Shift_JIS");
            PrintWriter out = response.getWriter();
        	out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
            out.println("<title>ログイン</title>");
            out.println("<link rel="+"stylesheet"+" href="+"updateD.css"+">");
            out.println("</head><body>");
            String aISBN = request.getParameter("ISBN");
            String str = request.getParameter("addBorrowedAmount");
            boolean strCheck = checkNull(str);
            boolean aISBNCheck =  checkNull(aISBN);
            if(strCheck && aISBNCheck){
                int addBorrowedAmount = Integer.parseInt(str);
                if (addBorrowedAmount >= 0){ 
                    int s = sql.dbAddBorrowedAmount( aISBN,addBorrowedAmount);
                    if(s == 0){
                        out.println("<h1  class="+"errorFlame"+">更新したい本がありません。</h1>");
                    }
                    if(s == 1){
                        out.println("<h1  class="+"flame16"+">貸出数は更新されました。</h1>");
                    }
                    if(s == 3){
                        out.println("<h1  class="+"errorFlame"+">エラーが発生しました</h1>");
                    }
                }
                else{
                    out.println("<h1  class="+"errorFlame"+">初めからやり直してください。</h1>");
                }
            }
            else{
                out.println("<h1  class="+"errorFlame"+">初めからやり直してください。</h1>");
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

