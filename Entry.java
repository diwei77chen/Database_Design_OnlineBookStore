package database;
import java.util.ArrayList;

public class Entry {
	private String publication;
    private ArrayList<String> author;
    private String pubType;
    private String pubDate;
    private String venue;
    private ArrayList<String> infoList;
    
    public Entry() {
    	author = new ArrayList<String>();
    	setInfoList(new ArrayList<String>());
    }
    
    public Entry(String publication, String author, String pubType, String pubDate, String venue) {
    	this.publication = publication;
    	this.author.add(author);
    	this.pubType = pubType;
    	this.pubDate = pubDate;
    	this.venue = venue;
    }
    
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public ArrayList<String> getAuthor() {
		return author;
	}
	public void setAuthor(ArrayList<String> author) {
		this.author = author;
	}
	public String getPubType() {
		return pubType;
	}
	public void setPubType(String pubType) {
		this.pubType = pubType;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}

	public ArrayList<String> getInfoList() {
		return infoList;
	}

	public void setInfoList(ArrayList<String> infoList) {
		this.infoList = infoList;
	}
	
    
    
}
