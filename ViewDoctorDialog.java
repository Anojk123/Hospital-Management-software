import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewDoctorDialog extends JDialog{
       public ViewDoctorDialog(Connection connection) {
        setTitle("View Doctors");
        //setSize(600, 400);
        setBounds(580, 280, 700, 300);

        String[] columnNames = { "Doctor ID", "Specialist" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);       
        add(scrollPane, BorderLayout.CENTER);

        try {
            String query = "SELECT * FROM doctors";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
            
                tableModel.addRow(new Object[] { id, name,specialization });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
