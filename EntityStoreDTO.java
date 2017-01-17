package database;

public class EntityStoreDTO {
	int subject;
	String predicate;
	String object;
	
	public EntityStoreDTO() {
		subject = 0;
		predicate = "";
		object = "";
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}
	
	
}
