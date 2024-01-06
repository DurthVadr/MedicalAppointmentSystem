import javax.swing.JFrame;
import javax.swing.JPanel;


    class DoctorInterface extends JFrame {
    private final Doctor doctor;

    public DoctorInterface(Doctor doctor) {
        super("Doctor Interface");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.doctor = doctor;

        JPanel panel = new JPanel();
        add(panel);
        placeDoctorComponents(panel);

        setVisible(true);
    }

    private void placeDoctorComponents(JPanel panel) {
        // Implement doctor interface components and functionalities
        // For example, display doctor's schedule, allow room assignment, etc.
        // ...
    }
}

