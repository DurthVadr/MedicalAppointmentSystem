

public class Patient extends User {
    String dateOfBirth;

    public Patient(String email, String username, String password_hash, String dateOfBirth) {
        super(email, username, password_hash, 4); // UserTypeCode for Patient is 4
        this.dateOfBirth = dateOfBirth;
    }

    // Add methods specific to patients...

    @Override
    void addUser() {
        // You can add patient-specific logic here if needed
        super.addUser(); // Call the parent method for common user addition logic
    }
}
