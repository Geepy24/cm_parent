package com.cm.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;
import com.cm.domain.MovieCheck;
import com.cm.domain.PictureCheck;
import com.cm.domain.Resource;

import net.sf.json.JSONObject;

/**
 * ����JSON����
 * @author mac
 *
 */
public class jsonUtils{
	/**
	 * ��articles��ΪJSON�ַ�������
	 * @param list
	 * @return
	 */
	public static String artListToJsonString(List<Article> list) {
		
		Iterator<Article> iterator = list.iterator() ;
		Map<String ,String> map = new HashMap<String, String>() ;
		int i = 1 ;
		while (iterator.hasNext()) {
			Article article = iterator.next() ;
			String artTitle = article.getArtTitle() ;
			String pubTime = article.getPubTime() ;
			String lastMod = article.getLastMod() ;
			String artId = String.valueOf(article.getArtId()) ;
		//	System.out.println(i+"-"+artTitle);
			//�ϲ���һ��String�У�ͨ��###�ָ�
			String content = artTitle+"###"+pubTime+"###"+lastMod+"###"+artId ;
			map.put(String.valueOf(i), content) ;
			
			i++ ;
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map) ;
		return jsonObject.toString() ;
	}
	/**
	 * ��drafts��ΪJSON�ַ���
	 * @param list
	 * @return
	 */
	public static String draListToJsonString(List<Draft> list) {
			
		Map<String ,String> map = new HashMap<String, String>() ;
		Iterator<Draft> iterator = list.iterator() ;
		int i = 1 ;
		while (iterator.hasNext()) {
			Draft draft = iterator.next() ;
			String artTitle = draft.getArtTitle() ;
			String lastMod = draft.getLastMod() ;
			String draId = String.valueOf(draft.getDraId()) ;
			System.out.println(i+"-"+artTitle);
			//�ϲ���һ��String�У�ͨ��###�ָ�
			String content = artTitle+"###"+lastMod+"###"+ draId;
			map.put(String.valueOf(i), content) ;
			
			i++ ;
			
		}
			JSONObject jsonObject = JSONObject.fromObject(map) ;
			return jsonObject.toString() ;
		}
	/**
	 * ��dustbins��ΪJSON�ַ���
	 * @param list
	 * @return
	 */
	public static String dsutListToJsonString(List<Dustbin> list) {
		
		Map<String ,String> map = new HashMap<String, String>() ;
		Iterator<Dustbin> iterator = list.iterator() ;
		int i = 1 ;
		while (iterator.hasNext()) {
			Dustbin dustbin = iterator.next() ;
			String artTitle = dustbin.getArtTitle() ;
			String delTime = dustbin.getDelTime() ;
			String dustId = String.valueOf(dustbin.getDustId()) ;
			System.out.println(i+"-"+artTitle);
			//�ϲ���һ��String�У�ͨ��###�ָ�
			String content = artTitle+"###"+delTime+"###"+ dustId;
			map.put(String.valueOf(i), content) ;
			
			i++ ;
			
		}
			JSONObject jsonObject = JSONObject.fromObject(map) ;
			return jsonObject.toString() ;
		}
		
	/**
	 * ��resource��movie��ΪJSON�ַ���
	 *	mpName/resCom/pubTime/resId
	 * @param list
	 * @return
	 */
	public static String movieListToJsonString(List<Resource> list) {
		//��Ҫ����ͼ���ƣ�����ʱ�䣬resId
		Map<String ,String> map = new HashMap<String, String>() ;
		Iterator<Resource> iterator = list.iterator() ;
		int i = 1 ;
		while (iterator.hasNext()) {
			Resource resource = iterator.next() ;
			String mpName = resource.getMovie().getMediaPreview().getMpName() ;
			String resCom = resource.getResCom() ;
			String pubTime = resource.getPubTime() ;
			String resId = String.valueOf(resource.getResId()) ;
			System.out.println(i+"-"+mpName);
			//�ϲ���һ��String�У�ͨ��###�ָ�
			String content = mpName+"###"+resCom+"###"+ pubTime+"###"+resId;
			map.put(String.valueOf(i), content) ;
			
			i++ ;
			
		}
			JSONObject jsonObject = JSONObject.fromObject(map) ;
			return jsonObject.toString() ;
		}
	
	/**
	 * ��resource��picture��ΪJSON�ַ���
	 * picName/resCom/pubTime/resId 
	 * @param list
	 * @return
	 */
	public static String pictureListToJsonString(List<Resource> list) {
		
		Map<String ,String> map = new HashMap<String, String>() ;
		Iterator<Resource> iterator = list.iterator() ;
		int i = 1 ;
		while (iterator.hasNext()) {
			Resource resource = iterator.next() ;
			String picName = resource.getPicture().getPicName() ;
			String resCom  = resource.getResCom() ;
			String pubTime = resource.getPubTime() ;
			String resId = String.valueOf(resource.getResId()) ;
			System.out.println(i+"-"+picName);
			//�ϲ���һ��String�У�ͨ��###�ָ�
			String content = picName+"###"+resCom+"###"+ pubTime+"###"+resId;
			map.put(String.valueOf(i), content) ;
			
			i++ ;
			
		}
			JSONObject jsonObject = JSONObject.fromObject(map) ;
			return jsonObject.toString() ;
		}
	

	/**
	 * ��pc�б��picture��ΪJSON�ַ���
	 * picName/checkTag/checkCom/resId ;
	 * @param list
	 * @return
	 */
	public static String pcListToJsonString(List<PictureCheck> list) {
		
		Map<String ,String> map = new HashMap<String, String>() ;
		Iterator<PictureCheck> iterator = list.iterator() ;
		int i = 1 ;
		while (iterator.hasNext()) {
			PictureCheck pictureCheck = iterator.next() ;
			String picName = pictureCheck.getPicName() ;
			String checkTag = String.valueOf(pictureCheck.getCheckTag()) ;
			String checkCom = pictureCheck.getCheckCom() ;
			String picId = String.valueOf(pictureCheck.getPicId() ) ;
			System.out.println(i+"-"+picName);
			//�ϲ���һ��String�У�ͨ��###�ָ�
			String content = picName+"###"+checkTag+"###"+ checkCom+"###"+picId;
			map.put(String.valueOf(i), content) ;
			
			i++ ;
			
		}
			JSONObject jsonObject = JSONObject.fromObject(map) ;
			return jsonObject.toString() ;
		}
	/**
	 * ��mc�б��movie��ΪJSON�ַ���
	 * mpName/checkTag/checkCom/resId;
	 * @param list
	 * @return
	 */
	public static String mcListToJsonString(List<MovieCheck> list) {
		//��Ҫ����ͼ���ƣ�����ʱ�䣬resId
		Map<String ,String> map = new HashMap<String, String>() ;
		Iterator<MovieCheck> iterator = list.iterator() ;
		int i = 1 ;
		while (iterator.hasNext()) {
			MovieCheck movieCheck = iterator.next() ;
			String mpName = movieCheck.getMediaPreview().getMpName() ;
			String checkTag = String.valueOf(movieCheck.getCheckTag() ) ;
			String checkCom = movieCheck.getCheckCom() ;
			String movId = String.valueOf(movieCheck.getMovId()) ;
			System.out.println(i+"-"+mpName);
			//�ϲ���һ��String�У�ͨ��###�ָ�
			String content = mpName+"###"+checkTag+"###"+ checkCom+"###"+movId;
			map.put(String.valueOf(i), content) ;
			
			i++ ;
			
		}
			JSONObject jsonObject = JSONObject.fromObject(map) ;
			return jsonObject.toString() ;
		}
}
