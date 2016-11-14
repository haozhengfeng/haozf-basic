package org.haozf.basic.user.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haozf.basic.dao.HibernateBaseDaoByHQL;
import org.haozf.basic.user.dao.IUserDao;
import org.haozf.basic.user.model.Resource;
import org.haozf.basic.user.model.Role;
import org.haozf.basic.user.model.User;
import org.springframework.stereotype.Repository;

import com.base.dao.Finder;

import common.base.AbstractJdbcDao;



@Repository("userDaoByJDBC")
public class UserDaoByJDBC extends AbstractJdbcDao<User> implements IUserDao {
	
	public List<Map<String, Object>> list(){
	    return getJdbcTemplate().queryForList("select id,username,password,nickname,status from t_user", new HashMap<String,Object>());
	}

    @Override
    public User add(User t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(User t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public User load(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> listUser() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User loadByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> listByRole(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Resource> listAllResource(int uid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Role> listRoleSnByUser(int uid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Role> listUserRole(int uid) {
        // TODO Auto-generated method stub
        return null;
    }


}
