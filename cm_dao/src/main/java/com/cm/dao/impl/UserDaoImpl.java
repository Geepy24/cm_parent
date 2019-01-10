package com.cm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cm.dao.IUserDao;
import com.cm.domain.User;
@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate ;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public User findUserById(int userId) {
		
		User user = null ;
		try {
			user = (User) hibernateTemplate.find("from User where userId="+userId).get(0);
		}
		catch(Exception e){
			return user;
		}
		return user;
		
	}

	@Override
	public User findUserByName(String userName) {
			User user = null ;
			try {
				String hql = "from User where userName=?" ;
				Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql) ;
				query.setParameter(0, userName) ;
				user =  (User) query.list().get(0) ;
			}catch (RuntimeException e) {
				return null ;
			}
			return user ;
			
		
	}

	@Override
	public List<User> findAllUsers() {
		return (List<User>) hibernateTemplate.find("from User");
	}

	@Override
	public void saveUser(User user) {
		
		hibernateTemplate.save(user) ;
		
	}

	@Override
	public void deleteUser(User user) {
		hibernateTemplate.delete(user);
	}
	
	//����û��Ƿ����Ա
	@Override
	public boolean isAds(int userId) {
		
		String hql = "from Ads where userId=?";
		try {			
			Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql) ;
			query.setParameter(0, userId) ;
			
		
		}catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
		
		return true;
	}

	@Override
	public void updateUser(User user) {
		hibernateTemplate.update(user);
		
	}

	
	@Override
	public void deleteById(int userId) {
		String hql = "delete from Ads where userId=?" ;
		try {
			Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql) ;
			query.setParameter(0, userId) ;
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

	@Override
	public boolean isUserExist(String userName) {
		 String hql = "select userId from User where userName=?" ;
		try {
			Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql) ;
			query.setParameter(0, userName) ;
			
			System.out.println(query.list().get(0)) ;
		}catch(Exception e){
			return false ;
		}
		
		return true ;	
	}

}
