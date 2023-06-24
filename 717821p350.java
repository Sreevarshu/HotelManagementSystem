package Managements;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;



public class HotelManagements

{

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_db";

    private static final String USERNAME = "your_username";

    private static final String PASSWORD = "your_password";



    public static Connection getConnection() 

    {

        try {

            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

        } catch (SQLException e) {

            e.printStackTrace();

            return null;

        }

    }

}

import java.sql.*;



public class RoomDAO {

    private Connection connection;



    public RoomDAO() {

        connection = DatabaseConnection.getConnection();

    }



    public void addRoom(Room room) {

        String query = "INSERT INTO rooms (room_number, type, availability) VALUES (?, ?, ?)";

        try {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, room.getRoomNumber());

            statement.setString(2, room.getType());

            statement.setBoolean(3, room.isAvailable());

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    public Room getRoomByNumber(int roomNumber) {

        String query = "SELECT * FROM rooms WHERE room_number = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, roomNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Room room = new Room();

                room.setRoomNumber(resultSet.getInt("room_number"));

                room.setType(resultSet.getString("type"));

                room.setAvailable(resultSet.getBoolean("availability"));

                return room;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }



    public void updateRoom(Room room) {

        String query = "UPDATE rooms SET type = ?, availability = ? WHERE room_number = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, room.getType());

            statement.setBoolean(2, room.isAvailable());

            statement.setInt(3, room.getRoomNumber());

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    public void deleteRoom(int roomNumber) {

        String query = "DELETE FROM rooms WHERE room_number = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, roomNumber);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}