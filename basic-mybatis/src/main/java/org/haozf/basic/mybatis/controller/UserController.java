package org.haozf.basic.mybatis.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.haozf.basic.mybatis.model.Article;
import org.haozf.basic.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/article")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping("/list")
	public String listall(Model model,HttpServletRequest request, HttpServletResponse response) {
        
		long l1 = new Date().getTime();  
		
		Page p = PageHelper.startPage(1, 2);
		List<Article> articles = userService.getUserArticlesNocache(1);
		
		long l2 = new Date().getTime();  
		System.out.println("++++++++++++total time use: " + (l2-l1));
		
		System.out.println(p.getPages());
		
		model.addAttribute("articles", articles);
		return "article/list";
	}
	
	@RequestMapping("/list/cache")
	public String listallcache(Model model,HttpServletRequest request, HttpServletResponse response) {
		long l1 = new Date().getTime();  
		
		Page p = PageHelper.startPage(1, 4);
		List<Article> articles = userService.getUserArticles(1);
		
		long l2 = new Date().getTime();  
		System.out.println("++++++++++++total time use: " + (l2-l1));
		
		System.out.println(p.getPages());
		
		model.addAttribute("articles", articles);
		return "article/list";
	}
}