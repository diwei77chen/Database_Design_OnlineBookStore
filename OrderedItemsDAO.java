package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;

public class OrderedItemsDAO implements DAO{
	private DataSource dataSource;
	
	public OrderedItemsDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/TestDB");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// checked
	public ArrayList<OrderedItemDTO> findAll() {
		ArrayList<OrderedItemDTO> orderedItems = new ArrayList<OrderedItemDTO>();
		String sql = "select * from OrderedItems";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderedItemDTO orderedItem = new OrderedItemDTO();
				orderedItem.setOrderedItemID(resultSet.getInt("OrderedItemID"));
				orderedItem.setAddedTime(resultSet.getTimestamp("AddedTime"));
				orderedItem.setChangedTime(resultSet.getTimestamp("ChangedTime"));
				orderedItem.setUserID(resultSet.getInt("UserID"));
				orderedItem.setStateID(resultSet.getInt("StateID"));
				orderedItem.setItemID(resultSet.getInt("ItemID"));
				orderedItems.add(orderedItem);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderedItems;
	}
	
	// checked
	public boolean insertRecord(Timestamp addedTime, Timestamp changedTime, int userID, int stateID, int itemID) {
		String sql = "insert into OrderedItems(AddedTime, ChangedTime, UserID, StateID, ItemID) "
						+ "values(?,?,?,?,?)";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, addedTime);
			preparedStatement.setTimestamp(2, changedTime);
			preparedStatement.setInt(3, userID);
			preparedStatement.setInt(4, stateID);
			preparedStatement.setInt(5, itemID);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
}
