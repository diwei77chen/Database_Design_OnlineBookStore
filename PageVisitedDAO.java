package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.sun.security.auth.PrincipalComparator;

public class PageVisitedDAO {
	private DataSource dataSource;
	
	public PageVisitedDAO () {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/TestDB");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// checked
	public boolean insertRecord(String pageLink, Timestamp startTime, Timestamp endTime, int userID) {
		String sql = "insert into PageVisited"
				+ "(PageLink, StartTime, EndTIme, UserID) values"
				+ "(?,?,?,?)";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, pageLink);
			preparedStatement.setTimestamp(2, startTime);
			preparedStatement.setTimestamp(3, endTime);
			preparedStatement.setInt(4, userID);
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
	public ArrayList<PageVisitedDTO> selectUserID(int userID) {
		ArrayList<PageVisitedDTO> pageVisitedDTOs = new ArrayList<PageVisitedDTO>();
		String sql = "select * from PageVisited where UserID = ?";
		
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PageVisitedDTO pageVisitedDTO = new PageVisitedDTO();
				pageVisitedDTO.setPageVisitedID(resultSet.getInt("PageVisitedID"));
				pageVisitedDTO.setPageLink(resultSet.getString("PageLink"));
				pageVisitedDTO.setStartTime(resultSet.getTimestamp("StartTime"));
				pageVisitedDTO.setEndTime(resultSet.getTimestamp("EndTime"));
				pageVisitedDTO.setUserID(resultSet.getInt("UserID"));
				pageVisitedDTOs.add(pageVisitedDTO);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pageVisitedDTOs;
	}
}	
