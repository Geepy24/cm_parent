package com.cm.service;

import java.util.List;

import com.cm.domain.MovieCheck;
import com.cm.domain.PictureCheck;
import com.cm.domain.Resource;
import com.cm.domain.User;

/**
 * 	��Դ���ҵ���ӿ�
 * @author Huangjiping
 *
 */
public interface IResourceService {

	/**
	 * 	������Դ
	 */
	void saveResource(Resource resource) ;
	/**
	 * 	ͨ�����ʱ�䵹���ҳ����������Դ
	 */
	List<Resource> findAllResource(String tag , Integer currentPage,Integer maxResults) ;
	/**
	 * 	ͨ��������û�id��ʱ�䵹���ҳ����������Դ
	 */
	List<Resource> findAllResource(String tag , Integer userId ,Integer currentPage,Integer maxResults) ;
	
	/**
	 * 	ͨ���û�id��������
	 */
	Long AllResourceNumber(User user);
	/**
	 * 	ͨ��id�ͷ��������Դ����
	 */
	Long AllResourceNumber(User user , String tag);
	/**
	 * 	���ҷ����µ���Դ����
	 */
	Long AllResourceNumber(String tag);
	/**
	 * ������Դ����
	 */
	Long AllResourceNumber();
	/**
	 * ɾ����Դ
	 */
	void deleteResource(Integer resId) ;
	/**
	 * 	ͨ��id������Դ
	 */
	Resource findResourceById(Integer resId) ;
	/**
	 * ͨ��id������һ����¼��id
	 */
	Integer nextResourceId(Integer resId,String tag) ;
	/**
	 * ͨ��id������һ������¼��id
	 */
	Integer preResourceId(Integer resId,String tag) ;
	//---------------------------------�������Ƶ��ͼƬ����---------------------------
	//---------------------------------ͼƬ,��Ƶ���-------------------------------------------
		/**
		 * ��������ͼƬ
		 */
		void savePictureCheck(PictureCheck pictureCheck) ;
		/**
		 * ����������Ƶ
		 */
		void saveMovieCheck(MovieCheck movieCheck) ;
		/**
		 * ����picId��ѯ����pictureCheck
		 */
		PictureCheck findPCById(Integer picId) ;
		/**
		 * ����movId��ѯ����movieCheck
		 */
		MovieCheck findMCById(Integer movId) ;
		/**
		 * ����checkTag��ҳ�������е�picCheck
		 */
		List<PictureCheck> findPCsByCheckTag(Integer checkTag,Integer currentPage,Integer maxResults) ;
		/**
		 * ����checkTag��ҳ�������е�movCheck
		 */
		List<MovieCheck> findMCsByCheckTag(Integer checkTag,Integer currentPage,Integer maxResults) ;
		/**
		 * ����userId��ҳ�������е�picCheck
		 */
		List<PictureCheck> findPCsByUserId(Integer userId,Integer currentPage,Integer maxResults) ;
		/**
		 * ����userId��ҳ�������е�movCheck
		 */
		List<MovieCheck> findMCsByUserId(Integer userId,Integer currentPage,Integer maxResults) ; 
		/**
		 * ����picId�޸�pc
		 */
		void updatePictureCheck(PictureCheck pictureCheck) ;
		/**
		 * ����movId�޸�mc
		 */
		void updateMovieCheck(MovieCheck movieCheck) ;
		/**
		 * ����checkTag��userId��ҳ����pc
		 */
		List<PictureCheck> findPCsByTagAndUserId(Integer checkTag,Integer userId,Integer currentPage,Integer maxResults) ;
		/**
		 * ����checkTag��userId��ҳ����mc
		 */
		List<MovieCheck> findMCsByTagAndUserId(Integer checkTag,Integer userId,Integer currentPage,Integer maxResults) ;
		/**
		 * ����ɾ����ͨ������Ƶ
		 */
		/**
		 * ����ɾ�����ͨ����ͼƬ
		 */
		/**
		 * ���Ҵ������Ƶ����
		 */
		Long findAllMcs() ;
		/**
		 * ���Ҵ����ͼƬ����
		 */
		Long findAllPcs() ;
		/**
		 * ����id�����������Ƶ����
		 */
		Long findAllMcsByUserId(Integer userId) ;
		/**
		 * ����id���������ͼƬ����
		 */
		Long findAllPcsByUserId(Integer userId) ;
		
		
}
