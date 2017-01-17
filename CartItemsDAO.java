package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;

public class CartItemsDAO {
	private DataSource dataSource;
	
	public CartItemsDAO () {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/TestDB");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// checked
	public ArrayList<CartItemDTO> findCartItemsByUserID(int userID) {
		ArrayList<CartItemDTO> cartItems = new ArrayList<CartItemDTO>();
		String sql = "select * from CartItems where UserID = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				CartItemDTO cartItem = new CartItemDTO();
				cartItem.setCartItemID(resultSet.getInt("CartItemID"));
				cartItem.setQuantity(resultSet.getInt("Quantity"));
				cartItem.setUserID(userID);
				cartItem.setItemID(resultSet.getInt("ItemID"));
				cartItems.add(cartItem);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartItems;
	}

	// checked
	public CartItemDTO findCartItemByUSerIDAndItemID(int userID, int itemID) {
		CartItemDTO cartItem = new CartItemDTO();
		String sql = "select * from CartItems where UserID = ? and ItemID = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			preparedStatement.setInt(2, itemID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				
				cartItem.setCartItemID(resultSet.getInt("CartItemID"));
				cartItem.setQuantity(resultSet.getInt("Quantity"));
				cartItem.setUserID(userID);
				cartItem.setItemID(itemID);
				
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartItem;
	}
	
	// checked
	public boolean updateQuantityByUserIDAndItemID(int userID, int itemID, int quantity) {
		String sql = "update CartItems set Quantity = ? where UserID = ? and ItemID = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, userID);
			preparedStatement.setInt(3, itemID);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// checked
	public boolean insertRecord(int userID, int itemID) {
		String sql = "insert into CartItems(Quantity, UserID, ItemID) values(?,?,?)";
		CartItemDTO cartItem = findCartItemByUSerIDAndItemID(userID, itemID);
		if (cartItem.getCartItemID() != 0) {
//			System.out.println(1);
			return updateQuantityByUserIDAndItemID(userID, itemID, cartItem.getQuantity()+1);
			
		}
		else {
			try {
				Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, 1);
				preparedStatement.setInt(2, userID);
				preparedStatement.setInt(3, itemID);
				preparedStatement.execute();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	// checked
	public boolean deleteRecord(int cartItemID) {
		String sql = "delete from CartItems where CartItemID = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cartItemID);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
