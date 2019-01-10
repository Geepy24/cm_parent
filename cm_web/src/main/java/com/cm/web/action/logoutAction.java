package com.cm.web.action;
/**
 * 可与其他动作类合并
 */
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("struts-default")
@Namespace("/Logout")
@Results({
	@Result(name="success",location="/index.jsp")
})
public class logoutAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Action("logout")
	public String Logout() {
			
		ServletActionContext.getRequest().getSession().removeAttribute("loginInfo");
		return SUCCESS ;
	}
	
	
	
}
