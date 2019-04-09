package com.capgemini.spring.main;

import org.springframework.context.support.*;
import org.springframework.context.*;

import com.capgemini.spring.calculator.*;

public class Main
{
	public static void main(String[] args)
	{	
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		Calculator calculator = context.getBean(Calculator.class);
	
		calculator.add(10,20);
		calculator.subtract(80,40);
		calculator.multiply(20,2);
		calculator.divide(50,0);
		try
		{
			calculator.afterThrow(10,0);
		}
		catch(Exception ex)
		{}
	}
}