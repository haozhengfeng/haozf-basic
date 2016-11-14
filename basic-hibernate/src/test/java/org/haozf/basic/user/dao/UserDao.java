package org.haozf.basic.user.dao;

import org.haozf.basic.dao.HibernateBaseDaoByHQL;
import org.haozf.basic.user.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends HibernateBaseDaoByHQL<User> implements IUserDao {

}
