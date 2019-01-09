package com.cm.service;

import java.util.List;

import com.cm.domain.User;


public interface IUserService {

	/**
	 * ͨ��id����һ���û�
	 * @return
	 */
	User findUserById(int userId) ;
	/**
	 *	ͨ�����ֲ����û�
	 */
	User findUserByName(String userName) ;
	/**
	 * 	�����û����ж��û��Ƿ����
	 */
	boolean isUserExist(String userName) ;
	/**
	 * 	�����û�id���һ���û��Ƿ��ǹ���Ա
	 */
	boolean isAds(int userId) ;
	/**
	 * 	���������û�
	 * @return
	 */
	List<User> findAllUsers() ;
	
	/**
	 * 	�����û�
	 */
	void saveUser(User user) ;
	
	/**
	 * ɾ���û�
	 */
	void deleteUser(User user) ;
	/**
	 * 	�����û�
	 * @param user
	 */
	void updateUser(User user);
	
	/**
	 * ͨ��idɾ��
	 * @param i 
	 */
	void deleteById(int userId);
}
