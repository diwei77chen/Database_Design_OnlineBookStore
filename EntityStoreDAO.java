package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;

public class EntityStoreDAO implements DAO{
	private DataSource dataSource;
	
	public EntityStoreDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/TestDB");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<EntityStoreDTO> findAll() {
		ArrayList<EntityStoreDTO> entityStoreDTOs = new ArrayList<EntityStoreDTO>();
		String sql = "select * from EntityStore";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				EntityStoreDTO entityStoreDTO = new EntityStoreDTO();
				entityStoreDTO.setSubject(resultSet.getInt("Subject"));
				entityStoreDTO.setPredicate(resultSet.getString("Predicate"));
				entityStoreDTO.setObject(resultSet.getString("Object"));
				entityStoreDTOs.add(entityStoreDTO);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entityStoreDTOs;
	}
	
	public boolean insertRecord(String subject, String predicate, String object) {
		String sql = "insert into EntityStore(Subject, Predicate, Object) values(?,?,?)";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, subject);
			preparedStatement.setString(2, predicate);
			preparedStatement.setString(3, object);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
