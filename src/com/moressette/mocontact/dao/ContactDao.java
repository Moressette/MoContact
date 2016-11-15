package com.moressette.mocontact.dao;

import java.util.List;
import java.util.Map;

import com.moressette.mocontact.entity.Contact;

public interface ContactDao {
	public List<Contact> findAllContacts(int uid);
	public Map<Integer, String> findUserGroups(int uid);
	public Map<String, Integer> findUserGroupsR(int uid);
	public boolean newContact(Contact contact);
	public boolean deleteContact(int cid);
	public Contact findContactByCid(int cid);
	public boolean modifyContact(Contact contact);
	public boolean editGroups(String gname,String gid);
	public boolean addGroups(String gname,int uid);
	public boolean hasNoContactByGidAndUid(int gid,int uid);
	public boolean deleteGroup(int gid);
	public List<Contact> showSingleGroup(int gid,int uid);
	public List<Contact> searchContact(String cname,int uid);
}
