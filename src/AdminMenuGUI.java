import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminMenuGUI extends JFrame{
    private JPanel adminPanel;
    private JButton viewStudentsButton;
    private JButton logoutButton;

    public AdminMenuGUI(String title){
        //Create JFrame
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(adminPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(300, 350));

        viewStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    JFrame menu = new AdminStudentList("Student List");
                    menu.setVisible(true);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });

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
    }
}
