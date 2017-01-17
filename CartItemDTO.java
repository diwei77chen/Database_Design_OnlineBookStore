package database;

import com.sun.scenario.effect.impl.prism.ps.PPSSepiaTonePeer;

public class CartItemDTO {
	private int cartItemID;
	private int quantity;
	private int userID;
	private int itemID;
	
	public CartItemDTO() {
		cartItemID = 0;
		quantity = 0;
		userID = 0;
		itemID = 0;
	}
	
	public int getCartItemID() {
		return cartItemID;
	}

	public void setCartItemID(int cartItemID) {
		this.cartItemID = cartItemID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	
}
