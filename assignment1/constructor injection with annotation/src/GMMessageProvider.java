package com.capgemini.spring.provider;

import org.springframework.stereotype.Component;

@Component("provider")
public class GMMessageProvider implements MessageProvider
{
	@Override
	public void message()
	{
		System.out.println("Good Morning");
	}
}