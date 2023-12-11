package com.example.fourfourtwopedia.Model;

import com.example.fourfourtwopedia.DBHandler.CompetetionDBHandler;
import com.example.fourfourtwopedia.Model.Competition.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Organizer extends User {
	
	private Integer competitonsCreated = 0;
	
	public Organizer() { super(); }
	public Organizer(Integer UserID, String Username, String password,Role Role) {
		super(UserID, Username,password, Role);
	}
	public Boolean registerCompetetion(String CompetitionName,ArrayList<Player> players, CompetitionType type){
		Competition competition=new Competition(CompetitionName,players,type);
		Integer competetionId = CompetetionDBHandler.registerCompetetion(this.getUserID(),CompetitionName,type);
		if(competetionId < 0){
			return false;
		}
		CompetetionDBHandler.registerCompetetionMatch(competetionId,competition.getMatches());
		return true;

	}
	public Integer getCompetitionsCreated() { return this.competitonsCreated; }
	public void setCompetitionsCreated(Integer New) { this.competitonsCreated = New; }
}
