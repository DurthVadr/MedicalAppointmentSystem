import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DoctorInterface extends JFrame {
    private Doctor doctor;

    public DoctorInterface(Doctor doctor2) {
        super("Doctor Interface");
        this.doctor = doctor2;
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    public DoctorInterface(String username) {
        //TODO Auto-generated constructor stub
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome, Dr. " + doctor.getDoctorName() + "!");
        welcomeLabel.setBounds(10, 20, 300, 25);
        panel.add(welcomeLabel);

        JButton viewAppointmentsButton = new JButton("View Upcoming Appointments");
        viewAppointmentsButton.setBounds(10, 50, 200, 25);
        viewAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewUpcomingAppointments();
            }
        });
        panel.add(viewAppointmentsButton);
    }

    private void viewUpcomingAppointments() {
        // Retrieve upcoming appointments for the doctor and display them
        List<Appointment> appointments = doctor.viewUpcomingAppointments(10); // View appointments for the next 10 days

        if (appointments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No upcoming appointments.");
        } else {
            StringBuilder message = new StringBuilder("Upcoming Appointments:\n");
            for (Appointment appointment : appointments) {
                message.append("ID: ").append(appointment.getAppointmentID())
                        .append(", Start Time: ").append(appointment.getStartTime())
                        .append(", End Time: ").append(appointment.getEndTime())
                        .append(", Status: ").append(appointment.getStatus())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, message.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Pass a Doctor object to the DoctorInterface constructor
                new DoctorInterface(new Doctor("MikdatSağlam", "Büyük Hekim"));
            }
        });
    }
}
