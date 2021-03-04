import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StudentBalance extends JFrame{
    private JLabel studentBalance;
    private JPanel studentBalancePanel;
    private JButton makePaymentButton;
    private JTextField paymentTextField;
    private int payment = 0;

    public StudentBalance(String title, Student student){
        //Create JFrame
        super(title);
        this.setContentPane(studentBalancePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(300, 350));

        //Displays student's balance
        studentBalance.setText(String.valueOf(student.getBalance()));

        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //Calculate new balance and update person obj
                payment = Integer.parseInt(paymentTextField.getText());
                payment = student.getBalance() - payment;
                student.setBalance(payment);

                try{
                    //Creates connection to sql database
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(SQL_Connection.getURL(), SQL_Connection.getUsername(), SQL_Connection.getPassword());

                    //Stores new payment balance to SQL database
                    String sql = "UPDATE students SET balance_due = ? WHERE id = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, String.valueOf(payment));
                    pst.setString(2, String.valueOf(student.getID()));
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Payment Successfully Made!");
                    con.close();
                    setVisible(false);
                }

                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
    }
}
