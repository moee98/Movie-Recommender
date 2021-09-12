package model;

public class User {

	public User(int userId) {
		
		super();
		this.userId = userId;
	}
		
	int userId;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
