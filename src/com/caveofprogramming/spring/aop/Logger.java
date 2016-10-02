package com.caveofprogramming.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger 
{
	
	// ----------- Pointcuts ----------- //
	
	
	// '..' means any argument at all. We can see that the advice occurs for all the snap method regardless their arguments, cause . 
	// e.g. without using '..' only the snap(no arguments) would be used
	// We are referring to any returning type methods with any arguments needed of the Camera class. 
	@Pointcut("execution(* com.caveofprogramming.spring.aop.Camera.*(..))")
	public void pointcutExecution()
	{}
	
	// We are referring to any returning type methods with a String argument needed of the Camera class. 
	@Pointcut("execution(* com.caveofprogramming.spring.aop.Camera.snap(String))")
	public void pointcutExecutionString()
	{}
	
	//within PCD: for any method in Class specified
	//@Deprecated means that this pointcut refers only to those classes having the @Deprecated annotation
	@Pointcut("within(@Deprecated com.caveofprogramming.spring.aop.*)")
	public void pointcutWithinDeprecated()
	{}
	
	// Normal target specifies a class, but @target specifies classes with specific annotations!
	@Pointcut("@target(org.springframework.stereotype.Component)")
	public void pointcutTargetComponentClasses()
	{}
	
	//@annotation will target all methods that are annotated with @Deprecated
	@Pointcut("@annotation(java.lang.Deprecated)")
	public void pointcutAnnotatedMethods()
	{}
	
	//@args will target any classe's method that takes as an argument an object of a @Component or @Deprecated e.t.c. Class
	@Pointcut("@args(org.springframework.stereotype.Component)")
	public void pointcutArgsOfAnnotatedClass()
	{}
	
	//bean PCD will target all methods connected with a bean called "camera"
	//bean(*camera) ->  all beans ending with camera e.t.c. or cam*ra...
	@Pointcut("bean(camera)")
	public void pointcutBean()
	{}
	
	//args() will target all methods with not arguments
	//args(int)->with an int argument and so on) + objects(full path) or args(int,*),  args(int,..*) = int and zero or more arguments
	// args will try to match any arguemnts that can me casted to ours, so if we want to target only ints for example, we should say Integer or Double and so on.
	@Pointcut("args(Double)")
	public void pointcutArgsDouble()
	{}
	
	@Pointcut("args(exposure, aperture)")
	public void pointcutArgsSpecified(int exposure, double aperture)
	{}
	
	@Pointcut("target(com.caveofprogramming.spring.aop.Camera)")
	public void pointcutTargetClass()
	{}
	
	
	
	
	// ----------- Advice ----------- //
	
	
	// Advice - this method advises another method
	// Join-point - any point of your code that could be advised
	// Pointcut - defines the join-point where we will put our code
	// Run this advice before the execution of our target method-pointcuts
	@Before("pointcutExecution()")
	public void pointcutExecutionBeforeAdvice()
	{
		System.out.println("******** BEFORE ********");
	}
	
	// Run this method after the execution of our target method-pointcut
	@After("pointcutExecution()")
	public void pointcutExecutionAfterAdvice()
	{
		System.out.println("******** AFTER ********");
	}
	
	// Run this method after the successful execution of our target method-pointcut
	@AfterReturning("pointcutExecution()")
	public void pointcutExecutionAfterReturningAdvice()
	{
		System.out.println("...After returning advice...\n\n\n");
	}
	
	// Run this method after the failed(Exception caught) execution of our target method-pointcut
	// It seems that there some issues with @AfterThrowing. @Around can be used instead.
	/*
	@AfterThrowing("pointcutExecutionString()")
	public void pointcutExecutionAfterThrowingAdvice()
	{
		System.out.println("...After throwing advice...\n\n\n");
	}*/
	
	// Before and After in one Annotation
	@Around("pointcutExecution()")
	public void pointcutExecutionAroundAdvice(ProceedingJoinPoint p)
	{
		System.out.println("--Around advice(Before)--");
		//Code here will run before the target method 
		try 
		{
			p.proceed();
			//Target method being executed
		} 
		catch (Throwable e) 
		{
			System.out.println("Inside around advice : Exception caught : " + e.getMessage());
		}
		//Code here will run after the target method
		System.out.println("--Around advice(After)--");
	}
	
	
	// With JoinPoint we can get the arguments being passed to our target methods.
	@Before("pointcutExecution()")
	public void pointcutExecutionBeforeJPAdvice(JoinPoint jp)
	{
		System.out.println("******** BEFORE WITH JOINPOINT ********");
		for(Object obj : jp.getArgs())
		{
			System.out.println("ARG: " + obj);
		}
	}
		
	//We can combine two or more Pointcuts using logical expressions
	@Before("pointcutWithinDeprecated() && pointcutTargetComponentClasses()")
	public void pointcutExecutionBeforeCombination()
	{
		System.out.println("******** BEFORE - COMPINED POINTCUTS ********");
	}	
}
