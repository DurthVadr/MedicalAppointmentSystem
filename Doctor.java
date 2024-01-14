import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public boolean isAvailableOnDayAndTime(String day, String time) {
        // Check if the doctor is available on a specific day and time
        // Implement your logic here
        return true; // Replace with actual logic
    }

    public List<Appointment> viewUpcomingAppointments(int days) {
        // Implement SQL query to get upcoming appointments for the doctor in the next
        // 'days' days
        // Return a list of Appointment objects
        List<Appointment> appointments = new ArrayList<>();

        try (Connection connection = connectToDatabase()) {
            // Example query (modify as per your database schema)
            String query = "SELECT * FROM Appointment WHERE PatientID = ? AND StartTime <= NOW() + INTERVAL ? DAY";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, this.doctorID); // Assuming doctorID is a field in the Doctor class
                statement.setInt(2, days);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Appointment appointment = new Appointment(
                                resultSet.getDate("StartTime"),
                                resultSet.getDate("EndTime"),
                                resultSet.getString("Status_"),
                                resultSet.getInt("PatientID"),
                                resultSet.getInt("NurseID"),
                                resultSet.getInt("DoctorID"),
                                resultSet.getInt("RoomID"));
                        // Set other appointment details as needed
                        appointments.add(appointment);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public List<Appointment> filterAppointmentsByPatientName(String patientName) {
        // Implement SQL query to get appointments based on patient's name
        // Return a list of Appointment objects
        List<Appointment> appointments = new ArrayList<>();

        try (Connection connection = connectToDatabase()) {
            // Example query (modify as per your database schema)
            String query = "SELECT * FROM appointments WHERE patient_name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, patientName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Appointment appointment = new Appointment(
                                resultSet.getDate("StartTime"),
                                resultSet.getDate("EndTime"),
                                resultSet.getString("Status_"),
                                resultSet.getInt("PatientID"),
                                resultSet.getInt("NurseID"),
                                resultSet.getInt("DoctorID"),
                                resultSet.getInt("RoomID"));
                        // Set other appointment details as needed
                        appointments.add(appointment);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
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


    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
}
