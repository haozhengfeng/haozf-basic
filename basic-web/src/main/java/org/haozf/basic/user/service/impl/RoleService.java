package org.haozf.basic.user.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.haozf.basic.user.dao.IRoleDao;
import org.haozf.basic.user.model.Resource;
import org.haozf.basic.user.model.Role;
import org.haozf.basic.user.model.RoleResource;
import org.haozf.basic.user.model.UserRole;
import org.haozf.basic.user.service.IRoleService;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService implements IRoleService {
	@Inject
	private IRoleDao roleDao;
	
	public void add(Role role) {
		roleDao.add(role);
	}

	public void delete(int id) {
		roleDao.delete(id);
	}

	public Role load(int id) {
		return roleDao.load(id);
	}

	public List<Role> list() {
		return roleDao.listRole();
	}

	public void update(Role role) {
		roleDao.update(role);
	}

	public List<Role> listRole() {
		return roleDao.listRole();
	}


	public UserRole loadUserRole(int uid, int roleId) {
		return roleDao.loadUserRole(uid, roleId);
	}

	public void addUserRole(int uid, int roleId) {
		roleDao.addUserRole(uid, roleId);
	}

	public void deleteUserRole(int uid, int roleId) {
		roleDao.deleteUserRole(uid, roleId);
	}

	public void deleteUserRoles(int uid) {
		roleDao.deleteUserRoles(uid);
	}

	public List<Resource> listRoleResource(int roleId) {
		return roleDao.listRoleResource(roleId);
	}

	public void addRoleResource(int roleId, int resId) {
		roleDao.addRoleResource(roleId, resId);
	}

	public void deleteRoleResource(int roleId, int resId) {
		roleDao.deleteRoleResource(roleId, resId);
	}

	public RoleResource loadResourceRole(int roleId, int resId) {
		return roleDao.loadResourceRole(roleId, resId);
	}

}
