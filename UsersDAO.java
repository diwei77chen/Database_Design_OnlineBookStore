package database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.tomcat.jdbc.pool.DataSource;

//import java.u
public class UsersDAO implements DAO{
	private DataSource dataSource;
	
	public UsersDAO() {
		try{
//			Hashtable env = new Hashtable();
//			env.put(Context.INITIAL_CONTEXT_FACTORY, 
//			    "org.apache.tomcat.jdbc.pool.DataSourceFactory");
//			env.put(Context.PROVIDER_URL, "jdbc:mysql://localhost");
//			env.put(Context.SECURITY_PRINCIPAL, "root");
//			env.put(Context.SECURITY_CREDENTIALS, "Kk132459");

			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/bookstore");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// checked
	public ArrayList<UserDTO> findAll() {
		
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		String sql = "select * from Users";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserDTO user = new UserDTO(resultSet.getString("Username"), resultSet.getString("Password"));
				user.setUserID(resultSet.getInt("UserID"));
				user.setUsername(resultSet.getString("Username"));
				user.setNickname(resultSet.getString("Nickname"));
				user.setPassword(resultSet.getString("Password"));
				user.setEmailAddress(resultSet.getString("EmailAddress"));
				user.setYearofBirth(resultSet.getDate("YearofBirth"));
				user.setAddress(resultSet.getString("Address"));
				user.setCreditCardNumber(resultSet.getString("CreditCardNumber"));
				user.setConfirmed(resultSet.getBoolean("IsConfirmed"));
				user.setSeller(resultSet.getBoolean("IsSeller"));
				user.setBanned(resultSet.getBoolean("IsBanned"));
				users.add(user);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	// checked
	public void insertRecord(UserInterface user) {
		String sql = "insert into Users" +
				"(Username, Nickname, Password, EmailAddress, " +
				"YearofBirth, Address, CreditCardNumber, Firstname, Lastname) values"
				+ "(?,?,?,?,?,?,?,?)";
		
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getNickname());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getEmailAddress());
			preparedStatement.setDate(5, user.getYearOfBirth().toLocaleString());
			preparedStatement.setString(6, user.getAddress());
			preparedStatement.setString(7, user.getCreditCardNumber());
			preparedStatement.setString(8, user.getFirstName());
			preparedStatement.setString(9, user.getLastName());
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// checked
	public boolean isLogin(String username, String password) {
		String sql = "select COUNT(*) as total from Users where Username = ? and Password = ?";
		boolean result = false;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("total") > 0) {
					result = true;
				}
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	// checked
	public boolean isSeller(String username) {
		boolean isSeller = false;
		String sql = "select IsSeller from Users where Username = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				boolean resultIsSeller = resultSet.getBoolean("IsSeller");
				isSeller = resultIsSeller;
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSeller;
	}

	// checked
	public boolean isBanned(String username) {
		boolean isBanned = false;
		String sql = "select IsBanned from Users where Username = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				boolean resultIsBanned = resultSet.getBoolean("IsBanned");
				isBanned = resultIsBanned;
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isBanned;
	}
	
	// checked
	public boolean setUserPassword(String username, String password) {
		String sql = "update Users set Password = ? where Username = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, username);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// checked
	public boolean setUserNickname(String username, String nickname) {
		String sql = "update Users set Nickname = ? where Username = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nickname);
			preparedStatement.setString(2, username);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// checked
	public boolean setUserEmailAddress(String username, String emailAddress) {
		String sql = "update Users set EmailAddress = ? where Username = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, emailAddress);
			preparedStatement.setString(2, username);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// checked
	public boolean setUserYearofBirth(String username, Date yearofBirth) {
		String sql = "update Users set yearofBirth = ? where Username = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, yearofBirth);
			preparedStatement.setString(2, username);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// checked
	public boolean setUserAddress(String username, String address) {
		String sql = "update Users set Address = ? where Username = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, address);
			preparedStatement.setString(2, username);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// checked
	public boolean setUserCreditCardNumber(String username, String creditCardNumber) {
		String sql = "update Users set CreditCardNumber = ? where Username = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, creditCardNumber);
			preparedStatement.setString(2, username);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// checked
	public boolean setUserConfirmed(String username, boolean isConfirmed) {
		String sql = "update Users set IsConfirmed = ? where Username = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, isConfirmed);
			preparedStatement.setString(2, username);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// checked
	public boolean setUserBanned(String username, boolean isBanned) {
		String sql = "update Users set IsBanned = ? where Username = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, isBanned);
			preparedStatement.setString(2, username);
			preparedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// checked
	public UserDTO getUserInfo(String username) {
		UserDTO user = null;
		String sql = "select * from Users where Username = ?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new UserDTO(resultSet.getString("Username"), resultSet.getString("Password"));
				user.setUserID(resultSet.getInt("UserID"));
				user.setUsername(resultSet.getString("Username"));
				user.setNickname(resultSet.getString("Nickname"));
				user.setPassword(resultSet.getString("Password"));
				user.setEmailAddress(resultSet.getString("EmailAddress"));
				user.setYearofBirth(resultSet.getDate("YearofBirth"));
				user.setAddress(resultSet.getString("Address"));
				user.setCreditCardNumber(resultSet.getString("CreditCardNumber"));
				user.setConfirmed(resultSet.getBoolean("IsConfirmed"));
				user.setSeller(resultSet.getBoolean("IsSeller"));
				user.setBanned(resultSet.getBoolean("IsBanned"));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	// checked
	public int getUserID(String username) {
		String sql = "select UserID from Users where Username = ?";
		int userID = 0;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userID = resultSet.getInt("UserID");
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userID;
	}
}
