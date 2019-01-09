package com.cm.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 	�˶����ฺ����ת
 * 	
 * @author Huangjiping
 *
 */
@Controller("jumpAction")
@ParentPackage("struts-default")
@Namespace("/User")
@Results({
		@Result(name="login",location="/jsp/login.jsp"),
		@Result(name="register",location="/jsp/register.jsp")

})
public class JumpAction extends ActionSupport  {
	
	 
	
	@Action("login")
	public String login() {
		//�Ӳ�ͬ�ط�������û�����½��Ҫȥ����ͬ�ط�����Ҫ����û���Դҳ���url����ű�ǵ�session
		HttpServletRequest request = ServletActionContext.getRequest() ; 
		System.out.println(request.getRequestURL());
		
		//����ԴURI����session�У��ȵ�½�ɹ���Ϳ���ȡ������ת����Ӧ��action
		request.getSession().setAttribute("login-ref", request.getRequestURL());
		
		
		
		return "login" ; 
			
	}
	@Action("register")
	public String register() {
		return "register" ;
	}
	
}
