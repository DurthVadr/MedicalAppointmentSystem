import javax.swing.*;

class PatientInterface extends JFrame {
    private final Patient patient;

    public PatientInterface(Patient patient) {
        super("Patient Interface");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.patient = patient;

        JPanel panel = new JPanel();
        add(panel);
        placePatientComponents(panel);

        setVisible(true);
    }

    private void placePatientComponents(JPanel panel) {
        // Implement patient interface components and functionalities
        // For example, display patient's upcoming appointments, allow appointment booking, etc.
        // ...
    }

}
