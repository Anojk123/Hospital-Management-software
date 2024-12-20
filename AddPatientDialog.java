import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPatientDialog extends JDialog {
    
    public AddPatientDialog(Connection connection) {
        setTitle("Add Patient");
        //setSize(400, 300);
        setBounds(625, 400, 700, 300);
        setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel(" Enter Patient Name:");
        JTextField nameField = new JTextField();
        JLabel ageLabel = new JLabel(" Enter Patient Age:");
        JTextField ageField = new JTextField();
        JLabel genderLabel = new JLabel(" Enter Patient Gender:");
        JTextField genderField = new JTextField();

        JButton addButton = new JButton("Add");
        JButton backButton = new JButton("Back");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();

                try {
                    String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, age);
                    preparedStatement.setString(3, gender);

                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(AddPatientDialog.this, "Patient added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(AddPatientDialog.this, "Failed to add patient.");
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
        add(new JLabel());
        add(addButton);
        add(backButton);
    }
    
}
