import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager {
    private int managerID;
    private String managerName;

    public Manager(String managerName) {
        this.managerName = managerName;
    }

    public void registerManager() {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            addUser(); // Use the common addUser method from the User class

            String managerQuery = "INSERT INTO Manager (ManagerName) VALUES (?)";
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

   public void viewPatientStatistics() {
        String sql = "SELECT COUNT(*), department FROM Patient " +
                "INNER JOIN User ON Patient.UserID = User.UserID " +
                "INNER JOIN Nurse ON User.UserID = Nurse.UserID " +
                "GROUP BY department";

        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int patientCount = resultSet.getInt(1);
                String department = resultSet.getString(2);
                System.out.println("Department: " + department + ", Patient Count: " + patientCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAppointmentRoomStatistics() {
        String sql = "SELECT Room.RoomID, COUNT(Appointment.RoomID) as AppointmentCount " +
                "FROM Room LEFT JOIN Appointment ON Room.RoomID = Appointment.RoomID " +
                "GROUP BY Room.RoomID";

        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int roomID = resultSet.getInt("RoomID");
                int appointmentCount = resultSet.getInt("AppointmentCount");
                System.out.println("Room ID: " + roomID + ", Appointment Count: " + appointmentCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewNurseAssignmentRatios() {
        String sql = "SELECT Nurse.department, COUNT(RoomAssignment.NurseID) AS AssignmentCount " +
                "FROM Nurse LEFT JOIN RoomAssignment ON Nurse.NurseID = RoomAssignment.NurseID " +
                "GROUP BY Nurse.department";

        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String department = resultSet.getString("department");
                int assignmentCount = resultSet.getInt("AssignmentCount");
                System.out.println("Department: " + department + ", Assignment Count: " + assignmentCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addUser() {
        // Implement addUser method from the User class as needed
    }

    private Connection connectToDatabase() throws SQLException {
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

    protected void closeStatement(PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
