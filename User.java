

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    String email;
    String username;
    String password_hash;
    int userTypeCode;

    public User(String email, String username, String password_hash, int userTypeCode) {
        this.email = email;
        this.username = username;
        this.password_hash = password_hash;
        this.userTypeCode = userTypeCode;
    }

    // Other methods for general user operations...

    void addUser() {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            String query = "INSERT INTO user (email, username, password_hash, UserTypeCode) VALUES (?, ?, ?, ?)";
            var statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password_hash);
            statement.setInt(4, userTypeCode);
            statement.executeUpdate();
            System.out.println("User added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
    
    // Other methods for general user operations...

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

    public void registerPatient(String dob) {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            String query = "INSERT INTO user (email, username, password_hash, userTypeCode) VALUES (?, ?, ?, 4)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password_hash);
            
            statement.executeUpdate();

            String patientQuery = "INSERT INTO patient (DOB) VALUES (?)";
            statement = connection.prepareStatement(patientQuery);
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
            String query = "INSERT INTO user (email, username, password_hash, userTypeCode) VALUES (?, ?, ?, 2)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password_hash);
            
            statement.executeUpdate();

            String patientQuery = "INSERT INTO doctor (DOB) VALUES (?)";
            statement = connection.prepareStatement(patientQuery);
            statement.setString(1, expertise);
            statement.executeUpdate();

            System.out.println("Doctor registered successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    public void registerNurse() {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            String query = "INSERT INTO user (email, username, password_hash, userTypeCode) VALUES (?, ?, ?, 3)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password_hash);
            
            statement.executeUpdate();

            

            System.out.println("Nurse registered successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void registerManager() {
        
        Connection connection = null;
        try {
            connection = connectToDatabase();
            String query = "INSERT INTO user (email, username, password_hash, userTypeCode) VALUES (?, ?, ?, 1)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password_hash);
            
            statement.executeUpdate();

            

            System.out.println("Nurse registered successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

       
    }
}