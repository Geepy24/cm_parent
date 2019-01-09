package com.cm.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.engine.jdbc.spi.ResultSetReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletConfigAware;

import com.cm.domain.User;
import com.cm.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 	һЩ˽����Դ��Ҫ��¼��֮����ܷ���
 * 	��������������������ж��Ƿ��е�¼����½�˾ͷ��У�δ��¼ȥ��¼
 * 	�ѵ�¼���ܽ���
 * 	��¼���鿴
 * @author Huangjiping
 *
 */
@ParentPackage("p1")
@Namespace("/privateSource")
@Results({
	@Result(name="manager",location="/management/index.jsp"),
	@Result(name="login",type="chain",location="login",params= {"namespace","/User"}),
	@Result(name="AuthorityError",location="/index.jsp"),
	@Result(name="success",location="/WEB-INF/jsp/userPage.jsp")
})
@InterceptorRefs({@InterceptorRef("loginDefault")})
public class privateSourceAction extends ActionSupport {

	@Autowired
	private IUserService userService ;
	
	/**
	 * 	��¼����ܲ鿴����ҳ�棬����ȥ��¼
	 * 	��¼���鿴session�е�loginInfo
	 * @return
	 */
	
	@Action("management")
	public String management() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("loginInfo") ;
		int userId = user.getUserId() ;
		//��ѯ�ǲ��ǹ���Ա
		boolean isAds = userService.isAds(userId) ;
		System.out.println("isAds="+isAds);
		if(false == isAds) {
			ServletActionContext.getRequest().setAttribute("AuthorityError","��ǰ�û��޷���Ȩ��");
			return "AuthorityError" ;
		}
		
		//�ǹ���Ա����ӹ���Ա���manager
		ServletActionContext.getRequest().getSession().setAttribute("manager", 1);
		return "manager"; 
	}
	
	@Action("userPage")
	public String toUserPage() {
		return SUCCESS ;
	}
	
	
	
}
