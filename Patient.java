import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient {
    private int patientID;
    private String dob;
    private String patientName;

    public Patient(String dob, String patientName) {
        this.dob = dob;
        this.patientName = patientName;
        
    }

    
    // Add other patient-related methods as needed...



   

    

    private Connection connectToDatabase() throws SQLException {
        // Implement connectToDatabase method from the User class as needed
        return null;
    }

    private void closeConnection(Connection connection) {
        // Implement closeConnection method from the User class as needed
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
}
