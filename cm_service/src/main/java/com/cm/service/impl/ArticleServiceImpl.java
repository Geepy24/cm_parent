package com.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.dao.IArticleDao;
import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;
import com.cm.service.IArticleService;

@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private IArticleDao articleDao ;
	
	@Override
	public Article findById(int articleId) {
		return articleDao.findById(articleId) ;
		
	}

	@Override
	public Article findByTitle(String articleTitle) {
		return articleDao.findByTitle(articleTitle);
	}

	@Override
	public List<Article> findByAuthor(String author) {
		return articleDao.findByAuthor(author);
	}

	@Override
	public List<Article> findAllArticle() {

		return articleDao.findAllArticle();
	}
	@Override
	public List<Article> findAllArticle(Integer currentPage, Integer maxResults) {
		
		return articleDao.findAllArticle(currentPage, maxResults);
	}
	@Override
	public void saveArticle(Article article) {
		articleDao.saveArticle(article) ;
		
	}
	

	@Override
	public void deleteArticle(Article article) {
		articleDao.deleteArticle(article);
		
	}

	@Override
	public void updateArticle(Article article) {
		articleDao.updateArticle(article); 
		
	}

	@Override
	public List<String> find(String content) {
		return articleDao.find(content);
	}
	
	public List<Article> findByUser(Integer userId,Integer currentPage,Integer maxResults){
		
		return articleDao.findByUser(userId, currentPage, maxResults) ;
		
	} 
//-------------------草稿箱----------------------------
	@Override
	public void saveDraft(Draft draft) {

		articleDao.saveDraft(draft);
		
		
	}

	
	@Override
	public Draft findDraftById(int draId) {
		return articleDao.findDraftById(draId) ;
	}

	@Override
	public List<Draft> findAllDraft(Integer userId ,Integer currentPage, Integer maxResults) {

		return articleDao.findAllDraft(userId,currentPage,maxResults);
	}

	@Override
	public Long AllDraftNumber(Integer authorId) {
		
		return articleDao.AllDraftNumber(authorId);
	}

	@Override
	public Long AllArticleNumber() {
		return articleDao.AllArticleNumber();
	}

	@Override
	public void deleteDraft(Integer draId) {
		articleDao.deleteDraft(draId);
		
	}
	@Override
	public void updateDraft(Draft draft) {
		articleDao. updateDraft(draft);
		
	}

	
	//----------------回收站--------------------
	@Override
	public void saveDustbin(Dustbin dustbin) {
		articleDao.saveDustbin(dustbin) ;
	}

	@Override
	public List<Dustbin> findAllDustbin(Integer currentPage, Integer MAXRESULTS) {
		
		return articleDao.findAllDustbin(currentPage,MAXRESULTS);
	}

	@Override
	public Long AllDustbinNumber() {
		return articleDao.AllDustbinNumber();
	}
	
	public void deleteDustbin(Integer dustId) {
		articleDao.deleteDustbin(dustId);
	}

	@Override
	public Dustbin findDustbinById(Integer dustId) {
		return articleDao.findDustbinById(dustId);
	}

	@Override
	public List<Dustbin> findAllDustbinByUser(Integer userId, Integer currentPage, Integer maxresults) {
		
		return articleDao.findAllDustbinByUser(userId, currentPage, maxresults);
	}

	@Override
	public Long AllArticleNumber(Integer userId) {
		return articleDao.AllArticleNumber(userId) ;
	}

	@Override
	public Long AllDustbinNumber(Integer userId) {
		return articleDao.AllDustbinNumber(userId) ;
	}



	

}
