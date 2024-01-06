

public class Manager extends User {
    public Manager(String email, String username, String password_hash) {
        super(email, username, password_hash, 1); // UserTypeCode for Manager is 1
    }

    // Add methods specific to managers...
    @Override
    void addUser() {
        super.addUser(); // Call the parent method for common user addition logic
        // Add additional logic specific to doctors if needed
    }
    
}
