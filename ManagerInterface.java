import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerInterface extends JFrame {
    private final String username;

    public ManagerInterface(String username) {
        super("Manager Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.username = username;

        JPanel panel = new JPanel();
        add(panel);
        placeManagerComponents(panel);

        setVisible(true);
    }

    private void placeManagerComponents(JPanel panel) {
        // Implement manager dashboard components and functionalities
        // For example, display admin statistics, add doctor/nurse, etc.
        // ...

        JLabel welcomeLabel = new JLabel("Welcome, Manager " + username + "!");
        welcomeLabel.setBounds(10, 20, 200, 25);
        panel.add(welcomeLabel);

        JButton viewAdminStatisticsButton = new JButton("View Admin Statistics");
        viewAdminStatisticsButton.setBounds(10, 50, 200, 25);
        viewAdminStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to view admin statistics
                JOptionPane.showMessageDialog(ManagerInterface.this, "Viewing Admin Statistics");
            }
        });
        panel.add(viewAdminStatisticsButton);

        JButton addDoctorButton = new JButton("Add Doctor");
        addDoctorButton.setBounds(10, 80, 200, 25);
        addDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to add a doctor
                JOptionPane.showMessageDialog(ManagerInterface.this, "Adding Doctor");
            }
        });
        panel.add(addDoctorButton);

        JButton addNurseButton = new JButton("Add Nurse");
        addNurseButton.setBounds(10, 110, 200, 25);
        addNurseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to add a nurse
                JOptionPane.showMessageDialog(ManagerInterface.this, "Adding Nurse");
            }
        });
        panel.add(addNurseButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManagerInterface("TestManager");
            }
        });
    }
}
