import java.sql.Date;

public class Student {
    private int id, balance;
    private String userName;
    private String password;
    private String email;
    private Date registerDate;
    private Boolean isAdmin;

    public Student (int id, int balance, String userName, String password, String email, Date registerDate, Boolean isAdmin){
        this.id = id;
        this.balance = balance;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.registerDate = registerDate;
        this.isAdmin = isAdmin;
    }

    public int getID(){
        return id;
    }
    public int getBalance(){
        return balance;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
    public Date getRegisterDate(){
        return registerDate;
    }
    public Boolean getIsAdmin(){
        return isAdmin;
    }
}
