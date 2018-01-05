package com.booksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booksystem.mybatis.entities.User;
import com.booksystem.mybatis.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	private User user;
	
	//修改借阅权限(解挂/挂失)
	public int ifLend(String userId){
		
		//获取到user
		user = userMapper.selectByPrimaryKey(userId);
		//更改借阅状态
		user.setIflend((user.getIflend()+1)%2);
		//修改借阅状态
		int updateUser = userMapper.updateByPrimaryKey(user);
		return updateUser;
	}
	
	
	
	
	//登录
	public User login(User user){
		user = userMapper.selectByPrimaryKey(user.getUserId());
		return user;
		
		
//		if(user.getPassword().equals(user.getPassword())){
//			//返回0(true)：表示登录成功
//			return 0;
//		}
//		//返回1(false):表示登录失败
//		return 1;
	}
	
	
	//获取学生
	public User getUser(String userId){
		
		user=userMapper.selectByPrimaryKey(userId);
		
		return user;
	}
	
	
	
	
	
	
}
