package database;

public class GraphStoreDTO {
	int subject;
	int predicate;
	int object;
	
	public GraphStoreDTO() {
		// TODO Auto-generated constructor stub
		subject = 0;
		predicate = 0;
		object = 0;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public int getPredicate() {
		return predicate;
	}

	public void setPredicate(int predicate) {
		this.predicate = predicate;
	}

	public int getObject() {
		return object;
	}

	public void setObject(int object) {
		this.object = object;
	}

	
}
