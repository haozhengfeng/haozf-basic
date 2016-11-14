package org.haozf.basic.user.controller;

import java.beans.ConstructorProperties;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.ConstructorResult;

import org.haozf.basic.model.Pager;
import org.haozf.basic.model.SystemContext;
import org.haozf.basic.user.model.User;
import org.haozf.basic.user.model.User2;
import org.haozf.basic.user.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService<User> userService;

	@Resource
	private IUserService<User2> user2Service;

	@RequestMapping("/toUser")
	public String toUser() {
		// DatabaseContextHolder.setCustomerType(DataSource.dataSource1.toString());
		return "userinfo";
	}

	@RequestMapping("/list")
	public String list(Model model) {
		try {

			// SystemContext.setPageOffset(5);
			// SystemContext.setPageSize(15);

			long l1 = new Date().getTime();
			Pager<User> u = userService.find();
			long l2 = new Date().getTime();
			System.out.println("++++++++++++total time use: " + (l2 - l1));
			model.addAttribute("users", u.getDatas());
			model.addAttribute("page", u.toString());

			// DatabaseContextHolder.setCustomerType(DataSource.dataSource2.toString());
			// Pager<User2> u2 = user2Service.find();
			// model.addAttribute("users2", u2.getDatas());

		} finally {
			// SystemContext.removePageOffset();
			// SystemContext.removePageSize();
		}
		return "list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(@RequestParam int page, @RequestParam int pageSize, Model model) {
		try {

			SystemContext.setPageOffset(page);
			SystemContext.setPageSize(pageSize);

			long l1 = new Date().getTime();
			Pager<User> u = userService.find();
			long l2 = new Date().getTime();
			System.out.println("++++++++++++total time use: " + (l2 - l1));
			model.addAttribute("users", u.getDatas());
			model.addAttribute("page", u.toString());

			// DatabaseContextHolder.setCustomerType(DataSource.dataSource2.toString());
			// Pager<User2> u2 = user2Service.find();
			// model.addAttribute("users2", u2.getDatas());

		} finally {
			// SystemContext.removePageOffset();
			// SystemContext.removePageSize();
		}
		return "list";
	}

	@RequestMapping("/{id}")
	public String user(@PathVariable int id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		return "userinfo";
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public String find(@RequestParam Integer id, Model model, RedirectAttributes attributes) {
		User user = userService.getUser(id);
		// System.out.println(user.getUsername());

		// User user2 = new User();
		// user2.setId(user.getId());
		// user2.setUsername(user.getUsername());

		// System.out.println(SystemContext.getPageOffset());
		// System.out.println(SystemContext.getPageSize());
		// Pager<User> u = userService.find();
		// for(User us:u.getDatas()){
		// System.out.println(us.getId());
		// }

		String us = JSONObject.toJSONString(user);

		attributes.addAttribute("user", us);
		model.addAttribute("user", user);
		return "userinfo";
		// return "redirect:add2";
	}

	@RequestMapping("/users")
	@ResponseBody
	public List users(Model model) {
		List<User> users = userService.list();
		model.addAttribute("users", users);
		return users;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(User user, Model model) {
		User u = userService.addUser(user);
		User u2 = userService.getUser(u.getId());
		System.out.println(u2.getUsername());
		u.setUsername("update"+u.getUsername());
		userService.updateUser(u);
		model.addAttribute("id", u.getId());
		return "userinfo";
	}

	@RequestMapping(value = "/add2")
	public String add2(@ModelAttribute("user") String us, Model model) {
		User user = JSONObject.parseObject(us, User.class);

		User2 user2 = new User2();
		user2.setId(user.getId());
		user2.setName(user.getUsername());
		user2Service.addUser(user2);

		Pager<User2> u2 = user2Service.find();
		model.addAttribute("users2", u2.getDatas());
		return "list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam int id, Model model) {

		userService.deleteUser(id);
		model.addAttribute("delFlag", true);

		return "userinfo";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(User user, Model model) {
		userService.updateUser(user);
		model.addAttribute("user", user);
		return "userinfo";
	}

}
