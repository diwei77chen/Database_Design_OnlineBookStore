package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;

public class StatesDAO{
	private DataSource dataSource;
	
	public StatesDAO () {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/TestDB");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	// checked
	public boolean insertRecord(String state) {
		String sql = "insert into States"
					+ "(State) values"
					+ "(?)";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, state);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// checked
	// stateName: isInCart, isRemoved, isPurchased
	public StateDTO getState(String stateName) {
		StateDTO state = new StateDTO();
		String sql = "select * from States where State = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, stateName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				state.setStateID(resultSet.getInt("StateID"));
				state.setState(resultSet.getString("State"));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
}
