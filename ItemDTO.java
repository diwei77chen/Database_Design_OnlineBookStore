package database;
import java.sql.Date;
import java.util.ArrayList;

public class ItemDTO {
	private int itemID;
	private String type;
	private String publication;
	private String date;
	private int price;
	private boolean isPaused;
	private int userId;
	private ArrayList<AuthorDTO> authors;
	private ArrayList<PictureDTO> pictures;
	private VenueDTO venue;
	
	public ItemDTO() {
		itemID = 0;
		type = "";
		publication = "";
		date = null;
		price = 0;
		isPaused = false;
		userId = 0;
		authors = null;
		pictures = null;
		venue = null;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ArrayList<AuthorDTO> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<AuthorDTO> authors) {
		this.authors = authors;
	}

	public ArrayList<PictureDTO> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<PictureDTO> pictures) {
		this.pictures = pictures;
	}

	public VenueDTO getVenue() {
		return venue;
	}

	public void setVenue(VenueDTO venue) {
		this.venue = venue;
	}
	
	
}
