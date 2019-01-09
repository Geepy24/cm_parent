package com.cm.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;

public class WebUtils {
	 
	
	/**
	 * 	�õ�webӦ�õĸ�Ŀ¼
	 * @return
	 */
	public static String getAppPath() {
		ActionContext actionContext = ActionContext.getContext();
		 ServletContext servletContext = (ServletContext) actionContext.get(ServletActionContext.SERVLET_CONTEXT);
		return servletContext.getRealPath("/");
	}

	/**
	 * 	����ʱ�����������
	 */
	
	public static String random() {
		String f ;
		long l =System.currentTimeMillis()%1000 ;
		f = String.valueOf(l) ;
		return f ;
	}
	@Test
	public void test() {
		System.out.println(random());
	}

	/**
	 * 	�ö�����������ǰ���ı��༭��������ı�������µ�html
	 * 	������ȡģ��html���滻ģ��html����Ӧ���ݣ������µ�html�ļ��������
	 * 	����ֵ�������ɵ�html��uri��ַ
	 * 	���������±��⣬���ߣ����ݣ�����ʱ��
	 */
	public static String createArticle(String title,String author,String content,String pubTime) {
		//����ģ��html��·��
				String articleTemplatePath = WebUtils.getAppPath() +"articleTemplate.html" ;
				//System.out.println(articleTemplatePath);
				
				//��ȡģ�壬��ģ���е���Ӧ�����滻�����µ���Ӧ���ݣ��ٽ��ļ�д��
				//ʹ��bufferedReader
				String str = "" ;
				try {
					String tempStr = "" ;
					FileInputStream is = new FileInputStream(articleTemplatePath) ;
					BufferedReader br = new BufferedReader(new InputStreamReader(is)) ;
					while ((tempStr = br.readLine()) != null) {
						str = str + tempStr ;
					}
					System.out.println("��ȡ��ģ��"+str);
					is.close(); 
					
				} catch (IOException e) {
					e.printStackTrace(); 
					
				}
				//���ɵ��µ�html�ĵ�ַ������Ϊ����+��λ�����
				//��ŵ�uri�������web��Ŀͬ����CMVolleyball-article�ļ�����
				//����������Ŀ�ڣ����²�������������
			//	pathwebapp = pathwebapp.substring(0,pathwebapp.lastIndexOf("\\"));
				String webPath = WebUtils.getAppPath() ;
				
				String newArticlePath =  webPath.substring(0,webPath.lastIndexOf("\\"))
						+ "-article\\" 
						+ title +"-"+WebUtils.random()+".html" ;
				System.out.println("�����µ�uri��ַ��"+newArticlePath);
				try {
					
					//���ύ�������µ������滻ģ������Ӧ������
					//��Ϊ�����п��ܻ��������ַ�ʹ��replaceAll���ִ�������Ҫ�ȴ��������ַ�
					title = java.util.regex.Matcher.quoteReplacement(title) ;
					author = java.util.regex.Matcher.quoteReplacement(author) ;
					content = java.util.regex.Matcher.quoteReplacement(content) ;
					
					str = str.replaceAll("###title###", title) ;
					str = str.replaceAll("###author###",author) ;
					str = str.replaceAll("###content###", content) ;
					
					System.out.println("�滻�������"+str);
					//д�����µ�html��
					
					File newArticle = new File(newArticlePath) ;
					//outputStreamWriter����ָ��д���ı���
					OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(newArticle),"UTF-8") ;
					
					BufferedWriter bw = new BufferedWriter(ow) ;
					bw.write(str);
					bw.close();
					str = "" ;
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		
			return newArticlePath ;
				
				
				
				
				
				
	}
	/**
	 * �õ� yyyy-mm-dd�ĵ�ǰʱ��
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(currentTime); 
	    return dateString ;
	}
	
	
	
}
