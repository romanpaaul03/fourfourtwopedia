package com.example.fourfourtwopedia.DBHandler;

import com.example.fourfourtwopedia.Model.*;
import com.example.fourfourtwopedia.Model.Competition.*;

import java.sql.*;
import java.util.ArrayList;

public class CompetetionDBHandler {

    public static Integer registerCompetetion(Integer organizerId, String CompetetionName, CompetitionType type) {
        String query = "INSERT INTO Competition (organizerId, competitionName, competitionType) " +
                "VALUES (?, ?, ?);";
        Connection con = dbConnection.getDBConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, organizerId);
            preparedStatement.setString(2, CompetetionName);
            preparedStatement.setInt(3, type == CompetitionType.LEAGUE ? 2 : 1);


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generateProductId = generatedKeys.getInt(1);
                    return generateProductId;
                }
                return -1;
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean registerCompetetionMatch(Integer CompetetionId, ArrayList<Match> matches) {
        String query = "INSERT INTO `Match` (competitionId, homePlayer, awayPlayer) " +
                "VALUES (?,?,?); ";
        Connection con = dbConnection.getDBConnection();
        for (Match match : matches) {


            try (PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, CompetetionId);
                preparedStatement.setInt(2, match.getHomePlayer().getUserID());
                preparedStatement.setInt(3, match.getAwayPlayer().getUserID());


                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == matches.size()) {
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    public static void updateCompetetionName(int competitionId, String newName) {
        String query = "UPDATE Competition SET CompetitionName=? WHERE id=?";

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, competitionId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Competition name updated successfully for Competition ID: " + competitionId);
            } else {
                System.out.println("No competition found for Competition ID: " + competitionId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateCompetetionActiveStatus(int competitionId, boolean active) {
        String query = "UPDATE Competition SET active=? WHERE id=?";

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setBoolean(1, active);
            preparedStatement.setInt(2, competitionId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Competition name updated successfully for Competition ID: " + competitionId);
            } else {
                System.out.println("No competition found for Competition ID: " + competitionId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Competition> getCompetitions(Organizer organizer) {
        String query = "SELECT * FROM Competition WHERE organizerId=? and active=true order by time";
        ArrayList<Competition> competitions = new ArrayList<>();

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, organizer.getUserID());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int competitionId = resultSet.getInt("id");
                    String competitionName = resultSet.getString("competitionName");
                    int competitionType = resultSet.getInt("competitionType");

                    ArrayList<Match> matches = getCompetitionMatches(competitionId);
                    ArrayList<Player> players=getCompetetionPlayer(competitionId);
                    Competition competition = new Competition(competitionId,competitionName,players,matches,competitionType==1?CompetitionType.KNOCKOUT:CompetitionType.LEAGUE);
                    competitions.add(competition);
                }
                return competitions;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Competition getCompetetion(int competitionId){
        String query = "SELECT * FROM Competition WHERE id=? order by time";
        ArrayList<Competition> competitions = new ArrayList<>();

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, competitionId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String competitionName = resultSet.getString("competitionName");
                    int competitionType = resultSet.getInt("competitionType");

                    ArrayList<Match> matches = getCompetitionMatches(competitionId);
                    ArrayList<Player> players=getCompetetionPlayer(competitionId);
                    Competition competition = new Competition(competitionId,competitionName,players,matches,competitionType==1?CompetitionType.KNOCKOUT:CompetitionType.LEAGUE);
                    competitions.add(competition);
                    return competition;
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static ArrayList<Player> getCompetetionPlayer(int competitionId) {
        String query="SELECT DISTINCT player_id " +
                "FROM ( " +
                "    SELECT homePlayer AS player_id FROM `Match` WHERE competitionId = ? " +
                "    UNION " +
                "    SELECT awayPlayer AS player_id FROM `Match` WHERE competitionId = ? " +
                ") AS player_ids; ";


        ArrayList<Player> players = new ArrayList<>();

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, competitionId);
            preparedStatement.setInt(2, competitionId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int userId = resultSet.getInt("player_id");
                    players.add((Player) UserDBHandler.getUser(userId));
                }
                return players;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<Match> getCompetitionMatches(int competitionId) {

        String query = "SELECT * FROM `Match` WHERE competitionId = ? ORDER BY time";
        ArrayList<Match> matches = new ArrayList<>();

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, competitionId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int matchId = resultSet.getInt("id");
                    int homePlayerId = resultSet.getInt("homePlayer");
                    int awayPlayerId = resultSet.getInt("awayPlayer");
                    int homePlayerScore = resultSet.getInt("HomePlayerScore");
                    int awayPlayerScore = resultSet.getInt("AwayPlayerScore");
                    Timestamp time = resultSet.getTimestamp("time");

                    // Create a Match object with retrieved attributes
                    Match match = new Match(matchId, homePlayerId, awayPlayerId, homePlayerScore, awayPlayerScore);
                    matches.add(match);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return matches;
    }
    public static ArrayList<Competition> getCompetitions() {
        String query = "SELECT * FROM Competition WHERE  active=true order by time";
        ArrayList<Competition> competitions = new ArrayList<>();

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int competitionId = resultSet.getInt("id");
                    String competitionName = resultSet.getString("competitionName");
                    int competitionType = resultSet.getInt("competitionType");

                    ArrayList<Match> matches = getCompetitionMatches(competitionId);
                    ArrayList<Player> players=getCompetetionPlayer(competitionId);
                    Competition competition = new Competition(competitionId,competitionName,players,matches,competitionType==1?CompetitionType.KNOCKOUT:CompetitionType.LEAGUE);
                    competitions.add(competition);
                }
                return competitions;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
