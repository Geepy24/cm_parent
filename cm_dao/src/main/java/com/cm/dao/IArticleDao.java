package com.cm.dao;

import java.util.List;

import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;

public interface IArticleDao {
	/**
	 * 	保存文章
	 */
	void saveArticle(Article article) ;
	/**
	 * 	删除文章
	 */
	void deleteArticle(Article article) ;
	/**
	 * 	更新文章
	 */
	void updateArticle(Article article) ;
	/**
	 * 通过文章id查找文章
	 */
	Article findById(int articleId) ;
	/**
	 * 	通过文章标题查找文章
	 */
	Article findByTitle(String articleTitle) ;
	/**
	 *  通过作者查找作者的所有文章（无分页）
	 */
	List<Article> findByAuthor(String author) ;
	/**
	 * 	查找所有文章（无分页）
	 */
	List<Article> findAllArticle() ;
	/**
	 * 	分页查找所有文章
	 */
	List<Article> findAllArticle(Integer currentPage,Integer maxResults ) ;
	/**
	 * 查找文章指定内容，倒叙返回十条
	 */
	List<String> find(String content) ;
	/**
	 * 查找所有文章总数
	 */
	Long AllArticleNumber();
	/**
	 * 查找指定用户的所有文章总数
	 */
	Long AllArticleNumber(Integer userId);
	/**
	 * 分页查找用户的所有文章
	 */
	public List<Article> findByUser(Article article,Integer currentPage,Integer maxResults);
//---------------草稿箱操作------------------
	
	/**
	 * 保存草稿
	 */
	void saveDraft(Draft draft) ;
	/**
	 * 	通过草稿id查找草稿
	 * @param draId
	 */
	Draft findDraftById(int draId);
	/**
	 * 根据用户分页查找所有草稿
	 */
	List<Draft> findAllDraft(Draft draft,Integer currentPage,Integer maxResults);
	/**
	 * 根据用户Id查找草稿总数
	 * @param authorId
	 * @return
	 */
	Long AllDraftNumber(Integer authorId);
	/**
	 * 更新草稿
	 * @param draft
	 */
	void updateDraft(Draft draft);
	/**
	 * 	删除所有草稿
	 */
	void deleteDraft(Integer draId) ;
	
	
	//---------------------回收站操作-------------------
	/**
	 *  保存
	 * @param dustbin
	 */
	void saveDustbin(Dustbin dustbin);
	/**
	 * 分页查所有
	 */
	List<Dustbin> findAllDustbin(Integer currentPage, Integer MAXRESULTS);
	/**
	 * 查找回收站文章总数
	 */
	Long AllDustbinNumber() ;
	/**
	 * 查找指定用户的回收站文章总数
	 * @param userId
	 * @return
	 */
	Long AllDustbinNumber(Integer userId);
	/**
	 * 删除
	 * @param dustId
	 */
	void deleteDustbin(Integer dustId);
	/**
	 * 通过回收站文章id查找
	 * @param dustId
	 * @return
	 */
	Dustbin findDustbinById(Integer dustId) ;
	/**
	 * 分页查找用户的回收站文章总数
	 * @param user
	 * @param currentPage
	 * @param maxresults
	 * @return
	 */
	List<Dustbin> findAllDustbinByUser(Dustbin dustbin, Integer currentPage, Integer maxresults);
	
	
	
	
}
