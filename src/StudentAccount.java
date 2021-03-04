import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentAccount extends JFrame{
    private JTextField usernameTextField;
    private JTextField emailTextField;
    private JTextArea addressTextArea;
    private JPasswordField passwordTextField;
    private JPasswordField passwordTextField2;
    private JPanel studentAccountPanel;
    private JButton applyChangesButton;

    public StudentAccount(String title, Student student){
        //Create JFrame
        super(title);
        this.setContentPane(studentAccountPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(300, 350));

        //Fill in the empty fields
        usernameTextField.setText(student.getUserName());
        emailTextField.setText(student.getEmail());
        addressTextArea.setText(student.getAddress());
        passwordTextField.setText(student.getPassword());
        passwordTextField2.setText(student.getPassword());

        applyChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    //Checks if password is matching
                    if (passwordTextField.getText().equals(passwordTextField2.getText())){
                        //Creates connection to sql database
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection(SQL_Connection.getURL(), SQL_Connection.getUsername(), SQL_Connection.getPassword());

                        //Reads and updates student account information
                        String sql = "UPDATE students SET user_name = ?, password = ?, email = ?, address = ? WHERE id = ?";
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, usernameTextField.getText());
                        pst.setString(2, passwordTextField2.getText());
                        pst.setString(3, emailTextField.getText());
                        pst.setString(4, addressTextArea.getText());
                        pst.setString(5, String.valueOf(student.getID()));
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Changes Applied Successfully!");
                        con.close();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"ERROR: Password does not match");
                    }
                }

                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
    }
}
