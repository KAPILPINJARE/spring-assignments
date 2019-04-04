package com.capgemini.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import com.capgemini.spring.provider.*;
import com.capgemini.spring.renderer.*;

@Configuration
public class MyJavaConfiguration
{
	@Bean
	public MessageProvider provider()
	{
		return new GMMessageProvider();
	}

 	@Bean
	public MessageRenderer renderer()
	{
		MessageRenderer render = new MessageRenderer();
		render.setMessageProvider(provider());
		return render;
	}
}