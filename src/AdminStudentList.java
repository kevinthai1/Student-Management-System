import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class AdminStudentList extends JFrame{
    private JPanel studentListPanel;
    private JTable table1;

    public AdminStudentList(String title){
        super(title);
        this.setContentPane(studentListPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(1500, 400));
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
                        rs.getString("email"), rs.getDate("register_date"), rs.getBoolean("is_admin"), rs.getString("address"));
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
        String[] columnNames = {"ID", "Username", "Password", "Email", "Balance Due", "Admin", "Register Date", "Address"};
        String[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table1 = new JTable(model);

        Object[] row = new Object[8];
        for(int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getID();
            row[1] = list.get(i).getUserName();
            row[2] = list.get(i).getPassword();
            row[3] = list.get(i).getEmail();
            row[4] = list.get(i).getBalance();
            row[5] = list.get(i).getIsAdmin();
            row[6] = list.get(i).getRegisterDate();
            row[7] = list.get(i).getAddress();
            model.addRow(row);
        }

        //Set column widths
        table1.getColumnModel().getColumn(0).setPreferredWidth(50);
        table1.getColumnModel().getColumn(1).setPreferredWidth(200);
        table1.getColumnModel().getColumn(2).setPreferredWidth(200);
        table1.getColumnModel().getColumn(3).setPreferredWidth(300);
        table1.getColumnModel().getColumn(4).setPreferredWidth(200);
        table1.getColumnModel().getColumn(5).setPreferredWidth(150);
        table1.getColumnModel().getColumn(6).setPreferredWidth(200);
        table1.getColumnModel().getColumn(7).setPreferredWidth(500);
    }
}
