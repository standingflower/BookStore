package com.booksystem.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class loginInterceptor extends AbstractInterceptor{

	
	private String result;
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(session.getAttribute("user")!=null){
			result=arg0.invoke();
			return result;
		}else{
			return "input";
		}
	}
}
