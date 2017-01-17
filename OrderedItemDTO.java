package database;
import java.sql.Timestamp;

public class OrderedItemDTO {
	private int orderedItemID;
	private Timestamp addedTime;
	private Timestamp changedTime;
	private int userID;
	private int stateID;
	private int itemID;
	
	public OrderedItemDTO() {
		orderedItemID = 0;
		addedTime = null;
		changedTime = null;
		userID = 0;
		stateID = 0;
		itemID = 0;
	}

	public int getOrderedItemID() {
		return orderedItemID;
	}

	public void setOrderedItemID(int orderedItemID) {
		this.orderedItemID = orderedItemID;
	}

	public Timestamp getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(Timestamp addedTime) {
		this.addedTime = addedTime;
	}

	public Timestamp getChangedTime() {
		return changedTime;
	}

	public void setChangedTime(Timestamp changedTime) {
		this.changedTime = changedTime;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getStateID() {
		return stateID;
	}

	public void setStateID(int stateID) {
		this.stateID = stateID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	
}
