import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SQL_Connection {
    private static String url, username, password;

    static String getURL() {

        url = "jdbc:mysql://localhost:3306/studentdatabase";
        return (url);
    }

    static String getUsername() {
        username = "root";
        return (username);
    }

    static String getPassword() {
        password = "naruto";
        return (password);
    }

}
