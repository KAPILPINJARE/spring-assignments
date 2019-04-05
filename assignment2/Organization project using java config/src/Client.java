package com.capgemini.spring.client;

import org.springframework.context.*;
import org.springframework.context.support.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.capgemini.spring.config.*;
import com.capgemini.spring.organization.*;

public class Client
{
	public static void main(String[] args)
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(MyJavaConfig.class);
		
		Organization org = context.getBean(Organization.class);
		System.out.println(org.toString());
	}
}