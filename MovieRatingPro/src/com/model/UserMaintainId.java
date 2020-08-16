package com.model;

import java.io.Serializable;



public class UserMaintainId implements Serializable{

	public UserMaintainId() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	
	private String appName;

	@Override
	public String toString() {
		return "UserMaintainId [userName=" + userName + ", appName=" + appName + "]";
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        UserMaintainId that = (UserMaintainId) o;

	        if (!userName.equals(that.userName)) return false;
	        return appName.equals(that.appName);
	    }

	    @Override
	    public int hashCode() {
	        int result = userName.hashCode();
	        result = 31 * result + appName.hashCode();
	        return result;
	    }

}
