package com.cm.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * ������Ƶ����
 * @author mac
 *
 */
public class movieUtils {

	public static final String ffmpegPath = "/usr/local/Cellar/ffmpeg/4.1_1/bin/ffmpeg" ; 
	
	
/**
 * ��ȡ��Ƶ������ͼ������ŵ���Ӧ�ļ���
 * @param ffmpeg : ffmpeg.exe��λ��
 * @param upFilePath : Ҫ�������Ƶ�ļ���λ��
 * @param mediaPicPath 	: ����ͼ��ŵ�λ��
 */
	
	public static void handler(String ffmpegPath,String upFilePath,String mediaPicPath) {
		List<String> cutpic = new ArrayList<>(); 
		cutpic.add(ffmpegPath);// ��Ƶ��ȡ���ߵ�λ�� 
		cutpic.add("-i"); // ��Ӳ�����-i�����ò���ָ��Ҫת�����ļ� 
		cutpic.add(upFilePath); // ��Ƶ�ļ�·�� 
		cutpic.add("-y"); 
		cutpic.add("-f"); 
		cutpic.add("image2"); 
		cutpic.add("-ss"); // ��Ӳ�����-ss�����ò���ָ����ȡ����ʼʱ�� 
		cutpic.add("1"); // �����ʼʱ��Ϊ��1�� 
		cutpic.add("-t"); // ��Ӳ�����-t�����ò���ָ������ʱ�� 
		cutpic.add("0.001"); // ��ӳ���ʱ��Ϊ1���� 
		cutpic.add("-s"); // ��Ӳ�����-s�����ò���ָ����ȡ��ͼƬ��С 
		cutpic.add("800*600"); // ��ӽ�ȡ��ͼƬ��СΪ800*600 
		cutpic.add(mediaPicPath); // ��ӽ�ȡ��ͼƬ�ı���·��
		
		boolean mark = true;  
		
		//��������Ӧ�ó���
        ProcessBuilder builder = new ProcessBuilder();  
        try {  

            builder.command(cutpic);  
            builder.redirectErrorStream(true);  
            // ���������Ϊ true�����κ���ͨ���˶���� start() ���������ĺ����ӽ������ɵĴ�������������׼����ϲ���  
            //������߾���ʹ�� Process.getInputStream() ������ȡ����ʹ�ù���������Ϣ����Ӧ�������ø�����  
            builder.start();  
        } catch (Exception e) {  
            mark = false;  
            System.out.println(e);  
            e.printStackTrace();  
        }  

	}	
	//������Ϻ����Ӧ�ļ����Ƿ��и��ļ���û�еĻ����׳��쳣
	
}
