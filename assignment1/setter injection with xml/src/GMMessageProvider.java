package com.capgemini.spring.provider;

public class GMMessageProvider implements MessageProvider
{
	@Override
	public void message()
	{
		System.out.println("Good Morning");
	}
}