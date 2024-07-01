package com.lcomputerstudyy.service;

import java.util.ArrayList;

import com.lcomputerstudyy.dao.UserDAO;
import com.lcomputerstudyy.vo.Pagination;
import com.lcomputerstudyy.vo.User;

public class UserService {
	private static UserService service = null;
	private static UserDAO dao = null;
	
	private UserService() {
		
	}
	
	public static UserService getInstance() {
		if(service == null) {
			service = new UserService();
			dao = UserDAO.getInstance(); //?
		}
		return service;
	}
	
	public ArrayList<User> getUsers() {
		return dao.getUsers(); 
	}
	
	public void insertUser(User user) {
		dao.insertUser(user);
	}
	//detail function
	public User getUser(int u_idx) {
		return dao.getUser(u_idx);
	}
	
	public void updateUser(User user) {
		dao.updateUser(user);
	}
	
	public void deleteUser(int u_idx) {
		dao.deleteUser(u_idx);
	}
	public int getUsersCount(Pagination pagination) {
		return dao.getUsersCount(pagination);
	}
	public ArrayList<User> getUsers(Pagination pagination) {
		return dao.getUsers(pagination);
	}
	public User loginUser(String idx, String pw) {
		return dao.loginUser(idx, pw);
	}
	

//	public ArrayList<User> searchUsers(String category, String keyword, Pagination pagination) {
//		return dao.searchUsers(category, keyword, pagination);
//	}
}
