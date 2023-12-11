package com.example.fourfourtwopedia.Model;

public class User {
	public enum Role {
		PLAYER, ORGANIZER,ADMIN
	}
	
	private Integer UserID;
	private String Username;
	private Role Role;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;
	public User() {};
	public User(Integer UserID, String Username,String password, Role Role) {
		this.UserID = UserID;
		this.Username = Username;
		this.Role = Role;
		this.password=password;
	}
	public Integer getUserID() {
        return UserID != null ? UserID : -1;
    }
	public void setUserID(Integer userID) { UserID = userID; }
	
	public String getUsername() { return Username; }
	public void setUsername(String username) { Username = username; }
	
	public Role getRole() { return Role; }
	public void setRole(Role role) { Role = role; }
	
	
}
