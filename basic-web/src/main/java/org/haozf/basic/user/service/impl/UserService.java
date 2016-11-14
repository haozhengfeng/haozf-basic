package org.haozf.basic.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.haozf.basic.shiro.kit.ShiroKit;
import org.haozf.basic.user.dao.IRoleDao;
import org.haozf.basic.user.dao.IUserDao;
import org.haozf.basic.user.model.Resource;
import org.haozf.basic.user.model.Role;
import org.haozf.basic.user.model.User;
import org.haozf.basic.user.service.IUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserService implements IUserService {
	@Inject
	private IUserDao userDao;
	@Inject
	private IRoleDao roleDao;
	@Inject
	private IUserDao userDaoByJDBC;

	public User add(User user) {
		if (ShiroKit.isEmpty(user.getUsername()) || ShiroKit.isEmpty(user.getPassword())) {
			throw new RuntimeException("用户名或者密码不能为空！");
		}
		user.setPassword(ShiroKit.md5(user.getPassword(), user.getUsername()));
		userDao.add(user);
		return user;
	}
	
	public void delete(int id) {
		userDao.delete(id);
	}

	public User update(User user, List<Integer> rids) {
		roleDao.deleteUserRoles(user.getId());
		for (int rid : rids) {
			roleDao.addUserRole(user.getId(), rid);
		}
		userDao.update(user);
		return user;
	}

	public User update(User user) {
		userDao.update(user);
		return user;
	}

	@Cacheable(value="usersCache")
	public User load(int id) {
		return userDao.load(id);
	}

	public User loadByUsername(String username) {
		return userDao.loadByUsername(username);
	}

	public User login(String username, String password) {
		User u = userDao.loadByUsername(username);
		if (u == null)
			throw new UnknownAccountException("用户名或者密码出错");
		if (!u.getPassword().equals(ShiroKit.md5(password, username)))
			throw new IncorrectCredentialsException("用户名或者密码出错");
		if (u.getStatus() == 0)
			throw new LockedAccountException("用户已经被锁定");
		return u;
	}

	public List<User> list() {
		return userDao.listUser();
	}

	public List<User> listByRole(int id) {
		return userDao.listByRole(id);
	}

	public List<Resource> listAllResource(int uid) {
		return userDao.listAllResource(uid);
	}

	public User add(User user, List<Integer> rids) {
		this.add(user);
		for (int rid : rids) {
			roleDao.addUserRole(user.getId(), rid);
		}
		return user;
	}

	@Override
	public List<String> listRoleSnByUser(int uid) {
		List<Role> roles = userDao.listRoleSnByUser(uid);
		List<String> r = new ArrayList<String>();
		for (Role role : roles) {
			r.add(role.getSn());
		}
		return r;
	}

	@Override
	public List<Role> listUserRole(int uid) {
		return userDao.listUserRole(uid);
	}

}
