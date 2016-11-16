package com.moressette.mocontact.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moressette.mocontact.DataSource.ConnectionManager;
import com.moressette.mocontact.DataSource.SQLManager;
import com.moressette.mocontact.dao.ContactDao;
import com.moressette.mocontact.entity.Contact;
import com.moressette.mocontact.util.Log;
import com.mysql.jdbc.Connection;

public class ContactDaoimpl implements ContactDao {
	ConnectionManager connectionManager = new ConnectionManager();
	Connection connection = (Connection) connectionManager.openConnection();
	SQLManager sqlManager = new SQLManager();
	
	@Override
	public List<Contact> findAllContacts(int uid) {
		List<Contact> list = new ArrayList<Contact>();
		String strSQL = "select contacts.cid,contacts.cname,contacts.csex,contacts.cphone,contacts.ctel,contacts.cemail,contacts.cqq,contacts.cwork,contacts.caddress,contacts.gid,groups.gname FROM contacts,groups WHERE contacts.uid=? AND contacts.gid=groups.gid";
		Object[] params ={ uid };
		ResultSet rs = sqlManager.execQuery(connection, strSQL, params);
		Log.out(strSQL, params);
		try{
			while(rs.next()){
				Contact contact = new Contact();
				contact.setCid(rs.getInt("cid"));
				contact.setCname(rs.getString("cname"));
				contact.setCsex(rs.getString("csex"));
				contact.setCphone(rs.getString("cphone"));
				contact.setCtel(rs.getString("ctel"));
				contact.setCemail(rs.getString("cemail"));
				contact.setCqq(rs.getString("cqq"));
				contact.setCwork(rs.getString("cwork"));
				contact.setCaddress(rs.getString("caddress"));
				contact.setGid(rs.getInt("gid"));
				contact.setGname(rs.getString("gname"));

				list.add(contact);
			}
			return list;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<Integer, String> findUserGroups(int uid){
		Map<Integer, String> map = new HashMap<Integer, String>();
		String strSQL = "select * from groups where uid=?";
		Object[] params ={ uid };
		ResultSet rs = sqlManager.execQuery(connection, strSQL, params);
		map.put(0, "未分组");
		try {
			while(rs.next()){
				int gid = rs.getInt("gid");
				String gname = rs.getString("gname");
				map.put(gid,gname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public Map<String, Integer> findUserGroupsR(int uid){
		Map<String, Integer> reverseMap = new HashMap<String, Integer>();
		String strSQL = "select * from groups where uid=?";
		Object[] params ={ uid };
		ResultSet rs = sqlManager.execQuery(connection, strSQL, params);
		reverseMap.put("未分组",0);
		try {
			while(rs.next()){
				int gid = rs.getInt("gid");
				String gname = rs.getString("gname");
				reverseMap.put(gname,gid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reverseMap;
	}
	
	public boolean newContact(Contact contact){
		String strSQL = "insert into contacts(cname,csex,cphone,ctel,cemail,cqq,cwork,caddress,gid,uid) values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params ={ contact.getCname(),contact.getCsex(),contact.getCphone(),contact.getCtel(),contact.getCemail(),contact.getCqq(),contact.getCwork(),contact.getCaddress(),contact.getGid(),contact.getUid() };
		if ( sqlManager.execUpdate(connection, strSQL, params) > 0 ){
			return true;
		}
		return false;
	}
	
	public boolean deleteContact(int cid){
		String strSQL = "delete from contacts where cid=?";
		Object[] params = { cid };
		if (sqlManager.execUpdate(connection, strSQL, params) > 0) {
			return true;
		}
		return false;
	}
	
	public Contact findContactByCid(int cid){
		String strSQL = "select * from contacts where cid=?";
		Object[] params = { cid };
		Contact contact = new Contact();
		ResultSet rs = sqlManager.execQuery(connection, strSQL, params);
		try {
			while(rs.next()){
				contact.setCid(rs.getInt("cid"));
				contact.setCname(rs.getString("cname"));
				contact.setCsex(rs.getString("csex"));
				contact.setCphone(rs.getString("cphone"));
				contact.setCtel(rs.getString("ctel"));
				contact.setCemail(rs.getString("cemail"));
				contact.setCqq(rs.getString("cqq"));
				contact.setCwork(rs.getString("cwork"));
				contact.setCaddress(rs.getString("caddress"));
				contact.setGid(rs.getInt("gid"));
				contact.setUid(rs.getInt("uid"));
			}
			return contact;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean modifyContact(Contact contact){
		String strSQL = "update contacts set cname=?,csex=?,cphone=?,ctel=?,cemail=?,cqq=?,cwork=?,caddress=?,gid=? where cid=?";
		Object[] params ={ contact.getCname(),contact.getCsex(),contact.getCphone(),contact.getCtel(),contact.getCemail(),contact.getCqq(),contact.getCwork(),contact.getCaddress(),contact.getGid(),contact.getCid() };
		//Log.out(strSQL, params);
		if ( sqlManager.execUpdate(connection, strSQL, params) > 0 ){
			return true;
		}
		return false;
	}
	
	public boolean editGroups(String gname,String gid){
		String strSQL = "update groups set gname=? where gid=?";
		Object[] params = { gname,gid };
		if ( sqlManager.execUpdate(connection, strSQL, params) > 0 ){
			return true;
		}
		return false;
	}
	
	public boolean addGroups(String gname,int uid){
		String strSQL = "insert into groups (gname,uid) values (?,?)";
		Object[] params = { gname,uid };
		if ( sqlManager.execUpdate(connection, strSQL, params) > 0 ){
			return true;
		}
		return false;
	}
	
	public boolean hasNoContactByGidAndUid(int gid,int uid){
		String strSQL = "select * from contacts where gid=? AND uid=?";
		Object[] params = { gid,uid };
		ResultSet rs = sqlManager.execQuery(connection, strSQL, params);
		try {
			if(rs.next()==false){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteGroup(int gid){
		String strSQL = "delete from groups where gid=?";
		Object[] params = { gid };
		if (sqlManager.execUpdate(connection, strSQL, params) > 0) {
			return true;
		}
		return false;
	}

	public List<Contact> showSingleGroup(int gid,int uid){
		List<Contact> list = new ArrayList<Contact>();
		String strSQL = "select contacts.cid,contacts.cname,contacts.csex,contacts.cphone,contacts.ctel,contacts.cemail,contacts.cqq,contacts.cwork,contacts.caddress,contacts.gid,groups.gname FROM contacts,groups WHERE contacts.uid=? AND contacts.gid=groups.gid AND contacts.gid=?";
		Object[] params ={ uid,gid };
		ResultSet rs = sqlManager.execQuery(connection, strSQL, params);
		try{
			while(rs.next()){
				Contact contact = new Contact();
				contact.setCid(rs.getInt("cid"));
				contact.setCname(rs.getString("cname"));
				contact.setCsex(rs.getString("csex"));
				contact.setCphone(rs.getString("cphone"));
				contact.setCtel(rs.getString("ctel"));
				contact.setCemail(rs.getString("cemail"));
				contact.setCqq(rs.getString("cqq"));
				contact.setCwork(rs.getString("cwork"));
				contact.setCaddress(rs.getString("caddress"));
				contact.setGid(rs.getInt("gid"));
				contact.setGname(rs.getString("gname"));

				list.add(contact);
			}
			return list;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Contact> searchContact(String cname,int uid){
		List<Contact> list = new ArrayList<Contact>();
		String strSQL = "select contacts.cid,contacts.cname,contacts.csex,contacts.cphone,contacts.ctel,contacts.cemail,contacts.cqq,contacts.cwork,contacts.caddress,contacts.gid,groups.gname FROM contacts,groups WHERE contacts.cname like ? AND contacts.uid=? AND contacts.gid=groups.gid";
		Object[] params = { "%"+cname+"%",uid };
		ResultSet rs = sqlManager.execQuery(connection, strSQL, params);
		try{
			while(rs.next()){
				Contact contact = new Contact();
				contact.setCid(rs.getInt("cid"));
				contact.setCname(rs.getString("cname"));
				contact.setCsex(rs.getString("csex"));
				contact.setCphone(rs.getString("cphone"));
				contact.setCtel(rs.getString("ctel"));
				contact.setCemail(rs.getString("cemail"));
				contact.setCqq(rs.getString("cqq"));
				contact.setCwork(rs.getString("cwork"));
				contact.setCaddress(rs.getString("caddress"));
				contact.setGid(rs.getInt("gid"));
				contact.setGname(rs.getString("gname"));

				list.add(contact);
			}
			return list;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
