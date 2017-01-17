package database;

public class AuthorDTO {
	private int AuthorID;
	private String AuthorName;
	
	public AuthorDTO() {
		AuthorID = 0;
		AuthorName = "";
	}

	public int getAuthorID() {
		return AuthorID;
	}

	public void setAuthorID(int authorID) {
		AuthorID = authorID;
	}

	public String getAuthorName() {
		return AuthorName;
	}

	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}
	
}
