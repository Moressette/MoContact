package com.moressette.mocontact.dao;

import com.moressette.mocontact.entity.User;

public interface UserDao {
	public User login(User user);
	public boolean register(User user);
	//public User findUserInfo(int uid);
	public boolean modifyUser(int uid,String username,String pwd,String realname);
}
