package com.capgemini.spring.renderer;

import com.capgemini.spring.provider.MessageProvider;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("renderer")
public class MessageRenderer
{
	private MessageProvider provider;
	
	@Autowired
	public MessageRenderer(MessageProvider provider)
	{
		this.provider = provider;
	}

	public void render()
	{
		provider.message();
	}
}