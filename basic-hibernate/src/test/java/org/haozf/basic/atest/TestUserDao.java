package org.haozf.basic.atest;

import javax.inject.Inject;


import org.haozf.basic.user.dao.IMyuserDao;
import org.haozf.basic.user.dao.IUserDao;
import org.haozf.basic.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestUserDao extends AbstractSessionTestCase {

	@Inject
	private IUserDao userDao;
	
	@Inject
	private IMyuserDao myuserDao;
	
//	@Inject
//	private IMyuserDao myuserDaoExt;

	@Test
	public void initUser() {
		for (int i = 0; i < 1000; i++) {
			String[] firstname = { "张", "李", "王", "赵" ,"钱","孙","郝","周","武","郑","马"};
			String[] secondname ={ "三", "四", "五", "六" ,"七","八","九","十","十一","十二","十三"};
			// 产生0-(arr.length-1)的整数值,也是数组的索引
			int index = (int) (Math.random() * firstname.length);
			String fname = firstname[index];
			String sname = secondname[index];
			User user = new User();
			System.out.println(fname + "  " + sname);
			user.setUsername(fname + sname);
//			userDao.add(user);
		}

	}

	@Test
	public void testLoad() {
		
//		userDao.changeDb(dbFactory);
		
		User user = new User();
		user.setId(1);
		user.setUsername("张三2");
//		userDao.add(user);
		
//		userDao.update(user);
//		userDao.delete(3);
		
//		Page page = new Page();
//		page.setPageIndex(1);
//		page.setPageSize(4);
		
//		myuserDao.listUser(page);
		
//		System.out.println(userDao.load(1).getUsername());
		
//		System.out.println(dbFactory.generateFactory());
		
		
//		myuserDaoExt.listUser(page);
	}
	
	
	public void testsql(){
	}

}
