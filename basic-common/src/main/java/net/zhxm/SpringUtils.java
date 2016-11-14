package net.zhxm;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Spring 工具类
 * 
 * <p>Title: SpringUtils.java</p>
 * <p>Description:IT部</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company:石家庄天远科技有限公司</p>
 * @author zhengxingmiao
 * @date Mar 10, 2014 4:17:31 PM
 * @version V1.0
 */
@Component("springUtils")
public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public SpringUtils() {

	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringUtils.applicationContext = context;
	}

	/**
	 * 根据beanName 获取 spring bean
	 * 
	 * @param beanName
	 * @return Object
	 */
	public static Object getBean(String beanName) {
		if (beanName == null) return null;
		return applicationContext.getBean(beanName);
	}
	
	public static <T> T getBean(String name, Class<T> type){
		Assert.hasText(name);
		Assert.notNull(type);
		return applicationContext.getBean(name, type);
	}

	/**
	 * 根据bean type 获取springBean
	 * 
	 * @param clazz
	 * @return
	 */
	public static Object getBeanByType(Class clazz) {
		return applicationContext.getBean(clazz);
	}

	/**
	 * 获取 Spring applicationContext
	 * 
	 * @return
	 */
	public static ApplicationContext getContext() {
		return applicationContext;
	}


}
