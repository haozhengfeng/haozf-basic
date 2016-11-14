package org.haozf.basic.user.dao;

import java.util.List;

import net.zhxm.frame.base.dao.Page;

import org.haozf.basic.user.model.User;

public interface IMyuserDaoExt {
	public List listUser(Page page);
	public User findUser(int id);
}
