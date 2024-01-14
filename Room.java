import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Room {

    private String roomType;
    private String roomName;
    private Boolean availability; // 1 = available, 0 = unavailable

    public Room(String roomType, String roomName, Boolean availability) {
        this.roomType = roomType;
        this.roomName = roomName;
        this.availability = availability;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public void addRoomToDatabase() {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            String query = "INSERT INTO Room (RoomType, RoomName, Availability) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, roomType);
            statement.setString(2, roomName);
            statement.setBoolean(3, availability);
            statement.executeUpdate();
            System.out.println("Room added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void updateRoomInDatabase() {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            String query = "UPDATE Room SET RoomType=?, RoomName=?, Availability=? WHERE RoomID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, roomType);
            statement.setString(2, roomName);
            statement.setBoolean(3, availability);
            // Assuming you have a RoomID field for uniquely identifying the room
            statement.setInt(4, getRoomIDFromDatabase());
            statement.executeUpdate();
            System.out.println("Room updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void deleteRoomFromDatabase() {
        Connection connection = null;
        try {
            connection = connectToDatabase();
            String query = "DELETE FROM Room WHERE RoomID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            // Assuming you have a RoomID field for uniquely identifying the room
            statement.setInt(1, getRoomIDFromDatabase());
            statement.executeUpdate();
            System.out.println("Room deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // Add other methods for retrieving room information from the database as needed

    private int getRoomIDFromDatabase() {
        // Implement logic to retrieve and return the RoomID from the database
        return 0; // Placeholder value, replace with actual implementation
    }

    private Connection connectToDatabase() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/cs202_project";
            String user = "root";
            String password = "Thed4rkside"; // Change this to your database password
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
