package database;
import java.sql.Blob;

public class PictureDTO {
	private int pictureID;
	private String pictureName;
	private Blob pictureSource;
	private int itemID;
	
	public PictureDTO() {
		pictureID = 0;
		pictureName = "";
		pictureSource = null;
		itemID = 0;
	}

	public int getPictureID() {
		return pictureID;
	}

	public void setPictureID(int pictureID) {
		this.pictureID = pictureID;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public Blob getPictureSource() {
		return pictureSource;
	}

	public void setPictureSource(Blob pictureSource) {
		this.pictureSource = pictureSource;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	
}
