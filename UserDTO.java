package database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDTO implements UserInterface{
	private int UserID;
	private Connection connection;
	private String Username;
	private String Nickname;
	private String firstName;
	private String lastName;
	private String Password;
	private String EmailAddress;
	private Date YearofBirth;
	private String Address;
	private String CreditCardNumber;
	private boolean isConfirmed;
	private boolean isSeller;
	private boolean isBanned;
	
	public UserDTO(String username, String password) {
		UserID = 0;
		Username = "";
		Nickname = "";
		Password = "";
		EmailAddress = "";
		YearofBirth = null;
		Address = "";
		CreditCardNumber = "";
		isConfirmed = false;
		isSeller = false;
		isBanned = false;
	}

	public int getUserID() {
		return UserID;
	}

	public String getUsername() {
		return Username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}

	public Date getYearofBirth() {
		return YearofBirth;
	}

	public void setYearofBirth(Date yearofBirth) {
		YearofBirth = yearofBirth;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCreditCardNumber() {
		return CreditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		CreditCardNumber = creditCardNumber;
	}

	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String nickname) {
		Nickname = nickname;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public boolean isSeller() {
		return isSeller;
	}

	public void setSeller(boolean isSeller) {
		this.isSeller = isSeller;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	
	
}
