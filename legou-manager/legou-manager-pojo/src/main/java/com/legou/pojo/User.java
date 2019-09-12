package com.legou.pojo;

public class User {
	private UserTest userTest;

	public UserTest getUserTest() {
		return userTest;
	}

	public void setUserTest(UserTest userTest) {
		this.userTest = userTest;
	}

	public User(UserTest userTest) {
		super();
		this.userTest = userTest;
	}
	
	
	public User() {
		super();
	}

	public void yy() {
		System.out.println("yyyyyy");
	}
}
