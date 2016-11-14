package org.haozf.basic.user.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.haozf.basic.model.Pager;
import org.haozf.basic.user.dao.IUser2Dao;
import org.haozf.basic.user.model.User;
import org.haozf.basic.user.model.User2;
import org.haozf.basic.user.service.IUserService;
import org.springframework.stereotype.Service;

@Service("user2Service")
public class User2Service implements IUserService<User2> {

    @Resource
    private IUser2Dao user2Dao;

    @Override
    public User getUser(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User2 addUser(User2 t) {
    	User2 u = user2Dao.add(t);
        return u;
    }

    @Override
    public void deleteUser(int id) {
        // TODO Auto-generated method stub

    }
    
    @Override
	public User2 updateUser(User2 t) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public List<User2> list() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pager<User2> find() {
        return user2Dao.find();
    }

}
