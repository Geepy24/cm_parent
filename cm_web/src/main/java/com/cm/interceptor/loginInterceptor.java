package com.cm.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 登陆拦截器，只有登录了之后才能查看内容
 * @author Huangjiping
 *
 */
public class loginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//用户数据在session域中
		System.err.println("进入拦截器");
		
		Object object = ServletActionContext.getRequest().getSession().getAttribute("loginInfo") ;
		System.out.println(object);
		if(object == null) {
			System.err.println("用户未登陆");
			return "login" ;//没登录，去登录
		}
		System.err.println("当前用户"+object.toString());
		
		return invocation.invoke() ;  //已登录，放行
	}

}
