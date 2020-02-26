package com.ustc.group2.dao;

import com.ustc.group2.domain.User;

public interface UserMapper {
	//增删改查   CRUD
	public int saveUser(User user);
	public User getUserByUsername(String username);
}
