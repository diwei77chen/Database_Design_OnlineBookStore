package database;

public class StateDTO{
	private int stateID;
	private String state;
	
	public StateDTO() {
		stateID = 0;
		state = "";
	}

	public int getStateID() {
		return stateID;
	}

	public void setStateID(int stateID) {
		this.stateID = stateID;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
