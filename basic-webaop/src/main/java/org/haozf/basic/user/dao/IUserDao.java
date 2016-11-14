package org.haozf.basic.user.dao;

import java.util.List;

import org.haozf.basic.dao.IBaseDao;
import org.haozf.basic.model.Pager;
import org.haozf.basic.user.model.User;

public interface IUserDao extends IBaseDao<User> {
	List<User> list();
	Pager<User> find();
}
