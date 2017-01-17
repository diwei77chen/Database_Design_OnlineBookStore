package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;

public class SessionsDAO {
	private DataSource dataSource;
	
	public SessionsDAO() {
		// TODO Auto-generated constructor stub
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/TestDB");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// checked
	public void insertRecord(int userID, String sessionKey, Timestamp createdOn) {
		String sql = "insert into Sessions (UserID, SessionKey, CreatedOn)"
				+ "values (?,?,?)";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			preparedStatement.setString(2, sessionKey);
			preparedStatement.setTimestamp(3, createdOn);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// checked
	public SessionDTO selectByUserID(int userID) {
		SessionDTO sessionDTO = new SessionDTO();
		String sql = "select * from Sessions where UserID = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				sessionDTO.setUserID(userID);
				sessionDTO.setSessionKey(resultSet.getString("SessionKey"));
				sessionDTO.setCreatedOn(resultSet.getTimestamp("CreatedOn"));
			} 
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sessionDTO;
	}
	
	// checked
	public void removeByUserID(int userID) {
		String sql = "delete from Sessions where UserID = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
