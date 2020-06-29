import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Logger;

/**
 * ログインクラス
 * 
 * @author 平松和貴
 * @see LibarayMain
 */

public class Login {
    Logger logger = Logger.getLogger(Login.class.getName());
    SqlMethod sqlmethod = new SqlMethod();
    
    /**
     * ログインのための従業員IDとパスワードを確認するメソッド
     * @return checkEmpId
     * @throws SQLException
     */
    public int loginCheck(int ID,String pass ) throws SQLException {
    	logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try{
            int empID = ID;
            String password = pass;
            final int checkID = sqlmethod.dbCheckLogin(empID,password);
            return checkID;
        }
        finally{
            logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        }
    }

    /**
     * ユーザーが管理者権限を持っているかを確認するメソッド
     * @param empID
     * @return boolean adminRight
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean checkRight(int empID) throws ClassNotFoundException, SQLException {
    	logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try{
            final int checkID = empID;
            final boolean adminRight = sqlmethod.dbCheckRight(checkID);
            return adminRight;
        }
        finally{
            logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        }
    }
}