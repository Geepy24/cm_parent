package com.cm.service;

import java.util.List;

import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;

/**
 * 	���µ�ҵ���ӿ�
 * @author Huangjiping
 *
 */
public interface IArticleService {

	/**
	 * 	��������
	 */
	void saveArticle(Article article) ;
	/**
	 * 	ɾ������
	 */
	void deleteArticle(Article article) ;
	/**
	 * 	�޸�����
	 */
	void updateArticle(Article article) ;
	
	/**ͨ��id��������
	 * 
	 */
	Article findById(int articleId) ;
	/**
	 * 	ͨ�������������
	 */
	Article findByTitle(String articleTitle) ;
	/**
	 * 	ͨ�����߲�������
	 */
	List<Article> findByAuthor(String author) ;
	/**
	 * 	������������
	 */
	List<Article> findAllArticle() ;
	/**
	 * 	��ҳ������������
	 * 
	 * @param currentPage
	 * @param maxResults
	 * @return
	 */
	List<Article> findAllArticle(Integer currentPage,Integer maxResults ) ;
	/**
	 * ��������ָ��������
	 * @return ����10�����������
	 */
	List<String> find(String content) ;
	/**
	 * ������������
	 */
	Long AllArticleNumber();
	Long AllArticleNumber(Integer userId);
	/**
	 * ɾ���ݸ�
	 */
	void deleteDraft(Integer draId) ;
	/**
	 * ��ҳ����ָ���û����������£�ÿҳ10��
	 */
	public List<Article> findByUser(Article article,Integer currentPage,Integer maxResults) ;
	//-----------�ݸ���------------
	/**
	 * �������ݸ���
	 */
	void saveDraft(Draft draft) ;
	/**
	 * 	����Id���Ҳݸ�
	 * @param draId
	 */
	Draft findDraftById(int draId);
	/**
	 * ����authorName���ҷ�ҳ�������вݸ�
	 */
	List<Draft> findAllDraft(Draft draft ,Integer currentPage,Integer maxResults);
	/**
	 * ����Id���Ҳݸ�����
	 * @param authorId
	 * @return
	 */
	
	Long AllDraftNumber(Integer authorId);
	//--------------����վ����--------------------
	/**
	 * 	�������վ
	 */
	void saveDustbin(Dustbin dustbin) ;
	/**
	 * 	��ҳ��������Dustbin
	 */
	List<Dustbin> findAllDustbin(Integer currentPage, Integer MAXRESULTS);
	/**
	 * ����dustbin����
	 */
	Long AllDustbinNumber() ;
	Long AllDustbinNumber(Integer userId);
	/**
	 * 	����ɾ��
	 * @param dustId
	 */
	void deleteDustbin(Integer dustId);
	/**
	 * ͨ��id����
	 * @param dustId
	 * @return
	 */
	Dustbin findDustbinById(Integer dustId) ;
	/**
	 * ͨ���û������û��Ļ���վ
	 * @param user
	 * @param currentPage
	 * @param maxresults
	 * @return
	 */
	List<Dustbin> findAllDustbinByUser(Dustbin dustbin, Integer currentPage, Integer maxresults);
	/**
	 * �޸Ĳݸ�
	 * @param draft
	 */
	void updateDraft(Draft draft);
	
	
}
