package com.example.fourfourtwopedia.Model;

import com.example.fourfourtwopedia.DBHandler.CompetetionDBHandler;
import com.example.fourfourtwopedia.DBHandler.MatchDbHandler;

import java.util.ArrayList;

public class Competition {


    public boolean isLastMatc(Integer matchId) {
        return matchId == matches.get(matches.size()-1).getMatchId();
    }

    public enum CompetitionType {
        LEAGUE, KNOCKOUT
    }

    private Integer CompetitionID;
    private String CompetitionName;
    private CompetitionType CompType;
    private ArrayList<Match> matches = new ArrayList<>();
    private ArrayList<Player> players=new ArrayList<>();
    public Competition() {}

    public Integer getCompetitionID() {
        return CompetitionID;
    }

    public void setCompetitionID(Integer competitionID) {
        CompetitionID = competitionID;
    }

    public String getCompetitionName() {
        return CompetitionName;
    }

    public void setCompetitionName(String competitionName) {
        CompetitionName = competitionName;
    }

    public CompetitionType getCompType() {
        return CompType;
    }

    public void setCompType(CompetitionType compType) {
        CompType = compType;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Competition(Integer CompID, String CompName, ArrayList<Player> players, ArrayList<Match> matches, CompetitionType Type) {
        this.CompetitionID = CompID;
        this.CompetitionName = CompName;
        this.CompType = Type;
        if ((this.CompType == CompetitionType.KNOCKOUT && !(players.size() == 4 || players.size() == 8 || players.size() == 16)) ||
                (players.size() <= 2 || players.size() > 16)) {
            throw new IllegalArgumentException("Invalid value for NumberOfPlayers");
        } else {
            this.players=players;
            this.matches=matches;
        }
    }
    public Competition(String CompName, ArrayList<Player> players, CompetitionType Type) {
        this.CompetitionName = CompName;
        this.CompType = Type;
        if ((this.CompType == CompetitionType.KNOCKOUT && !(players.size() == 4 || players.size() == 8 || players.size() == 16)) ||
                (players.size() <= 2 || players.size() > 16)) {
            throw new IllegalArgumentException("Invalid value for NumberOfPlayers");
        } else {
            this.players = players;
            this.matches = Type == CompetitionType.KNOCKOUT ? getKnockOutMatches(players) : getLeagueMatchesList(players);
        }
    }


    private ArrayList<Match> getLeagueMatchesList(ArrayList<Player> players){
        ArrayList<Match> matches=new ArrayList<>();

        for (int i=0; i < players.size()-1;i++){
            for (int j = i+1; j < players.size(); j++) {
                System.out.println(players.get(i).getUserID() +"- VS -"+players.get(j).getUserID());
                matches.add(new Match(players.get(i),players.get(j)));
            }
        }

        return matches;
    }
    private ArrayList<Match> getKnockOutMatches(ArrayList<Player> players){
        ArrayList<Match> matches=new ArrayList<>();
        for (int i = 0; i < players.size(); ) {
            Player homePlayer = players.get(i);
            Player awayPlayer = players.get(i+1);
            matches.add(new Match(homePlayer,awayPlayer));
            i=i+2;
        }
        return matches;
    }
    private Boolean checkMatchIsPresent(Player player1,Player player2,ArrayList<Match> matches){
        for (Match m:matches ) {
            if((m.getHomePlayer().getUserID() == player1.getUserID() && m.getAwayPlayer().getUserID()==player2.getUserID())
                ||
                (m.getHomePlayer().getUserID() == player2.getUserID() && m.getAwayPlayer().getUserID()==player1.getUserID())
            ){
                return true;
            }
        }
        return false;
    }
    /**
     *  Check the Does it require to insert new match like in knockout rounds
     * **/
    public void checkNewMatch() throws Exception {
        if(this.CompType==CompetitionType.KNOCKOUT){
            for (int i = 0; i < matches.size(); i++) {
                Player winner1 = matches.get(i).getWinner();
                if(matches.size() > i+1){
                    Player winner2 = matches.get(i+1).getWinner();
                    if(winner2==null){
                        throw new Exception("Only one winner left");
                    }
                    if(! checkMatchIsPresent(winner1,winner2,this.matches)){
                        MatchDbHandler.registerMatch(this.CompetitionID,new Match(winner1,winner2));
                    }
                }
                i++;
            }
        }
    }
}