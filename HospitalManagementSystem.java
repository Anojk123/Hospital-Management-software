import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HospitalManagementSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "mca6";
    private Connection connection;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HospitalManagementSystem().createAndShowGUI());

    }

    public HospitalManagementSystem() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Hospital Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(800, 500);
        frame.setBounds(100, 120, 1200, 600);

        JLabel headerLabel = new JLabel("Welcome To The Hospital Management System Software");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font headerFont = new Font("Georgia", Font.BOLD | Font.ITALIC, 40);
        headerLabel.setFont(headerFont);
        headerLabel.setBorder(new EmptyBorder(50, 10, 30, 10)); // Add padding
        frame.add(headerLabel, BorderLayout.NORTH);

        // Create and set up the content pane
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        JButton addPatientButton = new JButton("Add Patient");
        JButton viewPatientsButton = new JButton("View Patients");
        JButton viewEmployeeByIdButton = new JButton("View Employee By ID");
        JButton addEmployeeButton = new JButton("Add Employee");
        JButton viewEmployeesButton = new JButton("View Employees");
        JButton viewPatientByIdButton = new JButton("View Patient By ID");

        Font buttonFont = new Font(addPatientButton.getFont().getName(), Font.PLAIN, 25); // Adjust size
        addPatientButton.setFont(buttonFont);
        viewPatientsButton.setFont(buttonFont);
        viewEmployeeByIdButton.setFont(buttonFont);
        addEmployeeButton.setFont(buttonFont);
        viewEmployeesButton.setFont(buttonFont);
        viewPatientByIdButton.setFont(buttonFont);

        JLabel footerLabel = new JLabel("Developed By Anoj Kumar");
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font footerFont = new Font("Georgia", Font.BOLD | Font.ITALIC, 30);
        footerLabel.setFont(footerFont);
        footerLabel.setBorder(new EmptyBorder(10, 10, 100, 10)); // Add padding
        frame.add(footerLabel, BorderLayout.SOUTH);

        addPatientButton.addActionListener(e -> showAddPatientDialog());
        viewPatientsButton.addActionListener(e -> showViewPatientsDialog());
        viewPatientByIdButton.addActionListener(e -> showViewPatientByIdDialog());
        addEmployeeButton.addActionListener(e -> showaddEmployeeById());
        viewEmployeesButton.addActionListener(e -> showViewEmployeeDialog());
        viewEmployeeByIdButton.addActionListener(e -> showViewEmployeeByIdDialog());
        // viewDoctorsButton.addActionListener(e -> showViewDoctorDialog());

        panel.add(addPatientButton);
        panel.add(viewPatientsButton);
        panel.add(viewEmployeeByIdButton);
        panel.add(addEmployeeButton);
        panel.add(viewEmployeesButton);
        panel.add(viewPatientByIdButton);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void showAddPatientDialog() {
        new AddPatientDialog(connection).setVisible(true);
    }

    private void showViewPatientsDialog() {
        new ViewPatientsDialog(connection).setVisible(true);
    }

    private void showaddEmployeeById() {
        new addEmployeeById(connection).setVisible(true);
    }

    private void showViewPatientByIdDialog() {
        new ViewPatientByIdDialog(connection).setVisible(true);
    }

    private void showViewEmployeeDialog() {
        new ViewEmployeeDialog(connection).setVisible(true);
    }
    private void showViewEmployeeByIdDialog() {
        new ViewEmployeeByIdDialog(connection).setVisible(true);
    }

    // private void showViewDoctorDialog() {
    //     new ViewDoctorDialog(connection).setVisible(true);
    // }


}