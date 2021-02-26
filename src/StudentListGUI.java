import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class StudentListGUI extends JFrame{
    private JPanel studentListPanel;
    private JTable table1;

    public StudentListGUI(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(studentListPanel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    //Retrieve data from sql and store it in an Arraylist
    public ArrayList<Student> userList(){
        ArrayList<Student> usersList = new ArrayList<>();
        try{
            //Creates connection to sql database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdatabase", "root", "naruto");

            //Creates SQL statement to view student table
            String sql = "SELECT * FROM students";
            Statement pst = con.createStatement();
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next())
            {
                Student student = new Student(rs.getInt("id"), rs.getInt("balance_due"), rs.getString("user_name"), rs.getString("password"),
                        rs.getString("email"), rs.getDate("register_date"), rs.getBoolean("is_admin"));
                usersList.add(student);
            }
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return usersList;
    }

    //Creates the JTable of all users using Arraylist
    private void createUIComponents() {
        ArrayList<Student> list = userList();
        String[] columnNames = {"ID", "Username", "Password", "Email", "Balance Due", "Admin", "Register Date"};
        String[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table1 = new JTable(model);

        Object[] row = new Object[7];
        for(int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getID();
            row[1] = list.get(i).getUserName();
            row[2] = list.get(i).getPassword();
            row[3] = list.get(i).getEmail();
            row[4] = list.get(i).getBalance();
            row[5] = list.get(i).getIsAdmin();
            row[6] = list.get(i).getRegisterDate();
            model.addRow(row);
        }
    }
}
