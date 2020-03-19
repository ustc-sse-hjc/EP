package com.ustc.group2.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ustc.group2.dao.UserMapper;
import com.ustc.group2.domain.User;
import com.ustc.group2.exception.*;

@Service
public class UserService {
	@Resource
	UserMapper usermapper;
	//登录
	public User login(String username)throws LoginException{
		User user=null;
		try{
			user=usermapper.getUserByUsername(username);			
		}catch(Exception e){
			throw new LoginException("用户名异常"+e.getMessage());
		}
		return user;
	}
	
}
