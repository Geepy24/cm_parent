package com.cm.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cm.domain.User;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * ����JSON����
 * @author Huangjiping
 *
 */

@ParentPackage("json-default")
@Results({
	//@Result(name="success",type="json",params= {"root","returndata"})
})
public class getJSON extends ActionSupport {
	private User user ;
	private JSONObject returndata ;
	
	public JSONObject getReturndata() {
		return returndata;
	}
	public void setReturndata(JSONObject returndata) {
		this.returndata = returndata;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

//	@Action("getJSON")
//	public String execute() {
//		//��ôʶ��ǰ�˵�jsonString
//		System.out.println(jsonString);
//		//����JSONObject����,���´���û������
//		JSONObject json = JSONObject.fromObject(jsonString) ;
//		System.out.println(json);
//		System.out.println(json.get("username"));
//		System.out.println(json.get("password"));
//		System.out.println(json.get("age"));
//		System.out.println(json.get("BigText"));
//		
//		return null ;
//	}
	
	
	@Action(value="jqueryJson",results= {@Result(name="success",type="json")},
			params= {"root","returndata"})
	public String executeJson() {
		//�Զ�����bean��
		System.out.println(user);
		System.out.println("ǰ�˴���name:"+user.getRealName());
		//��װ��json��ʽ
		Map<String, String> map = new HashMap<>() ;
		map.put("backusername",user.getUserName()) ;
		System.out.println("��װ��map�гɹ�"+map.get("backusername"));
		
	//���ɹ�	returndata = "{\"backusername\":\""+map.get("backusername")+"\"}";
		
		//��mapת��json����
		JSONObject json = JSONObject.fromObject(map) ;
		System.out.println("json����"+json);
		//���ñ���ַ������أ��������з����������
		returndata  = json ;
		System.out.println("Ҫ���ص�JSONObject����"+returndata);
		return SUCCESS ;
	}
	
}
