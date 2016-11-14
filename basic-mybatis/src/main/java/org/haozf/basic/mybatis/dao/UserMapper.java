package org.haozf.basic.mybatis.dao;

import java.util.List;

import org.haozf.basic.mybatis.model.Article;
import org.haozf.basic.mybatis.model.User;

public interface UserMapper {
	 public User selectUserByID(int id);
	 
	 public List<Article> getUserArticles(int id);
}
