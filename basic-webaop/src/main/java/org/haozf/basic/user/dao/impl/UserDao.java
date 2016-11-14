package org.haozf.basic.user.dao.impl;

import java.util.List;

import org.haozf.basic.dao.HibernateBaseDaoByHQL;
import org.haozf.basic.dao.HibernateBaseDaoBySQL;
import org.haozf.basic.model.Pager;
import org.haozf.basic.user.dao.IUserDao;
import org.haozf.basic.user.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends HibernateBaseDaoBySQL<User> implements IUserDao {

	@Override
	public List<User> list() {
		return listBySql(" select * from t_user ", User.class, true);
	}

	@Override
	public Pager<User> find() {
		return findBySql(" select * from t_user ", User.class, true);
	}

}
