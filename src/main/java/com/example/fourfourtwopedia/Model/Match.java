package com.example.fourfourtwopedia.Model;

import com.example.fourfourtwopedia.DBHandler.CompetetionDBHandler;
import com.example.fourfourtwopedia.DBHandler.MatchDbHandler;
import com.example.fourfourtwopedia.DBHandler.UserDBHandler;
import javafx.beans.property.SimpleIntegerProperty;

public class Match {
	private Integer matchId;
	public Player getHomePlayer() {
		return homePlayer;
	}

	public void setHomePlayer(Player homePlayer) {
		this.homePlayer = homePlayer;
	}

	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	public Integer getHomeUserScore() {
		return homeUserScore;
	}

	public void setHomeUserScore(Integer homeUserScore) {
		this.homeUserScore = homeUserScore;
	}

	public Integer getAwayUserScore() {
		return awayUserScore;
	}

	public void setAwayUserScore(Integer awayUserScore) {
		this.awayUserScore = awayUserScore;
	}

	private Player homePlayer;
	private Player awayPlayer;
	
	private Integer homeUserScore;
	private Integer awayUserScore;

	public Match(Player homePlayer, Player awayPlayer) {
		this.homePlayer = homePlayer;
		this.awayPlayer = awayPlayer;
	}

	public Match(Integer matchId, Integer homePlayerId,Integer awayPlayerId,Integer homePlayerScore,Integer awayPlayerScore){
		this.matchId=matchId;
		this.homePlayer=(Player) UserDBHandler.getUser(homePlayerId);
		this.awayPlayer=(Player) UserDBHandler.getUser(awayPlayerId);
		this.homeUserScore = homePlayerScore;
		this.awayUserScore = awayPlayerScore;
	}


	@Override
	public String toString() {
//		return "Match No. " + this.getMatchID() + ": " + this.getHomeUsername()  + " " + this.getHomeUserScore() + "-" + this.getAwayUserScore() + " " + this.getAwayUsername();
		return "";
	}

	public Player getAwayPlayer() {
		return awayPlayer;
	}

	public void setAwayPlayer(Player awayPlayer) {
		this.awayPlayer = awayPlayer;
	}

	public void updateScore(Integer homePlayerScore, Integer awayPlayerScore) {
		MatchDbHandler.updateScore(this.matchId,homePlayerScore,awayPlayerScore);
	}
	public SimpleIntegerProperty homeUserScoreProperty() {
		return new SimpleIntegerProperty(homeUserScore);
	}

	public SimpleIntegerProperty awayUserScoreProperty() {
		return new SimpleIntegerProperty(awayUserScore);
	}

	public Player getWinner() {
		if(homeUserScore == awayUserScore){
			return null;
		}
		if(homeUserScore > awayUserScore){
			return homePlayer;
		}
		return  awayPlayer;
	}
}
