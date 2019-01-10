package com.cm.service;

import java.util.List;

import com.cm.domain.User;


public interface IUserService {

	/**
	 * 通过id查找
	 * @param userId
	 * @return
	 */
	User findUserById(int userId) ;
	/**
	 * 通过用户名查找
	 * @param userName
	 * @return
	 */
	User findUserByName(String userName) ;
	/**
	 * 通过用户名判断用户是否存在
	 * @param userName
	 * @return
	 */
	boolean isUserExist(String userName) ;
	/**
	 * 检查用户是否管理员
	 * @param userId
	 * @return
	 */
	boolean isAds(int userId) ;
	/**
	 * 查找所有
	 * @return
	 */
	List<User> findAllUsers() ;
	
	/**
	 * 	保存用户
	 */
	void saveUser(User user) ;
	
	/**
	 * 删除用户
	 */
	void deleteUser(User user) ;
	/**
	 * 	更新用户
	 * @param user
	 */
	void updateUser(User user);
	
	/**
	 * ͨ通过id删除
	 * @param i 
	 */
	void deleteById(int userId);
}
