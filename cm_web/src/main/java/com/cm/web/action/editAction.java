package com.cm.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cm.domain.User;
import com.cm.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("struts-default")
@Namespace("/User")
@Results({
	@Result(name="edit1",location="/fail.jsp"),
	@Result(name="success",type="chain",location="findAll"), //��ת��findAll.action
	@Result(name="error",location="/fail.jsp")
})
/**
 * �޸��û���Ϣ
 * @author Huangjiping
 *
 */
public class editAction extends ActionSupport implements ModelDriven<User>{
	//ģ������
	
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
		System.out.println("ģ������������"+user);
		user = userService.findUserById(user.getUserId());
		//��Ҫ�޸ĵ��û�����Ž�request��
		request.setAttribute("editUser", user); 
		return "edit1" ;
		
	}
	/**
	 * �޸��û���ʵ��
	 * @return
	 */
	@Action("editUser")
	public String edit(){
		System.out.println("�޸���֮�������"+user);
	//	user = userService.findUserById(user.getUserId()) ;
		try {
			//��ģ���������������޸ĺ��user���������ݿ��е�user
			userService.updateUser(user) ;
			
		}catch(Exception e){
			e.printStackTrace();
			return "error" ;
		}
		
		return "success" ;
	}
	
	/**
	 * 	ɾ���û���ʵ��
	 */
	@Action("delete")
	public String deleteUser() {
		System.out.println("��������Ҫɾ�����û�ID"+user.getUserId());
		user = userService.findUserById(user.getUserId()) ;
		//����ֻͨ��userIdɾ���û���Ҫ��ʵ�塣����û�����������������������������ԣ�
		//userService.deleteById(user.getUserId()) ;
		userService.deleteUser(user);
		
		
		
		return "success" ;
	}
	
	
	
}
