import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Nurse {
    private int nurseID;
    private String nurseName;

    public Nurse(String nurseName) {
        this.nurseName = nurseName;
    }

    public void registerNurse() {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            addUser(); // Use the common addUser method from the User class

            String nurseQuery = "INSERT INTO Nurse (NurseName) VALUES (?)";
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

    // Add other nurse-related methods as needed...

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
