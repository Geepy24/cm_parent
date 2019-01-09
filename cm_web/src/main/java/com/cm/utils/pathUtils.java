package com.cm.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ·���Ĺ�����
 * @author mac
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:WebContent/WEB-INF/applicationContext.xml") 
public class pathUtils {
	
	/**
	 * ��ǰ��Ŀ�ľ���·��
	 */
	
	public static String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	
	
	/**
	 * �õ���Ƶ�ļ��洢Ŀ¼�ľ���·��
	 */
	
	public static String moviePath() {
		HttpServletRequest request = ServletActionContext.getRequest() ;
		String realpath = request.getRealPath("/") ;
		String root = request.getContextPath() ;
		String [] temp = root.split("/") ;
		temp = realpath.split(temp[1]) ;
		String filePath = temp[0]+"mov" ;
		return filePath ;
	}
	/**
	 * ��Ƶ����ͼ���ļ���λ��
	 * @return
	 */
	public static String mpPath() {
		HttpServletRequest request = ServletActionContext.getRequest() ;
		String realpath = request.getRealPath("/") ;
		String root = request.getContextPath() ;
		String [] temp = root.split("/") ;
		temp = realpath.split(temp[1]) ;
		String filePath = temp[0]+"media_preview" ;
		return filePath ;
	}
	/**
	 * �õ�ͼƬ�ļ��е�·��
	 * @return
	 */
	public static String picturePath() {
		HttpServletRequest request = ServletActionContext.getRequest() ;
		String realpath = request.getRealPath("/") ;
		String root = request.getContextPath() ;
		String [] temp = root.split("/") ;
		temp = realpath.split(temp[1]) ;
		String filePath = temp[0]+"pic" ;
		return filePath ;
	}
	
	
	
	
}
