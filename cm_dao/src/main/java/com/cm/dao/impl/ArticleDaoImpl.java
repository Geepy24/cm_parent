package com.cm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cm.dao.IArticleDao;
import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;
import com.cm.domain.User;


@Repository("articleDao")
public class ArticleDaoImpl implements IArticleDao {

	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	// ��hibernate session�����ó��������ᱨ��ָ���쳣��
	// getCurrentSessin()û�а��̣߳��������ļ����Ѿ�����

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * ��������
	 */

	@Override
	public void saveArticle(Article article) {
		hibernateTemplate.save(article);

	}

	/**
	 * ͨ������id��������
	 */
	@Override
	public Article findById(int articleId) {
		Article article = null;
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "FROM Article WHERE artId=? ";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, articleId);
			article = (Article) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
		session.close();
		return article;
	}

	/**
	 * ͨ�����±����������
	 * 
	 */
	@Override
	public Article findByTitle(String articleTitle) {
		Article article = null;
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "FROM Article WHERE artTitle=? ";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, articleTitle);
			article = (Article) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
		session.close();
		return article;
	}

	/**
	 * ͨ���������Ʋ�������
	 */
	@Override
	public List<Article> findByAuthor(String author) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		List<Article> articles = null;
		try {
			String hql = "SELECT artTitle FROM Article WHERE artAuthor=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, author);
			articles = query.list();
		} catch (Exception e) {
			return null;
		}
		session.close();
		return articles;
	}

	/**
	 * ����ȫ������
	 */
	@Override
	public List<Article> findAllArticle() {

		return (List<Article>) hibernateTemplate.find("from article");

	}

	/**
	 * ����ȫ�����µ����� ���յ����ҳ��ѯ
	 * 
	 * @param currentPage ��ǰҳ��
	 * @param maxResults  ÿҳ�������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Article> findAllArticle(Integer currentPage, Integer maxResults) {
		List<Article> articles = null;
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "FROM Article ORDER BY artId DESC ";
		try {
			System.out.println("����dao");
			Query query = session.createQuery(hql);
			// ���ÿ�ʼ��ѯ�Ķ������� ��ǰҳ��-1 ����ÿҳ�����Ŀ��
			query.setFirstResult((currentPage - 1) * maxResults);
			// ����ÿҳ�����Ŀ��
			query.setMaxResults(maxResults);
			System.out.println(query);
			articles = query.list();

			System.out.println(articles);
		} catch (Exception e) {
			System.out.println("����catch");
			return null;
		}
		System.out.println("����");
		System.out.println(articles);
		session.close();
		return articles;

	}
	
	/**
	 * ��ҳ����ָ���û����������£�ÿҳ10��
	 */
	public List<Article> findByUser(Article article,Integer currentPage,Integer maxResults){
		
		
		return hibernateTemplate.findByExample(article, (currentPage-1)*maxResults, maxResults) ;
		
	}
	
	
	/**
	 * ��ѯָ������
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<String> find(String content){
		List<String> list = new ArrayList<>() ;
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "SELECT "+content+" FROM Article ORDER BY artId DESC ";
		try {
			System.out.println("����dao");
			Query query = session.createQuery(hql);
			// ���ÿ�ʼ��ѯ�Ķ������� ��ǰҳ��-1 ����ÿҳ�����Ŀ��
			query.setFirstResult(0);
			// ����ÿҳ�����Ŀ��
			query.setMaxResults(10);
			System.out.println(query);
			list = query.list();

		} catch (Exception e) {
			System.out.println("����catch");
			return null;
		}
		System.out.println("����");
		session.close();
		return list;
		
		
		
	}
	
	
	/**
	 * ɾ������
	 */
	@Override
	public void deleteArticle(Article article) {
		hibernateTemplate.delete(article);

	}

	/**
	 * ����������Ϣ
	 */
	@Override
	public void updateArticle(Article article) {
		hibernateTemplate.update(article);

	}
