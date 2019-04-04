package com.capgemini.spring.renderer;

import com.capgemini.spring.provider.MessageProvider;

public class MessageRenderer
{
	private MessageProvider provider;
	public MessageRenderer(MessageProvider provider)
	{
		this.provider = provider;
	}

	public void render()
	{
		provider.message();
	}
}