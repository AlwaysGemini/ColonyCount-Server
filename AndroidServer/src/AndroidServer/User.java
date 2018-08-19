package AndroidServer;

public class User implements java.io.Serializable{
    private String UserAccount;
    private String UserPassword;

    public User(String userAccount,String userPassword){
        UserAccount = userAccount;
        UserPassword = userPassword;
    }
    public String getUserAccount() {
        return UserAccount;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserAccount(String userAccount) {
        UserAccount = userAccount;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }
}
