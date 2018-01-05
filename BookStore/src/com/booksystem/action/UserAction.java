 package com.booksystem.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.booksystem.mybatis.entities.User;
import com.booksystem.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Scope("prototype")
@Controller
public class UserAction extends ActionSupport implements RequestAware,SessionAware{
	
	private User user;
	
	private Map<String,Object> request;
	
	private Map<String,Object> session;
	
	@Autowired
	private UserService userService;
	
	//返回操作结果
	private String res;
	
	
	
	public String login(){
		System.out.println("slogin");
		System.out.println("login"+user);
		User newUser = userService.login(user);
		System.out.println(newUser);
		if(newUser!=null){
			if(user.getPassword().equals(newUser.getPassword())&&newUser.getUserRole()==user.getUserRole()){
				//登录成功
				session.put("user", newUser);
				//根据用户的不同，返回不同的页面
				if(user.getUserRole()==0){
					//管理员
					System.out.println("elogin");
					return "admin";
				}
				if(user.getUserRole()==1){
					System.out.println("elogin");
					return "userdetails";
				}
			}
		}else{
			res="借阅号或密码错误！";
			System.out.println("出错退出");
			//登录失败，返回错误信息
			System.out.println("elogin");
			return INPUT;
		}
		System.out.println("正常退出");
		System.out.println("elogin");
		return INPUT;
	}
	
	
	public String toManage(){
		
		User user=(User)session.get("user");
		
		if(user!=null){
			return "admin";
		}else{
			return INPUT;
		}
		
	}
	
	
	
	//挂失操作
	public String lose(){
		System.out.println("slose");
		//从session中获取user
		user=(User) session.get("user");
		System.out.println("user:"+user);
		//判断用状态
		//当前状态为可借，准备挂失操作
		if(user.getIflend()==0){
			userService.ifLend(user.getUserId());
			user.setIflend(1);
			session.put("user", user);
		}
		System.out.println("elose");
		return "userdetails";
	}
	
	
	//解挂操作
	public String unlose(){
		System.out.println("sunlose");
		String userId=user.getUserId();
		//判断当前操作用户
		user=(User) session.get("user");
		if(user.getUserRole()==0){
			System.out.println(user);
			if(userService.ifLend(userId)>0){
				res="1";
			}else{
				res="0";
			}
		}else{
			res="0";
		}
		System.out.println("eunlose");
		return "resjson";
	}
	
	
	
	
	


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request=arg0;
	}


	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}
	
	
	
	
	
	
}
