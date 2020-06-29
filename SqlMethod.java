import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.*;
import java.sql.*;
import java.text.*;
/**
 * @ Sql methods for 書籍管理システム
 * @author Engler Mate Janos
 * @version 2.1
 * @since 1.0
 *bb
 */

public class SqlMethod{

    Logger logger = Logger.getLogger(SqlMethod.class.getName());
    final String url = "jdbc:mysql://localhost/librarySystem"; 
    final String userName = "tester";
    final String pwd = "77Coffee/";

    public void InitializeDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {

        }
    }


    /**
     * 書籍を登録するメソッド。
     * 各変数をDBのbookinfoに入れる
     *
     */

    public boolean registerBook (String ISBN, String title, String publisher,
                              String publishDate, String field, String authors,
                               int inventory, int borrowedAmount)throws SQLException{

        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());

        try{

            String query =
            "INSERT INTO bookinfo (ISBN," +
            "title,"+
            "publisher,"+
            "publish_date,"+
            "category," +
            "author,"+
            "inventory," +
            "borrowed)"+
            "VALUES" +
            "('" + ISBN + "',"+
            " '" + title + "',"+
            " '" + publisher + "',"+
            " '" + publishDate + "',"+
            " '" + field + "',"+
            " '" + authors + "',"+
            " '" + inventory + "',"+
            " '" + borrowedAmount + "') ON DUPLICATE KEY"+
            " UPDATE inventory = inventory +1;";

            
            Connection con = DriverManager.getConnection(url, userName, pwd);
            Statement st = con.createStatement();
            int count = st.executeUpdate(query);
            
           
            st.close();
            con.close();
            
            return true;
            }

            finally{
                    logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
                    }

    }
    /**
     * @author Engler Mate Janos
     * 書籍の貸出が可能か否かくにんするメソッド
     * 書籍が全部貸出されたら終わる。
     *
     *

     */

     public boolean borrowBookCheck(String ISBN)throws SQLException{

        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
    	
        try{

            
            String query = "SELECT inventory, borrowed FROM bookinfo"+
                            " WHERE"+
                            " ISBN ='" + ISBN+ "'  ";
            
            
            Connection con = DriverManager.getConnection(url, userName, pwd);
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int inv = rs.getInt("inventory");
            int out = rs.getInt("borrowed");
            st.close();
            con.close();
            
            if(inv <= out){
                return false;
            }else{
                return true;
                }               
            
            }

            finally{
                    logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
                }

    }
    /** 
     * @author Engler Mate Janos
     * 書籍の貸出を承認するメソッド
     * 従業員は10冊まで貸出出来る
     * 
     * 
    */
     public boolean borrowBook(String ISBN, int empID, String borrowFrom, String borrowTill)throws SQLException{
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
    	
        try{

            String query = "SELECT employee_name FROM employee"+
                            " WHERE"+
                            " employee_id = '" + empID + "'";
            
            
            String query2 ="SELECT COUNT('employee_name') FROM checkout"+
                            " WHERE"+
                            " employee_id='" + empID + "'  ";
            
            
            Connection con = DriverManager.getConnection(url, userName, pwd);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String ename = rs.getString("employee_name");          
           
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(query2);
            rs2.next();
            int empcount = rs2.getInt(1);
            
            if (empcount == 10){
                return false;
            }else{

                String query3 = "SELECT title FROM bookinfo WHERE ISBN ='" + ISBN + "'";
                Statement st3 = con.createStatement();
                ResultSet rs3 = st3.executeQuery(query3);
                rs3.next();
                String title = rs3.getString("title");

                String query4 =
                "INSERT INTO checkout (ISBN,"+
                " title,"+
                " employee_id,"+
                " employee_name,"+
                " borrowed_from,"+
                " borrowed_until)"+
                " VALUES"+
                " ('" + ISBN + "',"+
                " '" + title + "', "+
                "'" + empID + "', "+
                "'" + ename + "',"+
                " '" + borrowFrom + "', '" + borrowTill + "')";

                Statement st4 = con.createStatement();
                int count = st4.executeUpdate(query4);

                String query5 ="UPDATE bookinfo SET borrowed =borrowed+1 "+
                                "WHERE"+
                                " ISBN='" + ISBN + "'";
                Statement st5 = con.createStatement();
                int count2 = st5.executeUpdate(query5);
                st.close();
                con.close();
                
                return true;
                }             
            }

            finally{
                    logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
                }
     }
    /**
     * @author Kazutaka Hiramatsu
     * ログインする時従業員IDとパスワードを確認するメソッド
     *
     */

    public int dbCheckLogin(int empID, String password) throws SQLException {
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        
    try{
        Connection con = DriverManager.getConnection(url, userName, pwd); 
         
        int ID = empID;
        String pass = password;

        String query = "SELECT COUNT('employee_id') FROM passwords "+
                        "WHERE"+
                        " employee_id='" + ID + "'&& password = '"+ pass + "'  ";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        int empcount = rs.getInt(1);
        st.close();
        con.close();

        if(empcount == 0){
            return 0;
        }
        else{
            return ID;
        }         
    }
    finally{
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
}

    /**
     * @author Kazutaka Hiramatsu
     * ログインの後、利用者は管理者権限があるか否確認するメソッド
     *
    */

    public boolean dbCheckRight(int empID) throws ClassNotFoundException, SQLException {
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
    try{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, pwd); 

        int checkID = empID;

        String query = "SELECT administrator_right FROM employee WHERE"+
                        " employee_id='" + checkID + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String admin = rs.getString("administrator_right");
        String check = "Y";
        st.close();
        con.close();
        if(admin.equals(check)){
            return true;
        }
        else{
            return false;
        }
    }
    finally{
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
}
    /**
     * 書籍を著者ごと検索するメソッド
     *
     */
    public List<Book> searchAuthor(String author){

    	logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());

        List<Book> books = new ArrayList<Book>();
      
        try{

            String query = "SELECT ISBN, "+

                            "title, "+

                            "publisher,"+

                            " publish_date,"+

                            " category,"+

                            " author,"+

                            " inventory,"+

                            " borrowed"+

                            " FROM bookinfo "+

                            " WHERE"+

                            " author LIKE '%"+ author +"%'";           
    
            Connection con = DriverManager.getConnection(url, userName, pwd);

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while(rs.next()){

                Book book = new Book(

                    rs.getString(1),

                    rs.getString(2),

                    rs.getString(3),

                    rs.getDate(4),

                    rs.getString(5),

                    this.splitList(rs.getString(6)),

                    rs.getInt(7),

                    rs.getInt(8));

                books.add(book);

              }

            st.close();

            con.close();

        } catch(Exception e) {

            System.out.println(e);

        

        } finally {

            logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());

        }

        return books;

    }


    /**
     * 書籍を分野ごと検索メソッド
     *
     */
    public List<Book> searchField(String category){

    	logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());

        List<Book> books = new ArrayList<Book>();
      
        try{

            String query = "SELECT ISBN, "+

                            "title, "+

                            "publisher,"+

                            " publish_date,"+

                            " category,"+

                            " author,"+

                            " inventory,"+

                            " borrowed"+

                            " FROM bookinfo "+

                            " WHERE"+

                            " category LIKE '%"+ category +"%'";           
    
            Connection con = DriverManager.getConnection(url, userName, pwd);

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while(rs.next()){

                Book book = new Book(

                    rs.getString(1),

                    rs.getString(2),

                    rs.getString(3),

                    rs.getDate(4),

                    rs.getString(5),

                    this.splitList(rs.getString(6)),

                    rs.getInt(7),

                    rs.getInt(8));

                books.add(book);

              }

            st.close();

            con.close();

        } catch(Exception e) {

            System.out.println(e);

        

        } finally {

            logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());

        }

        return books;

    }
    

    
    /**
     * 書籍をタイトルごと検索メソッド
     *
     */
    public List<Book> searchTitle(String title){

    	logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());

        List<Book> books = new ArrayList<Book>();
      
        try{

            String query = "SELECT ISBN, "+

                            "title, "+

                            "publisher,"+

                            " publish_date,"+

                            " category,"+

                            " author,"+

                            " inventory,"+

                            " borrowed"+

                            " FROM bookinfo "+

                            " WHERE"+

                            " title LIKE '%"+ title +"%'";           
    
            Connection con = DriverManager.getConnection(url, userName, pwd);

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while(rs.next()){

                Book book = new Book(

                    rs.getString(1),

                    rs.getString(2),

                    rs.getString(3),

                    rs.getDate(4),

                    rs.getString(5),

                    this.splitList(rs.getString(6)),

                    rs.getInt(7),

                    rs.getInt(8));

                books.add(book);

              }

            st.close();

            con.close();

        } catch(Exception e) {

            System.out.println(e);

        

        } finally {

            logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());

        }

        return books;

    }
    
    public List<String> splitList(String authors){
        String str = authors;
        String[] authorsArray = str.split(",",0);
        List<String> authorsList = Arrays.asList(authorsArray);
        return authorsList;
    }

    /*public List getSearchRecordTitle(){
        return searchRecordTitle;
    }
    */
    /**
     * 貸出書籍を返却するメソッド
     * 入力されたISBNと従業員IDと一致する行を貸出中のDBから削除する
     *
     */
      public int returnBook(String isbn,int id){
    	logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());

        try{

            String query = "DELETE FROM checkout "+
                            "WHERE"+
                            " employee_id = '" + id + "' && ISBN = '" + isbn + "';";

            String query2 ="UPDATE bookinfo SET borrowed = borrowed -1 "+
                            "WHERE "+
                            "ISBN = '" + isbn + "';";

            
            Connection con = DriverManager.getConnection(url, userName, pwd);
            Statement st = con.createStatement();
            int count = st.executeUpdate(query);

            Statement st2 = con.createStatement();
            int count2 = st.executeUpdate(query2);

            st.close();
            con.close();
            int a =0;
            return a;
            }catch(Exception e) {
            	int s = 3;
            	return s;
            }
            finally{
                    logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
                }
    }

    public boolean deleteBook(String ISBN) throws SQLException{
    	logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        
        try{

            
            String query = "SELECT COUNT('ISBN') FROM bookinfo"+
                            " WHERE"+
                            " ISBN='" + ISBN + "'  ";
            String query2 = "DELETE FROM bookinfo "+
                            "WHERE"+
                            " ISBN = '" + ISBN + "'";
            
            Connection con = DriverManager.getConnection(url, userName, pwd);
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int delete = rs.getInt(1);

            
            
            if(delete == 0){
            

                st.close();
                con.close();
                return false;

            }else{

                Statement st2 = con.createStatement();
                int count = st2.executeUpdate(query2);

                
                st.close();
                con.close();
                return true;

            }                       
                }

            finally{
                    logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        }
    }

    /**
     * 書籍の在庫数を更新するメソッド
     *
     */

      public int dbUpdataInventory(String ISBN,int Inventory ){
      	logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try{

            
            String query = "SELECT COUNT('title') FROM bookinfo"+
                            " WHERE"+
                            " ISBN='" + ISBN + "'  ";
            String query2 = "UPDATE bookinfo SET inventory = '" + Inventory + "' "+
                            "WHERE"+
                            " ISBN = '" + ISBN +"'";
            
            Connection con = DriverManager.getConnection(url, userName, pwd);
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int update = rs.getInt(1);
            int a =0 ;
            if(update == 0){
                return a;

            }else{
                a++;
                Statement st2 = con.createStatement();
                int count = st2.executeUpdate(query2);

                System.out.println("在庫数は更新されました。");
                st.close();
                con.close();
                }
                return a;
            
            
            }catch(Exception e) { 
                int s =3;
                return s;
            }

            finally{
                    logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
                }
    }
    /**
     * 書籍の貸出数を更新するメソッド
     *
     */

     public int dbAddBorrowedAmount(String ISBN,int addBorrowedAmount ){
    	logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());

        try{

            String query = "SELECT COUNT('title') FROM bookinfo"+
                            " WHERE"+
                            " ISBN='" + ISBN + "'  ";
            String query2 = "UPDATE bookinfo SET borrowed = '" + addBorrowedAmount + "' "+
                            "WHERE"+
                            " ISBN = '" + ISBN +"'";

            
            Connection con = DriverManager.getConnection(url, userName, pwd);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int update = rs.getInt(1);
            int a = 0;
            if(update == 0){
               return a;
            }else{
                a++;
                Statement st2 = con.createStatement();
                int count = st.executeUpdate(query2);

                st.close();
                con.close();
                }
                return a;

            
            }catch(Exception e) { 
                int s = 3;
                return s;
            }

            finally{
                    logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
                }

    }
    /**
     * パスワードを更新するメソッド
     *
     */
     public boolean dbUpdatePassword(int empID,String password){

        try{

            String query = "SELECT COUNT('employee_id') FROM passwords"+
                            " WHERE"+
                            " employee_id = '" + empID + "'  ";
            String query2 = "UPDATE passwords SET password = '" + password + "'"+
                            " WHERE"+
                            " employee_id = '" + empID +"'";

        
        Connection con = DriverManager.getConnection(url, userName, pwd);
        
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        int update = rs.getInt(1);

        if(update == 0){
            return false;
        }else{

            Statement st2 = con.createStatement();
            int count = st2.executeUpdate(query2);

            st.close();
            con.close();

            return true;
            }
        }
        catch(Exception e) { 
            return false;
        }
    }
    
     /**
     * 従業員IDが存在するかを調べるメソッド
     *
     */
     public boolean idExistCheck(int empID){
        try{
            String query = "SELECT COUNT('employee_id') FROM passwords"+
                            " WHERE"+
                            " employee_id = '" + empID + "'  ";
        
        	Connection con = DriverManager.getConnection(url, userName, pwd);
        
        	Statement st = con.createStatement();
        	ResultSet rs = st.executeQuery(query);
        	rs.next();
       		int checkExist = rs.getInt(1);

        	if(checkExist == 0){
            	return false;
       		}
       		else{
            	return true;
            }
        }
        catch(Exception e) { 
            return false;
        }
    }
    
     /**
     * ISBNが存在するかを調べるメソッド
     *
     */
     public boolean isbnExistCheck(String ISBN){
        try{
            String query = "SELECT COUNT('ISBN') FROM checkout"+
                            " WHERE"+
                            " ISBN = '" + ISBN + "'  ";
        
        	Connection con = DriverManager.getConnection(url, userName, pwd);
        
        	Statement st = con.createStatement();
        	ResultSet rs = st.executeQuery(query);
        	rs.next();
       		int checkExist = rs.getInt(1);

        	if(checkExist == 0){
            	return false;
       		}
       		else{
            	return true;
            }
        }
        catch(Exception e) { 
            return false;
        }
    }



}
