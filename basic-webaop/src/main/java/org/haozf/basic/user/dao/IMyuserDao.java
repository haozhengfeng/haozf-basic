package org.haozf.basic.user.dao;

import java.util.List;

import net.zhxm.frame.base.dao.Page;

import org.haozf.basic.dao.IBaseDao;
import org.haozf.basic.user.model.User;

public interface IMyuserDao extends IBaseDao<User>{
	public List listUser(Page page);
	public User findUser(int id);
}
