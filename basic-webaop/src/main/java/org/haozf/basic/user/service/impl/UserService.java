package org.haozf.basic.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.haozf.basic.model.Pager;
import org.haozf.basic.user.dao.IUserDao;
import org.haozf.basic.user.model.User;
import org.haozf.basic.user.service.IUserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserService implements IUserService<User> {

	@Resource
	private IUserDao userDao;

	@Override
	@Cacheable(value = "usersCache")
	public User getUser(int id) {
		userDao.list();
		return userDao.load(id);
	}

	@Override
	@CachePut(value = "usersCache", key = "#user.getId()")
	public User addUser(User user) {
		User u = userDao.add(user);
		return u;
	}

	@Override
	@CacheEvict(value = "usersCache", key = "#id")
	public void deleteUser(int id) {
		User user = new User();
		user.setId(id);
		userDao.delete(id);
	}

	@Override
	@CachePut(value = "usersCache", key = "#user.getId()")
	public User updateUser(User user) {
		userDao.update(user);
		
//		if(1==1){
//			throw new RuntimeException("123");
//		}
		
		return user;
	}

	@Override
	public List<User> list() {
		return userDao.list();
	}

	@Override
	public Pager<User> find() {
		return userDao.find();
	}

}
