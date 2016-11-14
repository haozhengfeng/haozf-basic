package org.haozf.basic.user.dao.impl;

import org.haozf.basic.dao.HibernateBaseDaoBySQL;
import org.haozf.basic.model.Pager;
import org.haozf.basic.user.dao.IUser2Dao;
import org.haozf.basic.user.model.User2;
import org.springframework.stereotype.Repository;

@Repository("user2Dao")
public class User2Dao extends HibernateBaseDaoBySQL<User2> implements IUser2Dao {

    @Override
    public Pager<User2> find() {
        return findBySql(" select * from t_user ", User2.class, false);
    }

}
