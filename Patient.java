import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private int patientID;
    private String dob;
    private String patientName;

    public Patient(String dob, String patientName) {
        this.dob = dob;
        this.patientName = patientName;
        
    }


    public List<Doctor> searchDoctorsByDayAndTime(String day, String time) {
        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = connectToDatabase()) {
            String query = "SELECT DISTINCT d.* FROM Doctor d " +
                    "INNER JOIN DoctorSchedule ds ON d.DoctorID = ds.DoctorID " +
                    "WHERE ds.Day = ? AND ds.StartTime <= ? AND ds.EndTime >= ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, day);
                statement.setString(2, time);
                statement.setString(3, time);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Doctor doctor = new Doctor(
                                resultSet.getString("DoctorName"),
                                resultSet.getString("Expertise"));
                        // Set other doctor details as needed
                        doctors.add(doctor);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    public List<Doctor> searchDoctorsByTimeRange(String startTime, String endTime, String[] days) {
        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = connectToDatabase()) {
            String query = "SELECT DISTINCT d.* FROM Doctor d " +
                    "INNER JOIN DoctorSchedule ds ON d.DoctorID = ds.DoctorID " +
                    "WHERE ds.Day IN (?, ?, ?, ?, ?, ?, ?) AND ds.StartTime <= ? AND ds.EndTime >= ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 1; i <= 7; i++) {
                    statement.setString(i, days[i - 1]);
                }
                statement.setString(8, startTime);
                statement.setString(9, endTime);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Doctor doctor = new Doctor(
                        resultSet.getString("DoctorName"),
                        resultSet.getString("Expertise"));
                        // Set other doctor details as needed
                        doctors.add(doctor);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    

    public List<Appointment> filterAppointmentsByDoctorExpertise(String expertise) {
        List<Appointment> appointments = new ArrayList<>();

        try (Connection connection = connectToDatabase()) {
            String query = "SELECT * FROM Appointment a " +
                    "INNER JOIN Doctor d ON a.DoctorID = d.DoctorID " +
                    "WHERE d.Expertise = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, expertise);
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

    public void bookAppointment(Doctor doctor, Date startTime) {
        // Implement logic to book an appointment if the time slot is available
        // Update the database accordingly

        try (Connection connection = connectToDatabase()) {
            // Check if the time slot is available
            String availabilityQuery = "SELECT * FROM Appointment " +
                    "WHERE DoctorID = ? AND StartTime <= ? AND EndTime >= ?";
            try (PreparedStatement availabilityStatement = connection.prepareStatement(availabilityQuery)) {
                availabilityStatement.setInt(1, doctor.getDoctorID());
                availabilityStatement.setDate(2, new java.sql.Date(startTime.getTime()));
                availabilityStatement.setDate(3, new java.sql.Date(startTime.getTime()));
                try (ResultSet availabilityResultSet = availabilityStatement.executeQuery()) {
                    if (availabilityResultSet.next()) {
                        // Time slot is not available
                        System.out.println("Time slot is not available.");
                        return;
                    }
                }
            }

            // Book the appointment
            String bookQuery = "INSERT INTO Appointment (StartTime, EndTime, Status_, PatientID, DoctorID) " +
                    "VALUES (?, ?, 'Scheduled', ?, ?)";
            try (PreparedStatement bookStatement = connection.prepareStatement(bookQuery)) {
                bookStatement.setDate(1, new java.sql.Date(startTime.getTime()));
                // Assuming 1-hour appointment, adjust as needed
                bookStatement.setDate(2, new java.sql.Date(startTime.getTime() + 3600 * 1000));
                bookStatement.setInt(3, this.patientID);
                bookStatement.setInt(4, doctor.getDoctorID());
                bookStatement.executeUpdate();
                System.out.println("Appointment booked successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelAppointment(Appointment appointment) {
        // Implement logic to cancel an appointment if it is cancelable
        // Update the database accordingly

        if (!appointment.isCancelable()) {
            System.out.println("Appointment is not cancelable.");
            return;
        }

        try (Connection connection = connectToDatabase()) {
            String query = "UPDATE Appointment SET Status_ = 'Canceled' WHERE AppointmentID = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, appointment.getAppointmentID());
                statement.executeUpdate();
                System.out.println("Appointment canceled successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public List<Appointment> viewUpcomingAppointments(int days) {
        List<Appointment> appointments = new ArrayList<>();

        try (Connection connection = connectToDatabase()) {
            String query = "SELECT * FROM Appointment WHERE PatientID = ? AND StartTime <= NOW() + INTERVAL ? DAY";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, this.patientID);
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

    public List<Appointment> filterAppointmentsByExpertise(String expertise) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();

        try (Connection connection = connectToDatabase()) {
            String query = "SELECT a.* FROM Appointment a " +
                    "INNER JOIN Doctor d ON a.DoctorID = d.DoctorID " +
                    "WHERE d.Expertise = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, expertise);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

            return appointments;
        
    }

}
    
    // Add other patient-related methods as needed...



   

    

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



    public int getPatientID() {
        return patientID;
    }


    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }


    public String getDob() {
        return dob;
    }


    public void setDob(String dob) {
        this.dob = dob;
    }


    public String getPatientName() {
        return patientName;
    }


    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    public List<Doctor> searchDoctorsByExpertise(String expertise) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchDoctorsByExpertise'");
    }
}
