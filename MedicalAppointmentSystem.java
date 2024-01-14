import javax.swing.*;

public class MedicalAppointmentSystem {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Medical Appointment System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeComponents'");
    }
}