//----------------------�ݸ������----------------------
	@Override
	public void saveDraft(Draft draft) {
		hibernateTemplate.save(draft) ;
		
	}

	@Override
	public Draft findDraftById(int draId) {
		return (Draft) hibernateTemplate.find("From Draft WHERE draId=?", draId).get(0) ;
		
	}
	/**
	 * 	ͳ������������Ŀ
	 */
	@Override
	public Long AllArticleNumber() {
		
		return  (Long) hibernateTemplate.find("SELECT COUNT(*) FROM Article ").get(0) ;
				
	}
	/**
	 * 	�����û�idͳ������������Ŀ
	 */
	@Override
	public Long AllArticleNumber(Integer userId) {
		User user = new User() ;
		user.setUserId(userId);
		String sql = "SELECT COUNT(*) FROM Article WHERE user=:id" ;
		String param = "id" ;
		User value = user  ;
		return  (Long) hibernateTemplate.findByNamedParam(sql, param, value).get(0) ;
				
	}

	/**
	 * 	��ҳ��ѯ��find�ķ�ҳ����
	 */
	@Override
	public List<Draft> findAllDraft(Draft draft , Integer currentPage, Integer maxResults) {
		
		
		return hibernateTemplate.findByExample(draft, (currentPage-1)*maxResults, maxResults) ;
		
		
	}
	/**
	 * 	����authorIdͳ���ܹ���Ŀ
	 */
	@Override
	public Long AllDraftNumber(Integer authorId) {
		User user = new User() ;
		user.setUserId(authorId);
		String sql = "SELECT COUNT(*) FROM Draft WHERE user=:id" ;
		String param = "id" ;
		User value = user  ;
		return  (Long) hibernateTemplate.findByNamedParam(sql, param, value).get(0) ;
				
	}
	@Override
	public void deleteDraft(Integer draId) {
		hibernateTemplate.delete(this.findDraftById(draId));
		
	}

	
	//------------------����վ����--------------------------
	
	/**
	 * ͨ��id ����
	 */
	@Override
	public Dustbin findDustbinById(Integer dustId) {
		
		return (Dustbin) hibernateTemplate.find("FROM Dustbin where dustId=?",dustId).get(0) ;
	}
	
	@Override
	public void saveDustbin(Dustbin dustbin) {
		
		hibernateTemplate.save(dustbin) ;
		
	}
	/**
	 * �������ķ�ҳ
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Dustbin> findAllDustbin(Integer currentPage, Integer MAXRESULTS) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Dustbin.class) ;
		return (List<Dustbin>) hibernateTemplate.findByCriteria(criteria, (currentPage-1)*MAXRESULTS, MAXRESULTS) ;
	}
	/**
	 *	��������
	 */
	@Override
	public Long AllDustbinNumber() {
		return (Long) hibernateTemplate.find("SELECT COUNT(*) FROM Dustbin").get(0);
	}
	/**
	 *	����id��������
	 */
	@Override
	public Long AllDustbinNumber(Integer userId) {
		User user = new User() ;
		user.setUserId(userId);
		String sql = "SELECT COUNT(*) FROM Dustbin WHERE user=:id" ;
		String param = "id" ;
		User value = user  ;
		return  (Long) hibernateTemplate.findByNamedParam(sql, param, value).get(0) ;
	}
	
	
	/**
	 * 	����ɾ��
	 */
	@Override
	public void deleteDustbin(Integer dustId) {
//		
//		Dustbin dustbin = this.findDustbinById(dustId) ;
//		dustbin.setUser(null);
//		hibernateTemplate.update(dustbin);
//		
//		System.out.println("�����ϵ");
//		
//		System.out.println(this.findDustbinById(dustId).getUser() == null);
		
		hibernateTemplate.delete(this.findDustbinById(dustId));
		
			
	}

	@Override
	public List<Dustbin> findAllDustbinByUser(Dustbin dustbin, Integer currentPage, Integer maxresults) {
		return hibernateTemplate.findByExample(dustbin, (currentPage-1)*maxresults, maxresults);
	}
	//�޸Ĳݸ�
	@Override
	public void updateDraft(Draft draft) {
		hibernateTemplate.update(draft);
		
	}


}
