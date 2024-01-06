import javax.swing.*;

public class MedicalAppointmentSystem {
    public static void main(String[] args) {
        // Open the registration dialog
        RegistrationDialog registrationDialog = new RegistrationDialog(null);
        registrationDialog.setVisible(true);

        // Uncomment the following lines for each user type to see the example flow
        // For demonstration purposes, you may want to comment out lines that are not needed

        // Registering a patient and opening the patient interface
        // Patient patient = registerPatient();
        // openPatientInterface(patient);

        // Registering a doctor and opening the doctor interface
        // Doctor doctor = registerDoctor();
        // openDoctorInterface(doctor);

        // Registering a nurse and opening the nurse interface
        // Nurse nurse = registerNurse();
        // openNurseInterface(nurse);

        // Registering a manager and opening the manager interface
        // Manager manager = registerManager();
        // openManagerInterface(manager);
    }

    private static Patient registerPatient() {
        // Implement patient registration logic here
        // Return a Patient object after successful registration
        return null;
    }

    private static void openPatientInterface(Patient patient) {
        // Open the patient interface
        new PatientInterface(patient);
    }

    private static Doctor registerDoctor() {
        // Implement doctor registration logic here
        // Return a Doctor object after successful registration
        return null;
    }

    private static void openDoctorInterface(Doctor doctor) {
        // Open the doctor interface
        new DoctorInterface(doctor);
    }

    private static Nurse registerNurse() {
        // Implement nurse registration logic here
        // Return a Nurse object after successful registration
        return null;
    }

    private static void openNurseInterface(Nurse nurse) {
        // Open the nurse interface
        new NurseInterface(nurse);
    }

    private static Manager registerManager() {
        // Implement manager registration logic here
        // Return a Manager object after successful registration
        return null;
    }

    private static void openManagerInterface(Manager manager) {
        // Open the manager interface
        new ManagerInterface(manager);
    }
}
