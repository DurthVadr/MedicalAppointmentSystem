import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Appointment {
    private int appointmentID;
    private String startTime;
    private String endTime;
    private String status;
    private int patientID;
    private int nurseID;
    private int doctorID;
    private int roomID;

    public Appointment(String startTime, String endTime, String status, int patientID, int nurseID, int doctorID,
            int roomID) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.patientID = patientID;
        this.nurseID = nurseID;
        this.doctorID = doctorID;
        this.roomID = roomID;
    }

    public void addAppointment() {
        Connection connection = null;
        try {
            connection = connectToDatabase();

            String appointmentQuery = "INSERT INTO Appointment (StartTime, EndTime, Status_, PatientID, NurseID, DoctorID, RoomID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(appointmentQuery);
            statement.setString(1, startTime);
            statement.setString(2, endTime);
            statement.setString(3, status);
            statement.setInt(4, patientID);
            statement.setInt(5, nurseID);
            statement.setInt(6, doctorID);
            statement.setInt(7, roomID);
            statement.executeUpdate();

            System.out.println("Appointment added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // Add other appointment-related methods as needed...



    private void closeConnection(Connection connection) {
        // Implement closeConnection method as needed
    }

    private Connection connectToDatabase() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/cs202_project";
            String user = "root";
            String password = "Thed4rkside"; // Change this to your database password
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
