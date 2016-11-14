package org.haozf.basic.user.dao.impl;

import java.util.List;

import org.haozf.basic.dao.HibernateBaseDaoByHQL;
import org.haozf.basic.user.dao.IUserDao;
import org.haozf.basic.user.model.Resource;
import org.haozf.basic.user.model.Role;
import org.haozf.basic.user.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends HibernateBaseDaoByHQL<User> implements IUserDao {
	
	public List<User> listUser() {
		return super.listByHQL("from User");
	}
	
	public User loadByUsername(String username) {
		return (User)super.queryObjectByHQL("from User where username=?", username);
	}

	public List<User> listByRole(int id) {
		String hql = "select u from User u,Role r,UserRole ur where u.id=ur.userId and r.id=ur.roleId and r.id=?";
		return super.listObj(hql, id);
	}

	public List<Resource> listAllResource(int uid) {
		String hql = "select res from User u,Resource res,UserRole ur,RoleResource rr where " +
				"u.id=ur.userId and ur.roleId=rr.roleId  and rr.resId=res.id and u.id=?";
		return super.listObj(hql, uid);
	}

	@Override
	public List<Role> listRoleSnByUser(int uid) {
		String hql = "select r from UserRole ur,Role r,User u where u.id=ur.userId and r.id=ur.roleId and u.id=?";
		return super.listObj(hql, uid);
	}
	
	@Override
	public List<Role> listUserRole(int uid) {
		String hql = "select r from UserRole ur,Role r,User u where u.id=ur.userId and r.id=ur.roleId and u.id=?";
		return super.listObj(hql, uid);
	}

}
