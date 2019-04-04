package com.capgemini.spring.renderer;

import com.capgemini.spring.provider.MessageProvider;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Service("renderer")
public class MessageRenderer
{
	@Autowired
	@Qualifier("provider")
	private MessageProvider provider;
	
	public void render()
	{
		provider.message();
	}
}