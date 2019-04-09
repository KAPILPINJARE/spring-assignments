package com.capgemini.spring.aspect;

import org.springframework.stereotype.*;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.*;

@Component
@Aspect
public class ValidationAspect
{
	@After("execution (* com.capgemini.spring.calculator.CalculateImpl.add(..))") 
	public void addLog() throws Throwable
	{
		System.out.println("do addition");	
	}

	@Before("execution (* com.capgemini.spring.calculator.CalculateImpl.subtract(..))")
	public void subtractLog() throws Throwable
	{
		System.out.println("do subtraction");	
	}

	@AfterReturning(pointcut = "execution (* com.capgemini.spring.calculator.CalculateImpl.multiply(..))",returning="mul")
	public void multiplyLog(Integer mul) throws Throwable
	{
		 
		System.out.println(mul);	
	}

	@Around("execution (* com.capgemini.spring.calculator.CalculateImpl.divide(..))")
	public void divideLog(ProceedingJoinPoint pjp) throws Throwable
	{
		Object[] args = pjp.getArgs();
		int secondArg = Integer.parseInt(args[1].toString());
		if(secondArg == 0)
			System.out.println("denominator should not be 0");
		else if(secondArg < 0)
			System.out.println("denominator should not be less than 0");
		else
			pjp.proceed();	
	}
}