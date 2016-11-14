package org.haozf.basic.user.service;

import java.util.List;

import org.haozf.basic.model.Pager;
import org.haozf.basic.user.model.User;

public interface IUserService<T> {

	User getUser(int id);

	T addUser(T t);

	void deleteUser(int id);
	
	T updateUser(T t);

	List<T> list();

	Pager<T> find();

}
