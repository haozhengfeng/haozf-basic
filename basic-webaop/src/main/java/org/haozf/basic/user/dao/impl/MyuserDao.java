package org.haozf.basic.user.dao.impl;

import java.util.List;

import net.zhxm.frame.base.dao.Page;

import org.haozf.basic.dao.HibernateBaseDaoByHQL;
import org.haozf.basic.user.dao.IMyuserDao;
import org.haozf.basic.user.model.User;
import org.springframework.stereotype.Repository;

@Repository("myuserDao")
public class MyuserDao extends HibernateBaseDaoByHQL<User> implements IMyuserDao {

	@Override
	public User findUser(int id) {
		User user = super.load(id);
		System.out.println("id:" + user.getId() + "  " + user.getUsername());
		return user;
	}

	@Override
	public List listUser(Page page) {
		List<User> users = getSession()
				.createSQLQuery(" select * from t_user ")
				.addEntity(User.class)
				.setFirstResult(page.getPageIndex()-1)
				.setMaxResults(page.getPageSize())
				.list();
		for(User user:users){
			System.out.println("hibernate 主键："+user.getId()+"  姓名："+user.getUsername());
		}
		
		return users;
	}
}
