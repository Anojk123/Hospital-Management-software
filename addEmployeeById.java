import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addEmployeeById extends JDialog{
    public addEmployeeById(Connection connection) {
        setTitle("Add Employees");
        // setSize(400, 300);
        setBounds(580, 280, 700, 300);
        setLayout(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel(" Enter Employee Name:");
        JTextField nameField = new JTextField();
        JLabel ageLabel = new JLabel(" Enter Employee Age:");
        JTextField ageField = new JTextField();
        JLabel genderLabel = new JLabel(" Enter Employee Gender:");
        JTextField genderField = new JTextField();
        JLabel roleLabel = new JLabel(" Enter Employee Role");
        JTextField roleField = new JTextField();

        JButton backButton = new JButton("Back");
        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                String role = roleField.getText();

                try {
                    String query = "INSERT INTO employees(name, age, gender,role) VALUES(?, ?, ?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, age);
                    preparedStatement.setString(3, gender);
                    preparedStatement.setString(4, role);

                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(addEmployeeById.this,
                                "Employee added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(addEmployeeById.this,
                                "Failed to add Employee.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });

        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(genderLabel);
        add(genderField);
        add(roleLabel);
        add(roleField);
        add(new JLabel());
        add(addButton);
        add(backButton);

    }
    
}
