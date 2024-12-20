import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewEmployeeDialog extends JDialog {
      public ViewEmployeeDialog(Connection connection) {
        setTitle("View Employees");
        //setSize(600, 400);
        setBounds(580, 280, 700, 300);

        String[] columnNames = { "Employee ID", "Name", "Age", "Gender", "Role" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);       
        add(scrollPane, BorderLayout.CENTER);

        try {
            String query = "SELECT * FROM employees";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String role = resultSet.getString("role");
                tableModel.addRow(new Object[] { id, name, age, gender, role });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
