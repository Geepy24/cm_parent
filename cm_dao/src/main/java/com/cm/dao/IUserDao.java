package com.cm.dao;

import java.util.List;

import com.cm.domain.User;

public interface IUserDao {
	
	//ͨ��id����
	User findUserById(int userId);
	//ͨ���û�������
	User findUserByName(String userName);
	//��������
	List<User> findAllUsers();
	//�����û�
	void saveUser(User user);
	//ɾ���û�
	void deleteUser(User user);
	//����û��Ƿ����Ա
	boolean isAds(int userId) ;
	//�����û�
	void updateUser(User user);
	//ͨ��idɾ���û�
	void deleteById(int userId);
	//ͨ���û����ж��û��Ƿ����
	boolean isUserExist(String userName);
}
