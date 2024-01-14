import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Doctor {
    private int doctorID;
    private String doctorName;
    private String expertise;

    public Doctor(String doctorName, String expertise) {
        this.doctorName = doctorName;
        this.expertise = expertise;
    }

    public void registerDoctor() {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            addUser(); // Use the common addUser method from the User class

            String doctorQuery = "INSERT INTO Doctor (DoctorName, Expertise) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(doctorQuery);
            statement.setString(1, doctorName);
            statement.setString(2, expertise);
            statement.executeUpdate();

            System.out.println("Doctor registered successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // Add other doctor-related methods as needed...

    private void addUser() {
        // Implement addUser method from the User class as needed
    }

    private Connection connectToDatabase() throws SQLException {
        // Implement connectToDatabase method from the User class as needed
        return null;
    }

    private void closeConnection(Connection connection) {
        // Implement closeConnection method from the User class as needed
    }
}
