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

    public static void main(String[] args){
        JFrame frame = new LoginGUI("Student Management System");
        frame.setVisible(true);
    }

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
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdatabase", "root", "naruto");

                    String sql = "SELECT * FROM students WHERE user_name=? and password=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, userNameField.getText());
                    pst.setString(2, passwordField1.getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()){
                        JFrame menu = new MainMenuGUI("Main Menu");
                        menu.setVisible(true);
                        setVisible(false);
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
