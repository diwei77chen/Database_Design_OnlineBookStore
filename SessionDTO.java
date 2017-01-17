package database;

import java.sql.Timestamp;

public class SessionDTO {
	private int userID;
	private String sessionKey;
	private Timestamp createdOn;
	
	public SessionDTO() {
		// TODO Auto-generated constructor stub
		userID = 0;
		sessionKey = "";
		createdOn = null;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	
	
}
