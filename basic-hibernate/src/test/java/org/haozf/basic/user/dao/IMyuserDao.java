package org.haozf.basic.user.dao;

import com.base.dao.Page;


public interface IMyuserDao {
	public void listUser(Page page);
	public void findUser(int id);
}
