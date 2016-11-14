package org.haozf.basic.user.dao;

import java.util.List;

import org.haozf.basic.dao.IBaseDao;
import org.haozf.basic.user.model.Resource;
import org.haozf.basic.user.model.Role;
import org.haozf.basic.user.model.User;


public interface IUserDao extends IBaseDao<User> {
	public List<User> listUser();
	
	public User loadByUsername(String username);
	
	public List<User> listByRole(int id);
	
	public List<Resource> listAllResource(int uid);
	
	public List<Role> listRoleSnByUser(int uid);
	
	public List<Role> listUserRole(int uid);
	
}
