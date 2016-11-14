package org.haozf.basic.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.haozf.basic.user.model.User;
import org.haozf.basic.user.service.IUserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTask {

	@Resource
	private IUserService<User> userService;

	/**
	 * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下） cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23)
	 * *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT) 注意： 30 * * * * *
	 * 表示每分钟的第30秒执行，而（*斜杠30）表示每30秒执行
	 * 
	 * */
	@Scheduled(cron = "*/10 * * * * *")
	public void firstTask() {
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println("It is first task!时间：" + now);
//		List<User> users = userService.list();
//		for (User user : users) {
//			System.out.println(user.getId() + "----------->" + user.getUsername());
//		}
		User user1 = userService.getUser(1);
		System.out.println(user1.getId()+"----------->"+user1.getUsername());
		user1.setUsername(now);
		userService.updateUser(user1);
		System.out.println(user1.getId()+"----------->"+user1.getUsername());
	}
	
	@Scheduled(cron = "*/5 * * * * *")
	public void secondTask() {
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println("It is second task!时间：" + now);
//		List<User> users = userService.list();
//		for (User user : users) {
//			System.out.println(user.getId() + "----------->" + user.getUsername());
//		}
		User user1 = userService.getUser(1);
		System.out.println(user1.getId()+"----------->"+user1.getUsername());
		user1.setUsername(now);
		userService.updateUser(user1);
		System.out.println(user1.getId()+"----------->"+user1.getUsername());
	}
}
