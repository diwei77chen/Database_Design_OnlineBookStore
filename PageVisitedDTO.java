package database;
import java.sql.Timestamp;

public class PageVisitedDTO {
	private int pageVisitedID;
	private String pageLink;
	private Timestamp startTime;
	private Timestamp endTime;
	private int userID;
	
	public PageVisitedDTO() {
		// TODO Auto-generated constructor stub
		pageVisitedID = 0;
		pageLink = "";
		startTime = null;
		endTime = null;
		userID = 0;
	}

	public int getPageVisitedID() {
		return pageVisitedID;
	}

	public void setPageVisitedID(int pageVisitedID) {
		this.pageVisitedID = pageVisitedID;
	}

	public String getPageLink() {
		return pageLink;
	}

	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
}
