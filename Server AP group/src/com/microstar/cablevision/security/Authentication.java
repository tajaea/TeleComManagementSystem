package com.microstar.cablevision.security;

import java.io.Serializable;

public class Authentication implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userid;
	private String password;
	
	
	public Authentication(String userid,char[]password) {
		this.userid = userid;
		this.password = Security.hashPassword((String.valueOf(password)+userid).getBytes());
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Authentication [userid=" + userid + ", password=" + password + "]";
	}
}
