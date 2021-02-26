import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginGUI extends JFrame{
    private JPanel loginPanel;
    private JTextField userNameField;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JLabel userNameJLabel;
    private JLabel passwordJLabel;
    private JLabel emptyLabel;

    public LoginGUI(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                    //Creates connection to sql database
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdatabase", "root", "naruto");

                    //Reads and stores user's username and password input
                    String sql = "SELECT * FROM students WHERE user_name=? and password=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, userNameField.getText());
                    pst.setString(2, passwordField1.getText());
                    ResultSet rs = pst.executeQuery();

                    //Displays main menu based on user's is_admin
                    if (rs.next()){
                        boolean isAdmin = rs.getBoolean("is_admin");

                        if (isAdmin == false){
                            JFrame menu = new StudentMenuGUI("Student Menu");
                            menu.setVisible(true);
                            setVisible(false);
                        }
                        else{
                            JFrame menu = new AdminMenuGUI("Admin Menu");
                            menu.setVisible(true);
                            setVisible(false);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error, wrong credentials");
                        passwordField1.setText("");
                    }
                    con.close();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
    }
}
