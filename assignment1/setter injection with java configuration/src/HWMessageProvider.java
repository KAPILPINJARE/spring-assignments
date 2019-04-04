package com.capgemini.spring.provider;

public class HWMessageProvider implements MessageProvider
{	
	@Override
	public void message()
	{
		System.out.println("Hello World!!!");
	}
}