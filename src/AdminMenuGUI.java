import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminMenuGUI extends JFrame{
    private JPanel adminPanel;
    private JButton viewStudentsButton;

    public AdminMenuGUI(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(adminPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        viewStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    JFrame menu = new StudentListGUI("Student List");
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
