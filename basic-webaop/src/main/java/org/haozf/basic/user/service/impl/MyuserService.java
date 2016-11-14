package org.haozf.basic.user.service.impl;

import java.util.List;

import javax.inject.Inject;

import net.zhxm.frame.base.dao.Page;

import org.haozf.basic.user.dao.IMyuserDao;
import org.haozf.basic.user.dao.IUserDao;
import org.haozf.basic.user.model.User;
import org.haozf.basic.user.service.IUserService;
import org.springframework.stereotype.Service;

@Service("myuserService")
public class MyuserService{
	
	@Inject
	private IMyuserDao myuserDao;
	
	@Inject
	private IUserDao userDao;
	
	@Inject
	private IMyuserDao myuserDaoExt;
	
	public List list(Page page){
		return myuserDao.listUser(page);
	}
	
	public User findUser(int id){
		return myuserDao.findUser(id);
//		return myuserDaoExt.findUser(id);
	}
	
	public void updateUser(User user){
		myuserDao.update(user);
//		myuserDaoExt.update(user);
//		throw new RuntimeException("修改错误");
	}
	
}
