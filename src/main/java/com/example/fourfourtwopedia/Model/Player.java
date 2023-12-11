package com.example.fourfourtwopedia.Model;


@SuppressWarnings("unused")
public class Player extends User {
	
	private Integer MatchesPlayed = 0;
	private Integer GoalsScored = 0;
	private Integer GoalsConcedeed = 0;
	private Integer MatchesWon = 0;
	private Integer MatchesLost = 0;
	
	public Player() {
		super();
	}
	public Player(Integer UserID, String Username,String password, Role Role) {
		super(UserID, Username,password, Role);
		// Extract other Stats
	}
	public Integer getMatchesPlayed() { return MatchesPlayed; }
	public void setMatchesPlayed(Integer matchesPlayed) { MatchesPlayed = matchesPlayed; }
	
	public Integer getGoalsScored() { return GoalsScored; }
	public void setGoalsScored(Integer goalsScored) { GoalsScored = goalsScored; }
	
	public Integer getGoalsConcedeed() { return GoalsConcedeed; }
	public void setGoalsConcedeed(Integer goalsConcedeed) { GoalsConcedeed = goalsConcedeed; }
	
	public Integer getMatchesWon() { return MatchesWon; }
	public void setMatchesWon(Integer matchesWon) { MatchesWon = matchesWon; }
	
	public Integer getMatchesLost() { return MatchesLost; }
	public void setMatchesLost(Integer matchesLost) { MatchesLost = matchesLost; }
	
	public void resetStatistics() {
        setMatchesPlayed(0);
        setMatchesWon(0);
        setMatchesLost(0);
        setGoalsScored(0);
        setGoalsConcedeed(0);
    }
	
}
