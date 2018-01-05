package com.booksystem.mybatis.entities;

public class User {
    private String userId;

    private String userName;

    private String password;

    private int iflend;

    private int userRole;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public int getIflend() {
        return iflend;
    }

    public void setIflend(int iflend) {
        this.iflend = iflend;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", iflend=" + iflend
				+ ", userRole=" + userRole + "]";
	}

	public User(String userId, String userName, String password, int iflend, int userRole) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.iflend = iflend;
		this.userRole = userRole;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}