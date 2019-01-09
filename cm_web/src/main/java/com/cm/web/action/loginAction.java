package com.cm.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.User;
import com.cm.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * ��֤��¼
 * @author Huangjiping
 *
 */
@ParentPackage("json-default")
@Namespace("/Login")
@Results({
	@Result(name="login",location="/jsp/login.jsp"),
	@Result(name="fail",location="/fail.jsp")
})

public class loginAction extends ActionSupport {
	@Autowired
	private IUserService userService ;
//������������ģ��
	private String userName ;
	private String password ;
	private String returndata ; 

	private HttpServletRequest request = ServletActionContext.getRequest() ;

	public String getReturndata() {
		return returndata;
	}

	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}

	HttpSession session = ServletActionContext.getRequest().getSession() ;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 	�����û����ж��û��Ƿ���ڣ��½�һ���û������Ƿ�����
	 */
	@Action(value="verifyUsername",results= {@Result(name="success",type="json")},
			params= {"root","returndata"})
	public String verifyUsername() {
		System.out.println("������");
		System.out.println("ǰ�˴���"+userName);
		//�õ����true��false
		returndata = String.valueOf(userService.isUserExist(userName)) ;
		//ƴ�ӳ�json�ַ���������
		returndata = "{\"backdata\" : \""+returndata+"\"}"   ;
		System.out.println("���ص�json�ַ�����"+returndata);
	
		return SUCCESS ;
	}
	
	
	/**
	 * ��֤��¼
	 * @return
	 */
	@Action(value="verifyLogin",results= {
			@Result(name="success",location="/index.jsp"),
			@Result(name="picture",type="redirectAction",location="indexpic",params= {"namespace","/Resource"}),
			@Result(name="manager",type="redirectAction",location="management",params= {"namespace","/privateSource"})
			//��chain����һ��action��ˢ��ҳ��ᵼ���ظ��ύ������redirectAction����
	})
	public  String Login() {
		
			User user = userService.findUserByName(userName) ;
			
			if(!user.getPassword().equals(password)) {
				System.out.println(user.getPassword());
				session.setAttribute("loginFail", "�������");
				return "fail" ;
				
			}
			
			//��¼�ɹ����ѵ�¼��ǡ�loginInfo���浽session����
			session.setAttribute("loginInfo", user);
			
			//ȡ���û���ԴUrl��ʵ�ִ�������ת����ȥ
		String ref = request.getSession().getAttribute("login-ref").toString() ;
		System.out.println("ҳ����Դ��"+ref);
		
		if(-1 != ref.indexOf("indexpic")) {
			return "picture" ;
		}
		if(-1 != ref.indexOf("management")) {
			return "manager" ;
		}
		
		
			return SUCCESS ;
		
		

	}
	
	
	
}
