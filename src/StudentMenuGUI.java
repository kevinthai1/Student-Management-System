import javax.swing.*;
import java.sql.*;

public class StudentMenuGUI extends JFrame{
    private JPanel studentMenuPanel;
    private JLabel titleLabel;

    public StudentMenuGUI(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(studentMenuPanel);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
