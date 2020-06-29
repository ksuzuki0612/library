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
            out.println("<h1  class="+"flame16"+">貸出数の更新</h1>");
            out.println("<a class="+"btn-square"+" href="+"updateBook.jsp"+" >登録変更メニューに戻る</a>");
            String aISBN = request.getParameter("ISBN");
            String str = request.getParameter("addBorrowedAmount");
            boolean strCheck = checkNull(str);
            boolean aISBNCheck =  checkNull(aISBN);
            if(strCheck && aISBNCheck){
                int addBorrowedAmount = Integer.parseInt(str);
                int s = sql.dbAddBorrowedAmount( aISBN,addBorrowedAmount);
                if(s==0){
                    out.println("<p>更新したい本がありません。</p>");
                }
                if(s==1){
                    out.println("<p>貸出数は更新されました。</p>");
                }
                if(s==3){
                    out.println("<p>エラーが発生しました</p>");
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

