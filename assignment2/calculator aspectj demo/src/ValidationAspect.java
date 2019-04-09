package com.capgemini.spring.aspect;

import org.springframework.stereotype.*;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.*;

@Component
@Aspect
public class ValidationAspect
{
	@After("execution (* com.capgemini.spring.calculator.CalculatorImpl.add(..))") 
	public void addLog() throws Throwable
	{
		System.out.println("after example------do addition");	
	}

	@Before("execution (* com.capgemini.spring.calculator.CalculatorImpl.subtract(..))")
	public void subtractLog() throws Throwable
	{
		System.out.println("before example-----do subtraction");	
	}

	@AfterReturning(pointcut = "execution (* com.capgemini.spring.calculator.CalculatorImpl.multiply(..))",returning="mul")
	public void multiplyLog(Integer mul) throws Throwable
	{
		 
		System.out.println("afterReturning example----------" + mul);	
	}

	@Around("execution (* com.capgemini.spring.calculator.CalculatorImpl.divide(..))")
	public void divideLog(ProceedingJoinPoint pjp) throws Throwable
	{
		Object[] args = pjp.getArgs();
		int secondArg = Integer.parseInt(args[1].toString());
		if(secondArg == 0)
			System.out.println("around example------denominator should not be 0");
		else if(secondArg < 0)
			System.out.println("denominator should not be less than 0");
		else
			pjp.proceed();	
	}

	@AfterThrowing(pointcut = "execution (* com.capgemini.spring.calculator.CalculatorImpl.afterThrow(..))",throwing="ex")
	public void multiplyLog(Exception ex)
	{	 
		System.out.println("afterThrowing example-------denominatior should not be zeroooooo");	
	}

}