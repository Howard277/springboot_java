package com.ms.springbootdemo_version009.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 定义一个日志切片类，用来记录重要方法的调用情况。
 * 
 * @author Administrator
 *
 */
@Slf4j
@Aspect
@Component
public class SystemLogConfig {
	/*
	 * 系统日志 类 切入点
	 */
	@Pointcut("@within(com.ms.springbootdemo_version009.annotation.SystemLogAnnotation)")
	public void systemLogClass() {
	}

	/*
	 * 系统日志 方法 切入点
	 */
	@Pointcut("@annotation(com.ms.springbootdemo_version009.annotation.SystemLogAnnotation)")
	public void systemLogMethod() {
	}

	/*
	 * 系统日志 排除方法 切入点
	 */
	@Pointcut("@annotation(com.ms.springbootdemo_version009.annotation.SystemLogExcludeAnnotation)")
	public void systemLogExclude() {
	}

	/**
	 * 系统日志切入点<br/>
	 * 对于使用“系统日志类、方法注解，并且没有使用排除方法注解的方法生效”
	 */
	@Pointcut("(systemLogClass()||systemLogMethod())&&(!systemLogExclude())")
	public void systemLog() {

	}

	/*
	 * 前置通知
	 */
	@Before("systemLog()")
	public void doBefore(JoinPoint jp) {
		Object[] os = jp.getArgs();// 获得入参
		String className = jp.getTarget().getClass().getSimpleName();// 获得类名称
		String methodName = jp.getSignature().getName();// 获得方法名称
		StringBuilder sBuilder = new StringBuilder();
		for (Object object : os) {
			sBuilder.append(JSONObject.toJSONString(object));
		}
		log.info("方法名：{} 入参：{}", className + "." + methodName, sBuilder.toString());
	}

	/*
	 * 后置通知
	 */
	@After("systemLog()")
	public void doAfter(JoinPoint jp) {
	}

	/*
	 * 环绕通知
	 */
	@Around("systemLog()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		String className = pjp.getTarget().getClass().getSimpleName();// 获得类名称
		String methodName = className + "." + pjp.getSignature().getName();// 获得方法名称
		DateTime startDate = DateTime.now();// 记录程序的开始时间
		Object retVal = pjp.proceed();// 执行目标方法，并获得返回值。
		DateTime endDate = DateTime.now();// 记录程序的结束时间
		Long spendTime = endDate.getMillis() - startDate.getMillis();
		log.info("方法名：{} 返回值：{} 用时：{}", methodName, JSONObject.toJSONString(retVal), spendTime);
		return retVal;
	}

	/*
	 * 异常返回通知
	 */
	// @AfterThrowing(pointcut = "systemLog()", throwing = "ex")
	public void doThrowing(JoinPoint jp, Throwable ex) {
		String className = jp.getTarget().getClass().getSimpleName();// 获得类名称
		String methodName = className + "." + jp.getSignature().getName();// 获得方法名称
		log.info("方法名：{} 异常信息：{}", methodName, ex);
	}
}
