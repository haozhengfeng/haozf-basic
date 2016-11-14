package org.haozf.basic.user.dao;

import java.util.List;

import org.haozf.basic.dao.HibernateBaseDaoByHQL;
import org.haozf.basic.user.model.User;
import org.springframework.stereotype.Repository;

import com.base.dao.Page;

@Repository("myuserDao")
public class MyuserDao extends HibernateBaseDaoByHQL<User> implements IMyuserDao {

	@Override
	public void findUser(int id) {
		User user = super.load(id);
		System.out.println("id:" + user.getId() + "  " + user.getUsername());
	}

	@Override
	public void listUser(Page page) {
		List<User> users = getSession()
				.createSQLQuery(" select * from t_user ").addEntity(User.class)
				.setFirstResult(page.getPageIndex()).setMaxResults(page.getPageSize()).list();
		for(User user:users){
			System.out.println("hibernate 主键："+user.getId()+"  姓名："+user.getUsername());
		}
	}
}
