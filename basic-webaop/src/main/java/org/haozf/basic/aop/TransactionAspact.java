package org.haozf.basic.aop;

import javax.inject.Inject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

//@Component
@Aspect
public class TransactionAspact {

	@Inject
	SessionFactory sessionFactory;

	SessionHolder holder;

	/**
	 * 定义一个切入点
	 */
	@Pointcut("execution(* org.haozf..*Service.*(..))")
	private void anyMethod() {
	}

	@Before("anyMethod()")
	public void before(JoinPoint joinPoint) {
		Session s = sessionFactory.openSession();
		String methodName = joinPoint.getSignature().getName();
		if (methodName.startsWith("add") || methodName.startsWith("delete") || methodName.startsWith("update")) {
			s.beginTransaction();
		}
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
	}

	@AfterReturning("anyMethod()")
	public void afterReturning(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		if (methodName.startsWith("add") || methodName.startsWith("delete") || methodName.startsWith("update")) {
			SessionFactoryUtils.closeSession(holder.getSession());
		}
	}

	@After("anyMethod()")
	public void after(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		if (methodName.startsWith("add") || methodName.startsWith("delete") || methodName.startsWith("update")) {
			holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
			Session s = holder.getSession();
			s.flush();
		}
		// TransactionSynchronizationManager.unbindResource(sessionFactory);
	}

	@AfterThrowing(pointcut = "anyMethod()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex) {
		System.out.println(ex.getMessage());
		Session s = holder.getSession();
		s.getTransaction().rollback();
		// TransactionSynchronizationManager.unbindResource(sessionFactory);
		String methodName = joinPoint.getSignature().getName();
		if (methodName.startsWith("add") || methodName.startsWith("delete") || methodName.startsWith("update")) {
			SessionFactoryUtils.closeSession(holder.getSession());
		}
	}

}
