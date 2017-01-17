package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;

public class PrivilegesDAO {
	private DataSource dataSource;
	
	public PrivilegesDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/TestDB");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// checked
	public boolean insertPriviledge(int userID) {
		String sql = "insert into Privileges"
					+ "(IsAdmin, UserID) values"
					+ "(?,?)";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, userID);
			preparedStatement.execute();
			connection.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	// checked
	public boolean setAdmin(boolean isAdmin, int userID) {
		String sql = "update Privileges set IsAdmin = ? where UserID = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, isAdmin);
			preparedStatement.setInt(2, userID);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	// checked
	public boolean isAdmin(int userID) {
		String sql = "select COUNT(*) as total from Priviledges where UserID = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			ResultSet resultSet = preparedStatement.executeQuery();
			int count = 0;
			while (resultSet.next()) {
				count = resultSet.getInt("total");
			}
			if (count > 0) {
				return true;
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
