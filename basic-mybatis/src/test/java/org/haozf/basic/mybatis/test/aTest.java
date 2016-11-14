package org.haozf.basic.mybatis.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.haozf.basic.mybatis.dao.UserMapper;
import org.haozf.basic.mybatis.model.Article;
import org.haozf.basic.mybatis.model.User;
import org.junit.Test;

public class aTest {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static{
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInter(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	UserMapper userOperation=session.getMapper(UserMapper.class);
            User user = userOperation.selectUserByID(1);
            System.out.println(user.getUserAddress());
            System.out.println(user.getUserName());
        } finally {
            session.close();
        }
    }
	
	@Test
	public void getUserArticles(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	UserMapper userOperation=session.getMapper(UserMapper.class);           
            List<Article> articles = userOperation.getUserArticles(1);
            for(Article article:articles){
                System.out.println(article.getTitle()+":"+article.getContent()+
                        ":作者是:"+article.getUser().getUserName()+":地址:"+
                         article.getUser().getUserAddress());
            }
        } finally {
            session.close();
        }
    }

}
