package org.haozf.basic.events;

import java.util.List;

import org.haozf.basic.mybatis.model.Article;
import org.haozf.basic.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component  
public class ZhangsanListener implements ApplicationListener<ContentEvent> {  
    
	@Autowired
	UserService userService;
	
	@Override  
    public void onApplicationEvent(final ContentEvent event) {  
        System.out.println("张三收到了新的内容：" + event.getSource()); 
        List<Article> articleList = userService.getUserArticles(1);
        for(Article article:articleList){
        	System.out.println("===========文章"+article.getId()+"============");
        	System.out.println(article.getTitle());
        	System.out.println(article.getTitle());
        	System.out.println(article.getContent());
        	System.out.println(article.getUser().getUserName());
        	System.out.println(article.getUser().getUserAddress());
        	System.out.println("============================");
        	System.out.println();
        	System.out.println();
        }
	}  
} 
