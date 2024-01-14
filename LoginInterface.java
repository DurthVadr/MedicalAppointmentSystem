import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginInterface extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginInterface() {
        super("Login");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeLoginComponents(panel);

        setVisible(true);
    }

    private void placeLoginComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        panel.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(100, 80, 80, 25);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignUpInterface();
            }
        });
        panel.add(signUpButton);
    }

    private void performLogin() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        // Perform login logic here
        // Determine user type based on login credentials

        // Example: Redirect to Patient or Medical Staff dashboard based on userType
        int userType = determineUserType(username, password);

        if (userType == 1) { // Assuming userTypeCode for Patient is 1
            openManagerDashboard(username);
        } else if (userType == 2) { // Assuming userTypeCode for Medical Staff is 2
            openDoctorDashboard(username);
        } else if (userType == 3) { // Assuming userTypeCode for Medical Staff is 3
            openNurseDashboard(username);

        } 
        else if (userType == 4) { // Assuming userTypeCode for Medical Staff is 4
            openPatientDashboard(username);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
        
       
        

           
    }

    private void openSignUpInterface() {
        // Open the SignUpInterface
        new PatientInterface();
        // Close the current login interface if needed
        dispose();
    }

    private int determineUserType(String username, String password) {
        try {
            Connection connection = connectToDatabase();

            // Query to retrieve user information based on username
            String query = "SELECT userTypeCode, password_hash, password_salt FROM user WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userTypeCode = resultSet.getInt("userTypeCode");
                String storedPasswordHash = resultSet.getString("password_hash");
                String storedPasswordSalt = resultSet.getString("password_salt");

                // Validate the provided password against the stored hash and salt
                if (validatePassword(password, storedPasswordHash, storedPasswordSalt)) {
                    return userTypeCode;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return 0 for an invalid user or incorrect password
        return 0;
    }

    private boolean validatePassword(String providedPassword, String storedHash, String storedSalt) {
        // Use the provided password, stored hash, and stored salt to validate the
        // password
        String hashedProvidedPassword = PasswordUtils.hashPassword(providedPassword, storedSalt);
        return storedHash.equals(hashedProvidedPassword);
    }

    

    // You'll need to modify the connectToDatabase method based on your database configuration

    private Connection connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cs202_project";
        String user = "root";
        String password = "Thed4rkside"; // Change this to your database password
        return DriverManager.getConnection(url, user, password);
    }


    
    private void openManagerDashboard(String username) {
        // Implement logic to open the Manager dashboard
        new ManagerInterface(username);
    }

    private void openDoctorDashboard(String username) {
        // Implement logic to open the Doctor dashboard
        new DoctorInterface(username);
    }

    private void openNurseDashboard(String username) {
        // Implement logic to open the Nurse dashboard
        new NurseInterface(username);
    }


    private void openPatientDashboard(String username) {
        // Implement logic to open the Patient dashboard
        new PatientDashboard(username);
    }


    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginInterface();
            }
        });
    }
}
