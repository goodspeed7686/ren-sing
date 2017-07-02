package com.sing.ren.pojo;

public class RSUser {

	private String userId;
	private String password;
	private RSUserRole userRole;
	private RSUserStatus userStatus;
	
	public RSUser() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RSUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(RSUserRole userRole) {
		this.userRole = userRole;
	}

	public RSUserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(RSUserStatus userStatus) {
		this.userStatus = userStatus;
	}
	
	
}
