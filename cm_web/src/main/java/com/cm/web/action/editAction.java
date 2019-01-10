package com.cm.web.action;
/**
 * 可与其他user相关的动作类合并
 */
import javax.servlet.http.HttpServletRequest;

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
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("struts-default")
@Namespace("/User")
@Results({
	@Result(name="edit1",location="/fail.jsp"),
	@Result(name="success",type="chain",location="findAll"), 
	@Result(name="error",location="/fail.jsp")
})
/**
 * 修改用户信息
 * @author Huangjiping
 *
 */
public class editAction extends ActionSupport implements ModelDriven<User>{
	
	
	private static final long serialVersionUID = 1L;

	private User user = new User();
	
	@Autowired
	IUserService userService ;

	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	@Override
	public User getModel() {
		return user;
	}
	
	
	@Action("edit")
	public String jumpEdit() {
		System.out.println("要修改的用户："+user);
		user = userService.findUserById(user.getUserId());
		//把要修改的用户对象放进request域
		request.setAttribute("editUser", user); 
		return "edit1" ;
		
	}
	/**
	 * 修改用户的实现
	 * @return
	 */
	@Action("editUser")
	public String edit(){
		System.out.println("修改完之后进来"+user);
		try {
			//用模型驱动传进来的修改后的user来更新数据库中的user
			userService.updateUser(user) ;
			
		}catch(Exception e){
			e.printStackTrace();
			return "error" ;
		}
		
		return "success" ;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@Action("delete")
	public String deleteUser() {
		System.out.println("传进来的要删除的用户ID"+user.getUserId());
		user = userService.findUserById(user.getUserId()) ;
		userService.deleteUser(user);
		
		return "success" ;
	}
	
	
	
}
