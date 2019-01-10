package com.cm.web.action;

import java.io.Serializable;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
@ParentPackage("json-default")
@Namespace("/User")
@Results({
	@Result(name="success",location="/jsp/login.jsp")
})
public class userRegister extends ActionSupport implements Serializable ,ModelDriven<User>{
	
	private static final long serialVersionUID = 1L;
	private User user  ;
	@Autowired
	//返回的json数据
	private JSONObject returndata ; 
	
	@Override
	public User getModel() {
		return user;
	}
	
//----------getter and setter-------------------------

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



//--------------Action------------------------------------


	@Action(value="registerAction",results= {@Result(name="success",type="json")},
				params={"root","returndata"})
	public String register() {
		System.out.println("注册接收请求");
		System.out.println("前端传回："+user);
		//保存正常
		
		//传回json对象
		String str = "{\"returndata\" : \"registerSuccess\"}" ;
		returndata = JSONObject.fromObject(str) ;
		return SUCCESS ;
	}

}
