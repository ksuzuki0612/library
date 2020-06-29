public class ResetPassword{
    SqlMethod sqlmethod = new SqlMethod();

    public boolean checkResetPass(int ID,String pass,String checkPass){
        int empID = ID;
        String password = pass;
        String checkPassword = checkPass;

        if(password == null || checkPassword == null){
			return false;
        }
        else{
            if(password.equals(checkPassword)){
                boolean result = sqlmethod.dbUpdatePassword(empID, password);
                 return result;
            }else{
                return false;    
            }   
        }
    }
}




