package com.example.fourfourtwopedia.DBHandler;

import com.example.fourfourtwopedia.Model.Organizer;
import com.example.fourfourtwopedia.Model.Player;
import com.example.fourfourtwopedia.Model.User;
import com.example.fourfourtwopedia.Model.User.Role;

import java.sql.*;
import java.util.ArrayList;

public class UserDBHandler {
    public static boolean isPresent(String userName, Role role){
        return false;
    }
    public static User getUser(String userName, Role role) {
        String query = "SELECT u.id,u.username,u.password FROM user u JOIN enums e ON u.role = e.id WHERE u.username = ? AND e.title = ?";

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, userName);
            statement.setString(2, role.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    userName = resultSet.getString(2);
                    String userPass = resultSet.getString(3);
                    switch (role){
                        case PLAYER -> {
                            return new Player(id,userName,userPass,Role.PLAYER);
                        }
                        case ORGANIZER -> {
                            return new Organizer(id, userName,userPass,Role.ORGANIZER);
                        }
                    }
                    return new User(id,userName,userPass,role); // Returns true if the user exists, false otherwise
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Return false in case of an exception or no result
    }
    public static User loginUser(String userName, String password,Role role) {
        String query = "SELECT u.id,u.username,u.password FROM user u JOIN enums e ON u.role = e.id WHERE u.username = ? AND e.title = ? AND u.password=?";

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, userName);
            statement.setString(2, role.toString());
            statement.setString(3, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    userName = resultSet.getString(2);
                    String userPass = resultSet.getString(3);
                    switch (role){
                        case PLAYER -> {
                            return new Player(id,userName,userPass,Role.PLAYER);
                        }
                        case ORGANIZER -> {
                            return new Organizer(id, userName,userPass,Role.ORGANIZER);
                        }
                    }
                    return new User(id,userName,userPass,role); // Returns true if the user exists, false otherwise
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Return false in case of an exception or no result
    }

    public static ArrayList<User> getUsers() {
        String query = "SELECT id,username,password,role FROM user where username<> 'GuestPlayer'";

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement statement = con.prepareStatement(query)) {


            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<User> users=new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String userName = resultSet.getString(2);
                    String userPass = resultSet.getString(3);
                    Role role=resultSet.getString(4)=="PLAYER"? Role.PLAYER: Role.ORGANIZER;

                    switch (role){
                        case PLAYER -> {
                            users.add(new Player(id,userName,userPass,Role.PLAYER));
                        }
                        case ORGANIZER -> {
                            users.add(new Organizer(id, userName,userPass,Role.ORGANIZER));
                        }
                    }
                }
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUserName(Integer userId, String userName,String password) {
        String query = "UPDATE User SET username=?, password=? WHERE id=?";

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Username updated successfully for User ID: " + userId);
            } else {
                System.out.println("No user found for User ID: " + userId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean insertUser(String userName,String password,Role role){
        int num=0;
        switch (role){
            case ORGANIZER ->{ num=5;}
            case PLAYER ->{ num=6;}
        }
        String query = "Insert into User (username,password,role) value (?,?,?)";

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, userName);
            statement.setString(2,password);
            statement.setInt(3, num);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }
        return false;
    }


    public static ArrayList<Player> getPlayerList(){
        ArrayList<Player> players=new ArrayList<>();
        String query="Select * from User where role=6 and username <> 'GuestPlayer'";
        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
             try (ResultSet resultSet = statement.executeQuery()) {
                 while(resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String userName = resultSet.getString(2);
                     String userPass = resultSet.getString(3);
                    players.add(new Player(id,userName,userPass,Role.PLAYER));
                }
             return players;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }
    }
    public static ArrayList<Player> getPlayerListWithGuestOne(){
        ArrayList<Player> players=new ArrayList<>();
        String query="Select * from User where role=6";
        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String userName = resultSet.getString(2);
                    String userPass = resultSet.getString(3);
                    players.add(new Player(id,userName,userPass,Role.PLAYER));
                }
                return players;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }
    }

    public static User getUser(Integer userId) {
        String query = "SELECT u.id,u.username,u.password,u.role FROM user u where u.id=?";

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String userName = resultSet.getString(2);
                    String userPass=resultSet.getString(3);
                    int role=resultSet.getInt(4);

                    switch (role){
                        case 5 -> {
                            return new Organizer(id, userName,userPass,Role.ORGANIZER);
                        }
                        case 6 -> {
                            return new Player(id,userName,userPass,Role.PLAYER);
                        }
                        default -> {
                            return new User(id,userName,userPass,Role.ADMIN);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Return false in case of an exception or no result
    }
}
