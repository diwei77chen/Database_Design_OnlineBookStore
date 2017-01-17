package database;
import java.sql.Connection;
import java.sql.Date;

public interface UserInterface {
	
	public int getUserID();

	public String getUsername();

	public String getPassword();

	public void setPassword(String password);

	public String getFirstName();

	public void setFirstName(String name);

	public String getLastName();

	public void setLastName(String name);

	public String getEmailAddress();

	public void setEmailAddress(String emailAddress);

	public Date getYearofBirth();

	public void setYearofBirth(Date yearofBirth);

	public String getAddress();

	public void setAddress(String address);

	public String getCreditCardNumber();

	public void setCreditCardNumber(String creditCardNumber);

	public String getNickname();

	public void setNickname(String nickname);

	public boolean isConfirmed();

	public void setConfirmed(boolean isConfirmed);

	public boolean isSeller();

	public void setSeller(boolean isSeller);

	public boolean isBanned();

	public void setBanned(boolean isBanned);
}
