package org.haozf.basic.mybatis.service;

import java.util.List;

import org.haozf.basic.mybatis.dao.UserMapper;
import org.haozf.basic.mybatis.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Cacheable("userCache")
	public List<Article> getUserArticles(int id){
		return userMapper.getUserArticles(id);
	}
	
	@CacheEvict(value="userCache", allEntries=true)
	public List<Article> getUserArticlesNocache(int id){
		return userMapper.getUserArticles(id);
	}

}
