import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NurseInterface extends JFrame {
    private final String username;

    public NurseInterface(String username) {
        super("Nurse Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.username = username;

        JPanel panel = new JPanel();
        add(panel);
        placeNurseComponents(panel);

        setVisible(true);
    }

    private void placeNurseComponents(JPanel panel) {
        // Implement nurse dashboard components and functionalities
        // For example, display nurse's upcoming assigned rooms, view room availability,
        // etc.
        // ...

        JLabel welcomeLabel = new JLabel("Welcome, Nurse " + username + "!");
        welcomeLabel.setBounds(10, 20, 200, 25);
        panel.add(welcomeLabel);

        JButton viewAssignedRoomsButton = new JButton("View Assigned Rooms");
        viewAssignedRoomsButton.setBounds(10, 50, 200, 25);
        viewAssignedRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to view assigned rooms for nurse
                JOptionPane.showMessageDialog(NurseInterface.this, "Viewing Assigned Rooms for Nurse");
            }
        });
        panel.add(viewAssignedRoomsButton);

        JButton viewRoomAvailabilityButton = new JButton("View Room Availability");
        viewRoomAvailabilityButton.setBounds(10, 80, 200, 25);
        viewRoomAvailabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to view room availability
                JOptionPane.showMessageDialog(NurseInterface.this, "Viewing Room Availability for Nurse");
            }
        });
        panel.add(viewRoomAvailabilityButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NurseInterface("TestNurse");
            }
        });
    }
}
