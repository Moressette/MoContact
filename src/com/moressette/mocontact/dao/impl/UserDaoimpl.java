package com.moressette.mocontact.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.moressette.mocontact.DataSource.ConnectionManager;
import com.moressette.mocontact.DataSource.SQLManager;
import com.moressette.mocontact.dao.UserDao;
import com.moressette.mocontact.entity.User;
import com.mysql.jdbc.Connection;

public class UserDaoimpl implements UserDao {
	ConnectionManager connectionManager = new ConnectionManager();
	Connection connection = (Connection) connectionManager.openConnection();
	SQLManager sqlManager = new SQLManager();
	
	@Override
	public User login(User user) {
		String username = user.getUsername();
		String pwd = user.getPwd();
		String strSQL = "select * from users where username=? AND pwd=?";
		Object[] params = { username, pwd };
		ResultSet rs = sqlManager.execQuery(connection, strSQL, params);
		User u = new User();
		try {
			if(rs.next()){
				u.setUid(rs.getInt("uid"));
				u.setUsername(rs.getString("username"));
				u.setPwd(rs.getString("pwd"));
				u.setRealname(rs.getString("realname"));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean register(User user) {
		String strSQL = "insert into users(username,pwd,realname) values(?,?,?)";
		Object[] params = { user.getUsername(), user.getPwd(), user.getRealname() };
		if (sqlManager.execUpdate(connection, strSQL, params) > 0) {
			return true;
		}
		return false;
	}
	/*
	public User findUserInfo(int uid){
		String strSQL = "select * from users where uid=?";
		Object[] params ={ uid };
		ResultSet rs = sqlManager.execQuery(connection, strSQL, params);
		User u = new User();
		try {
			if(rs.next()){
				u.setUsername(rs.getString("username"));
				u.setPwd(rs.getString("pwd"));
				u.setUsername(rs.getString("realname"));
				connectionManager.closeConnection(connection);
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connectionManager.closeConnection(connection);
		return null;
	}*/
	
	public boolean modifyUser(int uid,String username,String pwd,String realname){
		String strSQL = "update users set username=?,pwd=?,realname=? where uid=?";
		Object[] params = {username,pwd,realname,uid};
		if (sqlManager.execUpdate(connection, strSQL, params) > 0) {
			return true;
		}
		return false;
	}
	
}
