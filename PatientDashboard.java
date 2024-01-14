import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientDashboard extends JFrame {
    private final String username;

    public PatientDashboard(String username) {
        super("Patient Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.username = username;

        JPanel panel = new JPanel();
        add(panel);
        placePatientComponents(panel);

        setVisible(true);
    }

    private void placePatientComponents(JPanel panel) {
        // Implement patient dashboard components and functionalities
        // For example, display patient's upcoming appointments, allow appointment
        // booking, etc.
        // ...

        JLabel welcomeLabel = new JLabel("Welcome, Patient " + username + "!");
        welcomeLabel.setBounds(10, 20, 200, 25);
        panel.add(welcomeLabel);

        JButton viewAppointmentsButton = new JButton("View Appointments");
        viewAppointmentsButton.setBounds(10, 50, 150, 25);
        viewAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to view appointments
                JOptionPane.showMessageDialog(PatientDashboard.this, "Viewing Patient Appointments");
            }
        });
        panel.add(viewAppointmentsButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PatientDashboard("TestPatient");
            }
        });
    }
}
