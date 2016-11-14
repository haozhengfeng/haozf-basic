package org.haozf.basic.user.dao.impl;

import java.util.List;

import net.zhxm.frame.base.dao.BaseJdbcDao;
import net.zhxm.frame.base.dao.Finder;
import net.zhxm.frame.base.dao.Page;

import org.haozf.basic.user.dao.IMyuserDaoExt;
import org.haozf.basic.user.model.User;
import org.springframework.stereotype.Repository;
@Repository("myuserDaoExt")
public class MyuserDaoExt extends BaseJdbcDao implements IMyuserDaoExt {

	@Override
	public User findUser(int id) {
		Finder finder = new Finder();
		finder.append(" select * from t_user where id=?");
		finder.setParam(id);
		return queryForObject(finder, User.class);
	}


	@Override
	public List listUser(Page page) {
		Finder finder = new Finder();
		finder.append("select * from t_user");
		List<User> users = queryForList(finder,User.class);
		for(User user:users){
			System.out.println("springjdbc 主键："+user.getId()+"  姓名："+user.getUsername());
		}
		return users;
	}

}
