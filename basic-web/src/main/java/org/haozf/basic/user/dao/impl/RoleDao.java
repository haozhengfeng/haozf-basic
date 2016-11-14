package org.haozf.basic.user.dao.impl;

import java.util.List;

import org.haozf.basic.dao.HibernateBaseDaoByHQL;
import org.haozf.basic.user.dao.IRoleDao;
import org.haozf.basic.user.model.Resource;
import org.haozf.basic.user.model.Role;
import org.haozf.basic.user.model.RoleResource;
import org.haozf.basic.user.model.UserRole;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDao extends HibernateBaseDaoByHQL<Role> implements IRoleDao {

	// public List<Role> listRole() {
	// String sql = "select * from t_role";
	// return super.listBySql(sql, Role.class, true);
	// }
	//
	// public UserRole loadUserRole(int uid, int roleId) {
	// String sql =
	// "select * from t_user_role ur where ur.user_Id=? and ur.role_Id=?";
	// return (UserRole) this.getSession().createSQLQuery(sql).setParameter(0,
	// uid).setParameter(1, roleId).uniqueResult();
	// }
	//
	// public void addUserRole(int uid, int roleId) {
	// UserRole ur = null;
	// ur = loadUserRole(uid, roleId);
	// if (ur == null) {
	// ur = new UserRole();
	// ur.setRoleId(roleId);
	// ur.setUserId(uid);
	// this.getSession().save(ur);
	// }
	// }
	//
	// public void deleteUserRole(int uid, int roleId) {
	// UserRole ur = null;
	// ur = loadUserRole(uid, roleId);
	// if (ur != null) {
	// this.getSession().delete(ur);
	// }
	// }
	//
	// public void deleteUserRoles(int uid) {
	// String sql = "delete from t_user_role where user_Id=?";
	// super.updateBySql(sql, uid);
	// }
	//
	// public List<Resource> listRoleResource(int roleId) {
	// String sql =
	// "select res.* from t_role role,t_resource res,t_role_res rr where " +
	// "role.id=rr.role_Id and res.id=rr.res_Id and role.id=?";
	// return listBySql(sql, roleId, Resource.class, true);
	// }
	//
	// public void addRoleResource(int roleId, int resId) {
	// RoleResource rr = null;
	// rr = loadResourceRole(roleId, resId);
	// if (rr == null) {
	// rr = new RoleResource();
	// rr.setResId(resId);
	// rr.setRoleId(roleId);
	// this.getSession().save(rr);
	// }
	// }
	//
	// public void deleteRoleResource(int roleId, int resId) {
	// RoleResource rr = null;
	// rr = loadResourceRole(roleId, resId);
	// if (rr != null) {
	// this.getSession().delete(rr);
	// }
	// }
	//
	// public RoleResource loadResourceRole(int roleId, int resId) {
	// String sql =
	// "select rr.* from t_role_res rr where rr.role_Id=? and rr.res_Id=?";
	// return (RoleResource)
	// this.getSession().createSQLQuery(sql).setParameter(0,
	// roleId).setParameter(1, resId).uniqueResult();
	// }

	public List<Role> listRole() {
		return super.listByHQL("from Role");
	}

	public UserRole loadUserRole(int uid, int roleId) {
		String hql = "select ur from UserRole ur where ur.userId=? and ur.roleId=?";
		Object param[] = new Object[] { uid, roleId };
		return (UserRole) super.queryObjectByHQL(hql, param);
	}

	public void addUserRole(int uid, int roleId) {
		UserRole ur = null;
		ur = loadUserRole(uid, roleId);
		if (ur == null) {
			ur = new UserRole();
			ur.setRoleId(roleId);
			ur.setUserId(uid);
			this.getSession().save(ur);
		}
	}

	public void deleteUserRole(int uid, int roleId) {
		UserRole ur = null;
		ur = loadUserRole(uid, roleId);
		if (ur != null) {
			this.getSession().delete(ur);
		}
	}

	public void deleteUserRoles(int uid) {
		String hql = "delete UserRole ur where ur.userId=?";
		super.updateByHQL(hql, uid);
	}

	public List<Resource> listRoleResource(int roleId) {
		String hql = "select res from Role role,Resource res,RoleResource rr where " + "role.id=rr.roleId and res.id=rr.resId and role.id=?";

		return super.listObj(hql, roleId);
	}

	public void addRoleResource(int roleId, int resId) {
		RoleResource rr = null;
		rr = loadResourceRole(roleId, resId);
		if (rr == null) {
			rr = new RoleResource();
			rr.setResId(resId);
			rr.setRoleId(roleId);
			this.getSession().save(rr);
		}
	}

	public void deleteRoleResource(int roleId, int resId) {
		RoleResource rr = null;
		rr = loadResourceRole(roleId, resId);
		if (rr != null) {
			this.getSession().delete(rr);
		}
	}

	public RoleResource loadResourceRole(int roleId, int resId) {
		String hql = "select rr from RoleResource rr where rr.roleId=? and rr.resId=?";
		Object param[] = new Object[] { roleId, resId };
		return (RoleResource) super.queryObjectByHQL(hql, param);
	}
}
