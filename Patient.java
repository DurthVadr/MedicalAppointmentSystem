import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Patient {
    private int patientID;
    private String dob;
    private String patientName;

    public Patient(String dob, String patientName) {
        this.dob = dob;
        this.patientName = patientName;
    }

    public void registerPatient() {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            addUser(); // Use the common addUser method from the User class

            String patientQuery = "INSERT INTO Patient (DOB, PatientName) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(patientQuery);
            statement.setString(1, dob);
            statement.setString(2, patientName);
            statement.executeUpdate();

            System.out.println("Patient registered successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // Add other patient-related methods as needed...

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
