

public class Nurse extends User{
    public Nurse(String email, String username, String password_hash) {
        super(email, username, password_hash, 3); // UserTypeCode for Nurse is 3
    }

    // Add methods specific to nurses...
    @Override
    void addUser() {
        super.addUser(); // Call the parent method for common user addition logic
        // Add additional logic specific to doctors if needed
    }
    
}
