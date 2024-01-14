import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationDialog extends JDialog {
    JTextField emailField, usernameField, dobField, expertiseField, passwordField;
    JComboBox<String> userTypeComboBox;

    public RegistrationDialog(JFrame parent) {
        super(parent, "Registration", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // ... other components ...

        JLabel userTypeLabel = new JLabel("User Type:");
        userTypeLabel.setBounds(10, 190, 80, 25);
        panel.add(userTypeLabel);

        String[] userTypes = {"Patient", "Doctor", "Nurse", "Manager"};
        userTypeComboBox = new JComboBox<>(userTypes);
        userTypeComboBox.setBounds(100, 190, 160, 25);
        panel.add(userTypeComboBox);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100, 220, 80, 25);
        panel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = emailField.getText();
        String username = usernameField.getText();
        String dob = dobField.getText();
        String expertise = expertiseField.getText();
        String password = passwordField.getText();
        int userType = (int) userTypeComboBox.getSelectedItem();

        User user = new User(email, username, password,  userType);
        
        switch (userType) {
            case 4:
                user.registerPatient(dob);
                break;
            case 2:
                user.registerDoctor(expertise);
                break;
            case 3:
                user.registerNurse(password);
                break;
            case 1:
                user.registerManager(password);
                break;
        }

        JOptionPane.showMessageDialog(this, userType + " registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}