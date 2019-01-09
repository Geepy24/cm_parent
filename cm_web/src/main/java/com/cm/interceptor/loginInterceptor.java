package com.cm.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 	��½��������ֻ�е�¼��֮����ܲ鿴����
 * @author Huangjiping
 *
 */
public class loginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//������session����
		Object object = ServletActionContext.getRequest().getSession().getAttribute("loginInfo") ;
		
		if(object == null) {
			return "login" ;//û��¼��ȥ��¼
		}
		System.out.println("��ǰ�û���"+object.toString());
		
		return invocation.invoke() ;  //�ѵ�¼������
	}

}
