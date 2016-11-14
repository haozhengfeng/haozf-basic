package org.haozf.basic.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Component
@Aspect
public class MyAspect {

	/**
	 * 定义一个切入点
	 */
	@Pointcut("execution(* org.haozf..*.*(..))")
	private void anyMethod() {}

	/**
	 * 显示切入点所执行的方法的类名、方法名、参数
	 * @param joinPoint
	 */
	@Before("anyMethod()")
	public void doBefore(JoinPoint joinPoint) {
		Object[] os = joinPoint.getArgs();  
        //获取类名  
        String className = joinPoint.getTarget().getClass().getSimpleName();  
        //获取方法名  
        String methodName = joinPoint.getSignature().getName();  
        String param = className + "." + methodName + ":";  
        for (int i = 0; i < os.length; i++) {  
            param += "参数[" + i + "]:" + os[i].toString(); 
        }  
        System.out.println(param);
	}

}
