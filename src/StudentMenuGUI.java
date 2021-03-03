import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentMenuGUI extends JFrame{
    private JPanel studentMenuPanel;
    private JButton payBalanceButton;
    private JButton viewEditAccountButton;
    private JButton logoutButton;
    private Student student;

    public StudentMenuGUI(String title, Student student){
        //Create JFrame
        super(title);
        this.student = student;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(studentMenuPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(300, 350));


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    JFrame menu = new LoginGUI("Student Management System");
                    menu.setVisible(true);
                    setVisible(false);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });

        viewEditAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    JFrame menu = new StudentAccount("Student Management System", student);
                    menu.setVisible(true);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });

        payBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
