package org.haozf.basic.mybatis.test;

import java.util.List;

import org.haozf.basic.events.ContentEvent;
import org.haozf.basic.mybatis.model.Article;
import org.haozf.basic.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class SpringTest {

	@Autowired
	UserService userService;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testPublishEvent() {
		applicationContext.publishEvent(new ContentEvent("今年是龙年的博客更新了"));
	}

	@Test
	public void testcache() {
		List<Article> list = userService.getUserArticles(1);
	}

}
