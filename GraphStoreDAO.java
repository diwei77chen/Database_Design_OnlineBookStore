package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;

public class GraphStoreDAO implements DAO {
	private DataSource dataSource;
	
	public GraphStoreDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/TestDB");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<GraphStoreDTO> findAll() {
		ArrayList<GraphStoreDTO> graphStoreDTOs = new ArrayList<GraphStoreDTO>();
		String sql = "select * from GraphStore";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				GraphStoreDTO graphStoreDTO = new GraphStoreDTO();
				graphStoreDTO.setSubject(resultSet.getInt("Subject"));
				graphStoreDTO.setPredicate(resultSet.getInt("Predicate"));
				graphStoreDTO.setObject(resultSet.getInt("Object"));
				graphStoreDTOs.add(graphStoreDTO);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return graphStoreDTOs;
	}
	
	public boolean insertRecord(String subject, String object) {
		String sql = "insert into GraphStore(Subject, Object) values(?,?)";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, subject);
			preparedStatement.setString(2, object);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
