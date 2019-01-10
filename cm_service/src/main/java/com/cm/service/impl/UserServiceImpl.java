package com.cm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cm.dao.IUserDao;
import com.cm.domain.User;
import com.cm.service.IUserService;
@Service("userService")
public class UserServiceImpl implements IUserService{
	@Resource(name="userDao")
	private IUserDao userDao  ;
	
	
	@Override
	public User findUserById(int userId) {
		return userDao.findUserById(userId);
	}
	@Override
	public User findUserByName(String userName) {
		return userDao.findUserByName(userName);
	}
	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}
	@Override
	public void saveUser(User user) {
		 userDao.saveUser(user) ;
		
	}
	@Override
	public void deleteUser(User user) {
		userDao.deleteUser(user) ;
	}
	@Override
	public boolean isAds(int userId) {
		return userDao.isAds(userId) ;
		
	}
	@Override
	public void updateUser(User user) {
		
		userDao.updateUser(user) ;
		
	}
	@Override
	public void deleteById(int userId) {
		
		userDao.deleteById(userId) ;
		
	}
	@Override
	public boolean isUserExist(String userName) {
		
		return userDao.isUserExist(userName) ;
	}

	
	
	
	
}
