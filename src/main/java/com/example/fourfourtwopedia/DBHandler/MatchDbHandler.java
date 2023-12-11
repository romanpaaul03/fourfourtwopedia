package com.example.fourfourtwopedia.DBHandler;

import com.example.fourfourtwopedia.DBHandler.dbConnection;
import com.example.fourfourtwopedia.Model.Match;
import com.example.fourfourtwopedia.Model.PlayerStats;

import java.sql.*;
import java.util.ArrayList;

public class MatchDbHandler {
    public static void updateScore(Integer matchId, Integer homeScore, Integer awayScore) {
        String query = "UPDATE `Match` SET HomePlayerScore = ?, AwayPlayerScore = ? WHERE id = ?";

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, homeScore);
            preparedStatement.setInt(2, awayScore);
            preparedStatement.setInt(3, matchId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Scores updated successfully for Match ID: " + matchId);
            } else {
                System.out.println("No match found for Match ID: " + matchId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean isEditableMatch(Integer matchId,Integer competetionId){
        String query = "Select count(*) from `match` m join competition c on m.competitionId=c.id \n" +
                "where  (c.competitionType <> 1 and m.competitionId=? and m.id=?) or (m.competitionId=? and m.id=? and m.homePlayerScore is null and m.awayPlayerScore is null);";

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, competetionId);
            preparedStatement.setInt(2, matchId);
            preparedStatement.setInt(3, competetionId);
            preparedStatement.setInt(4, matchId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count != 0; // If count is not 0, return true, else return false
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Boolean registerMatch(Integer CompetetionId, Match match) {
        String query = "INSERT INTO `Match` (competitionId, homePlayer, awayPlayer) " +
                "VALUES (?,?,?); ";
        Connection con = dbConnection.getDBConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, CompetetionId);
            preparedStatement.setInt(2, match.getHomePlayer().getUserID());
            preparedStatement.setInt(3, match.getAwayPlayer().getUserID());


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows >0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    public static Boolean registerExhibition(Match match) {
        String query = "INSERT INTO `Match` (homePlayer, awayPlayer,homePlayerScore,awayPlayerScore) VALUES (?,?,0,0);";
        Connection con = dbConnection.getDBConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, match.getHomePlayer().getUserID());
            preparedStatement.setInt(2, match.getAwayPlayer().getUserID());


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows >0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public static ArrayList<Match> getExhibitionMatchList(Integer playerId) {
        String query = "SELECT * FROM `Match` WHERE (homePlayer = ? OR awayPlayer = ?) AND competitionId IS NULL";
        ArrayList<Match> exhibitionMatches = new ArrayList<>();

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, playerId);
            preparedStatement.setInt(2, playerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int matchId = resultSet.getInt("id");
                    int homePlayerId = resultSet.getInt("homePlayer");
                    int awayPlayerId = resultSet.getInt("awayPlayer");
                    int homePlayerScore = resultSet.getInt("HomePlayerScore");
                    int awayPlayerScore = resultSet.getInt("AwayPlayerScore");


                    Match match = new Match(matchId, homePlayerId, awayPlayerId, homePlayerScore, awayPlayerScore);
                    exhibitionMatches.add(match);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exhibitionMatches;
    }
    public static ArrayList<PlayerStats> getPlayerStats(){
        ArrayList<PlayerStats> playerStatsList=new ArrayList<>();
        String query=
                "SELECT " +
                        "    u.username, " +
                        "    (SELECT COUNT(*) FROM `match` WHERE (homePlayer = u.id OR awayPlayer = u.id) and (HomePlayerScore is not null and AwayPlayerScore is not null)) AS 'Total Matches Played', " +
                        "    (SELECT COUNT(*) FROM `match` WHERE (homePlayer = u.id AND homePlayerScore > AwayPlayerScore) OR (awayPlayer = u.id AND homePlayerScore < AwayPlayerScore)) AS 'Total Wins', " +
                        "    (SELECT COUNT(*) FROM `match` WHERE (homePlayer = u.id OR awayPlayer = u.id) AND homePlayerScore = AwayPlayerScore and (HomePlayerScore is not null and AwayPlayerScore is not null)) AS 'Draws', " +
                        "    ( " +
                        "        SELECT SUM(goals) FROM ( " +
                        "            SELECT HomePlayerScore AS goals FROM `Match` WHERE homePlayer = u.id " +
                        "            UNION ALL " +
                        "            SELECT AwayPlayerScore AS goals FROM `Match` WHERE awayPlayer = u.id " +
                        "        ) AS totalGoals " +
                        "    ) AS 'Score Goal'" +
                        "FROM User u " +
                        "WHERE role = 6 and u.username <> 'GuestPlayer'";

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                int totalMatchesPlayed = resultSet.getInt("Total Matches Played");
                int totalWins = resultSet.getInt("Total Wins");
                int totalDraws = resultSet.getInt("Draws");
                int totalGoals = resultSet.getInt("Score Goal");

                PlayerStats playerStats = new PlayerStats(username, totalMatchesPlayed, totalWins, totalDraws, totalGoals);
                playerStatsList.add(playerStats);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playerStatsList;
    }

    public static PlayerStats getPlayerStats(Integer userId) {
        String query = "SELECT " +
                "    u.username, " +
                "    (SELECT COUNT(*) FROM `match` WHERE (homePlayer = u.id OR awayPlayer = u.id) AND (HomePlayerScore IS NOT NULL AND AwayPlayerScore IS NOT NULL)) AS 'Total Matches Played', " +
                "    (SELECT COUNT(*) FROM `match` WHERE (homePlayer = u.id AND homePlayerScore > AwayPlayerScore) OR (awayPlayer = u.id AND homePlayerScore < AwayPlayerScore)) AS 'Total Wins', " +
                "    (SELECT COUNT(*) FROM `match` WHERE (homePlayer = u.id OR awayPlayer = u.id) AND homePlayerScore = AwayPlayerScore AND (HomePlayerScore IS NOT NULL AND AwayPlayerScore IS NOT NULL)) AS 'Draws', " +
                "    ( " +
                "        SELECT SUM(goals) FROM ( " +
                "            SELECT HomePlayerScore AS goals FROM `Match` WHERE homePlayer = u.id " +
                "            UNION ALL " +
                "            SELECT AwayPlayerScore AS goals FROM `Match` WHERE awayPlayer = u.id " +
                "        ) AS totalGoals " +
                "    ) AS 'Score Goal' " +
                "FROM User u " +
                "WHERE role = 6 AND u.username <> 'GuestPlayer' AND u.id = ?";

        try (Connection con = dbConnection.getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    int totalMatchesPlayed = resultSet.getInt("Total Matches Played");
                    int totalWins = resultSet.getInt("Total Wins");
                    int totalDraws = resultSet.getInt("Draws");
                    int totalGoals = resultSet.getInt("Score Goal");

                    return new PlayerStats(username, totalMatchesPlayed, totalWins, totalDraws, totalGoals);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}