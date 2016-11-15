package com.moressette.mocontact.entity;

public class User {
	private int uid;
	private String username;
	private String pwd;
	private String realname;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}
	public User(String username, String pwd, String realname) {
		super();
		this.username = username;
		this.pwd = pwd;
		this.realname = realname;
	}
	
}
