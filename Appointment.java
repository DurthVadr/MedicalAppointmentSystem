import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public class Appointment {
    private int appointmentID;
    private Date startTime;
    private Date endTime;
    private String status;
    private int patientID;
    private int nurseID;
    private int doctorID;
    private int roomID;

    public Appointment(
            Date startTime, Date endTime, String status, int patientID, int nurseID, int doctorID,
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
            statement.setDate(1, startTime);
            statement.setDate(2, endTime);
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

    public boolean isCancelable() {
        // Check if the appointment can be canceled (more than 24 hours left)
        // Implement your logic here
        return true; // Replace with actual logic
    }



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

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getNurseID() {
        return nurseID;
    }

    public void setNurseID(int nurseID) {
        this.nurseID = nurseID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
}