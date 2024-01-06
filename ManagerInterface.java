import javax.swing.*;
import java.awt.*;

public class ManagerInterface extends JFrame {
    private final Manager manager;

    public ManagerInterface(Manager manager) {
        super("Manager Interface");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.manager = manager;

        JPanel panel = new JPanel();
        add(panel);
        placeManagerComponents(panel);

        setVisible(true);
    }

    private void placeManagerComponents(JPanel panel) {
        panel.setLayout(new GridLayout(2, 1));

        JLabel welcomeLabel = new JLabel("Welcome, ");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(welcomeLabel);

        JButton viewAppointmentsButton = new JButton("View Appointments");
        viewAppointmentsButton.addActionListener(e -> viewAppointments());
        panel.add(viewAppointmentsButton);
    }

    private void viewAppointments() {
        // Implement logic to view appointments for the manager
        // You can show a new JFrame or dialog for displaying appointments
        JOptionPane.showMessageDialog(this, "Viewing Appointments (Manager)", "View Appointments", JOptionPane.INFORMATION_MESSAGE);
    }
}

