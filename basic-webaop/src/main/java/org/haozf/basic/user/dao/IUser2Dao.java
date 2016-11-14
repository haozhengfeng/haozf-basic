package org.haozf.basic.user.dao;

import org.haozf.basic.dao.IBaseDao;
import org.haozf.basic.model.Pager;
import org.haozf.basic.user.model.User2;

public interface IUser2Dao extends IBaseDao<User2> {
    Pager<User2> find();
}
