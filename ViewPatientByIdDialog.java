import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewPatientByIdDialog extends JDialog {
     public ViewPatientByIdDialog(Connection connection) {
        setTitle("View Patient By ID");
        //setSize(400, 200);
        setBounds(450, 300, 1000, 300);
        setLayout(new GridLayout(3, 2));

        JLabel idLabel = new JLabel(" Please Enter Patient ID:");
        JTextField idField = new JTextField();
        JTextArea resultArea = new JTextArea();
        //resultArea.setSize(800, 300);
        resultArea.setEditable(false);
        JButton viewButton = new JButton("View");

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int patientId = Integer.parseInt(idField.getText());
                String query = "SELECT * FROM patients WHERE id = ?";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, patientId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int age = resultSet.getInt("age");
                        String gender = resultSet.getString("gender");
                        resultArea
                                .setText(String.format("ID: %d\nName: %s\nAge: %d\nGender: %s", id, name, age, gender));
                    } else {
                        resultArea.setText("No patient found with ID " + patientId);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });
        add(idLabel);
        add(idField);
        add(new JLabel());
       //add(new JLabel());
        add(viewButton);
        add(new JScrollPane(resultArea));
        add(backButton);
    }
}
