import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

public class User {
    String email;
    String username;
    String password_hash;
    String password_salt;
    int userTypeCode;

    public User(String email, String username, String password, int userTypeCode) {
        this.email = email;
        this.username = username;
        this.userTypeCode = userTypeCode;

        // Generate a random salt
        this.password_salt = generateSalt();

        // Hash the password with the salt
        this.password_hash = hashPassword(password, this.password_salt);
    }

    // Other methods for general user operations...

    void addUser() {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            String query = "INSERT INTO user (email, username, password_hash, password_salt, UserTypeCode) VALUES (?, ?, ?, ?, ?)";
            var statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password_hash);
            statement.setString(4, password_salt);
            statement.setInt(5, userTypeCode);
            statement.executeUpdate();
            System.out.println("User added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // Other methods for general user operations...

    public void registerPatient(String dob) {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            addUser(); // Use the common addUser method

            String patientQuery = "INSERT INTO patient (PatientID, DOB) VALUES (LAST_INSERT_ID(), ?)";
            PreparedStatement statement = connection.prepareStatement(patientQuery);
            statement.setString(1, dob);
            statement.executeUpdate();

            System.out.println("Patient registered successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void registerDoctor(String expertise) {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            addUser(); // Use the common addUser method

            String doctorQuery = "INSERT INTO doctor (DoctorID, Expertise) VALUES (LAST_INSERT_ID(), ?)";
            PreparedStatement statement = connection.prepareStatement(doctorQuery);
            statement.setString(1, expertise);
            statement.executeUpdate();

            System.out.println("Doctor registered successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void registerNurse(String nurseName) {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            addUser(); // Use the common addUser method

            String nurseQuery = "INSERT INTO nurse (NurseID, NurseName) VALUES (LAST_INSERT_ID(), ?)";
            PreparedStatement statement = connection.prepareStatement(nurseQuery);
            statement.setString(1, nurseName);
            statement.executeUpdate();

            System.out.println("Nurse registered successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void registerManager(String managerName) {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            addUser(); // Use the common addUser method

            String managerQuery = "INSERT INTO manager (ManagerID, ManagerName) VALUES (LAST_INSERT_ID(), ?)";
            PreparedStatement statement = connection.prepareStatement(managerQuery);
            statement.setString(1, managerName);
            statement.executeUpdate();

            System.out.println("Manager registered successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // Rest of the class remains the same...

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest((password + salt).getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected Connection connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cs202_project";
        String user = "root";
        String password = "Thed4rkside"; // Change this to your database password
        return DriverManager.getConnection(url, user, password);
    }

    protected void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_salt() {
        return password_salt;
    }

    public void setPassword_salt(String password_salt) {
        this.password_salt = password_salt;
    }

    public int getUserTypeCode() {
        return userTypeCode;
    }

    public void setUserTypeCode(int userTypeCode) {
        this.userTypeCode = userTypeCode;
    }
}
