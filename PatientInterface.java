import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientInterface extends JFrame {

    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField dobField;
    private JButton signUpButton;

    public PatientInterface() {
        super("Patient Interface");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placePatientComponents(panel);

        setVisible(true);
    }

    private void placePatientComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 20, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 20, 165, 25);
        panel.add(emailField);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(10, 50, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 50, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 80, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 80, 165, 25);
        panel.add(passwordField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(10, 110, 80, 25);
        panel.add(dobLabel);

        dobField = new JTextField(20);
        dobField.setBounds(100, 110, 165, 25);
        panel.add(dobField);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(10, 140, 80, 25);
        panel.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                String passwordString = new String(password);
                String dob = dobField.getText();

                // Perform signup logic
                User patient_user = new User(email, username, passwordString, 4); // Assuming userTypeCode for
                                                                                     // Patient is 4

                // Register the patient from patient_user object
                patient_user.registerPatient(username, dob);


                // You might want to close this signup window or navigate to another screen
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PatientInterface());
    }
}
