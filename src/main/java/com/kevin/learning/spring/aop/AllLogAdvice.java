package com.kevin.learning.spring.aop;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AllLogAdvice {
	private Logger logger = Logger.getLogger(AllLogAdvice.class);

	// 此方法将作为前置通知
	public void myBeforeAdvice(JoinPoint jionpoint) {
		// 获取被调用的类名
		String targetClassName = jionpoint.getTarget().getClass().getName();
		// 获取被调用的方法名
		String targetMethodName = jionpoint.getSignature().getName();
		// 日志格式字符串
		String logInfoText = "前置通知：" + targetClassName + "类的"
				+ targetMethodName + "方法开始执行";
		// 将日志信息写入配置的文件中
		logger.info(logInfoText);
	}

	// 此方法将作为后置通知
	public void myAfterReturnAdvice(JoinPoint jionpoint) {
		// 获取被调用的类名
		String targetClassName = jionpoint.getTarget().getClass().getName();
		// 获取被调用的方法名
		String targetMethodName = jionpoint.getSignature().getName();
		// 日志格式字符串
		String logInfoText = "后置通知：" + targetClassName + "类的"
				+ targetMethodName + "方法开始执行";
		// 将日志信息写入配置的文件中
		logger.info(logInfoText);
	}

	// 此方法将作为异常通知
	public void myThrowingAdvice(JoinPoint jionpoint, Exception e) {
		// 获取被调用的类名
		String targetClassName = jionpoint.getTarget().getClass().getName();
		// 获取被调用的方法名
		String targetMethodName = jionpoint.getSignature().getName();
		// 日志格式字符串
		String logInfoText = "异常通知：执行" + targetClassName + "类的"
				+ targetMethodName + "方法时发生异常";
		// 将日志信息写入配置的文件中
		logger.info(logInfoText);
	}

	// 此方法将作为环绕通知
	public void myAroundAdvice(ProceedingJoinPoint jionpoint) throws Throwable {
		long beginTime = System.currentTimeMillis();
		jionpoint.proceed();
		long endTime = System.currentTimeMillis();
		// 获取被调用的方法名
		String targetMethodName = jionpoint.getSignature().getName();
		// 日志格式字符串
		String logInfoText = "环绕通知：" + targetMethodName + "方法调用前时间" + beginTime
				+ "毫秒," + "调用后时间" + endTime + "毫秒";
		// 将日志信息写入配置的文件中
		logger.info(logInfoText);
	}
}