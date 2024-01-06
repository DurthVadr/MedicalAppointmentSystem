

public class Doctor extends User {
    String expertise;

    public Doctor(String email, String username, String password_hash, int UserTypeCode, String expertise) {
        super(email, username, password_hash, UserTypeCode = 2);
        this.expertise = expertise;
    }

    // Add methods specific to doctors (e.g., listing room availability, assigning rooms)
    // ...

    @Override
    void addUser() {
        super.addUser(); // Call the parent method for common user addition logic
        // Add additional logic specific to doctors if needed
    }
}