package database;

public class VenueDTO {
	private int venueID;
	private String venueName;
	
	public VenueDTO() {
		venueID = 0;
		venueName = "";
	}

	public int getVenueID() {
		return venueID;
	}

	public void setVenueID(int venueID) {
		this.venueID = venueID;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	
	
}
