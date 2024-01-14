import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PatientDashboard extends JFrame {
    private final String username;
    private final Patient patient;

    public PatientDashboard(String username, Patient patient) {
        super("Patient Dashboard");
        this.username = username;
        this.patient = patient;

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placePatientComponents(panel);

        setVisible(true);
    }

    private void placePatientComponents(JPanel panel) {
        JLabel welcomeLabel = new JLabel("Welcome, Patient " + username + "!");
        welcomeLabel.setBounds(10, 20, 200, 25);
        panel.add(welcomeLabel);

        JButton viewAppointmentsButton = new JButton("View Appointments");
        viewAppointmentsButton.setBounds(10, 50, 150, 25);
        viewAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAppointments();
            }
        });
        panel.add(viewAppointmentsButton);

        JButton searchByExpertiseButton = new JButton("Search Doctors by Expertise");
        searchByExpertiseButton.setBounds(10, 80, 200, 25);
        searchByExpertiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchDoctorsByExpertise();
            }
        });
        panel.add(searchByExpertiseButton);

        JButton searchByDayAndTimeButton = new JButton("Search Doctors by Day and Time");
        searchByDayAndTimeButton.setBounds(10, 110, 250, 25);
        searchByDayAndTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchDoctorsByDayAndTime();
            }
        });
        panel.add(searchByDayAndTimeButton);

        JButton searchByTimeRangeButton = new JButton("Search Doctors by Time Range");
        searchByTimeRangeButton.setBounds(10, 140, 220, 25);
        searchByTimeRangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchDoctorsByTimeRange();
            }
        });
        panel.add(searchByTimeRangeButton);
    }

    private void viewAppointments() {
        // Implement logic to view patient's appointments
        // For example, display a list of upcoming appointments
        JOptionPane.showMessageDialog(this, "Viewing Patient Appointments");
    }

    private void searchDoctorsByExpertise() {
        String expertise = JOptionPane.showInputDialog(this, "Enter doctor's expertise:");
        if (expertise != null && !expertise.isEmpty()) {
            List<Doctor> doctors = patient.searchDoctorsByExpertise(expertise);
            displaySearchResults(doctors, "Search Results by Expertise");
        }
    }

    private void searchDoctorsByDayAndTime() {
        String day = JOptionPane.showInputDialog(this, "Enter day:");
        String time = JOptionPane.showInputDialog(this, "Enter time:");
        if (day != null && time != null && !day.isEmpty() && !time.isEmpty()) {
            List<Doctor> doctors = patient.searchDoctorsByDayAndTime(day, time);
            displaySearchResults(doctors, "Search Results by Day and Time");
        }
    }

    private void searchDoctorsByTimeRange() {
        String startTime = JOptionPane.showInputDialog(this, "Enter start time:");
        String endTime = JOptionPane.showInputDialog(this, "Enter end time:");
        String days = JOptionPane.showInputDialog(this, "Enter days (comma-separated):");
        if (startTime != null && endTime != null && days != null &&
                !startTime.isEmpty() && !endTime.isEmpty() && !days.isEmpty()) {
            List<Doctor> doctors = patient.searchDoctorsByTimeRange(startTime, endTime, days.split(","));
            displaySearchResults(doctors, "Search Results by Time Range");
        }
    }

    private void displaySearchResults(List<Doctor> doctors, String title) {
        if (doctors.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No doctors found matching the criteria.");
        } else {
            StringBuilder message = new StringBuilder(title + ":\n");
            for (Doctor doctor : doctors) {
                message.append("ID: ").append(doctor.getDoctorID())
                        .append(", Name: ").append(doctor.getDoctorName())
                        .append(", Expertise: ").append(doctor.getExpertise())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, message.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Pass a Patient object to the PatientDashboard constructor
                Patient patient = new Patient("1990-01-01", "John Doe");
                new PatientDashboard("TestPatient", patient);
            }
        });
    }
}
