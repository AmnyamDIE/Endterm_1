import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentManagementGUI extends JFrame {
    private JTextField textFieldName;
    private JTextField textFieldAge;
    private JTextField textFieldMajor;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JTextArea textAreaOutput;
    private Connection connection;

    public StudentManagementGUI() {
        setTitle("Student Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Initialize GUI components
        textFieldName = new JTextField();
        textFieldAge = new JTextField();
        textFieldMajor = new JTextField();
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        textAreaOutput = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textAreaOutput);

        // Add components to the frame
        add(new JLabel("Name:"));
        add(textFieldName);
        add(new JLabel("Age:"));
        add(textFieldAge);
        add(new JLabel("Major:"));
        add(textFieldMajor);
        add(btnAdd);
        add(btnUpdate);
        add(btnDelete);
        add(scrollPane);

        // Connect to the database
        connectToDatabase();

        // Add action listeners to buttons
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
    }

    private void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/endterm";
            String user = "root";
            String password = "Ermram12DSH47";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addStudent() {
        String name = textFieldName.getText();
        int age = Integer.parseInt(textFieldAge.getText());
        String major = textFieldMajor.getText();

        try {
            String query = "INSERT INTO students (name, age, major) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, major);
            preparedStatement.executeUpdate();
            textAreaOutput.setText("Student added successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            textAreaOutput.setText("Error: Failed to add student.");
        }
    }

    private void updateStudent() {
        // Implement updating student information
    }

    private void deleteStudent() {
        // Implement deleting a student
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementGUI().setVisible(true);
            }
        });
    }
}