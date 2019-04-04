package com.capgemini.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.capgemini.spring.renderer.MessageRenderer;
import com.capgemini.spring.config.*;

public class Test
{
	public static void main(String[] args)
	{					
		ApplicationContext context = new AnnotationConfigApplicationContext(MyJavaConfiguration.class);
		
		MessageRenderer renderer = (MessageRenderer) context.getBean("renderer",MessageRenderer.class); 
		renderer.render();
		
		MessageRenderer renderer1 = (MessageRenderer) context.getBean("renderer",MessageRenderer.class);
		System.out.println(renderer.hashCode());
		System.out.println(renderer1.hashCode());
		if(renderer.hashCode() == renderer1.hashCode())
			System.out.println("It is singleton");
		else
			System.out.println("It is prototype");
	}
}