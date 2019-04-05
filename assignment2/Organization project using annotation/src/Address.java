package com.capgemini.spring.organization.address;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Component("address")
public class Address
{
	@Value("Mumbai")
	private String city;
	@Value("Maharashtra")
	private String state;
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}

	@Override
	public String toString()
	{
		return "city = " + city + "\nState = " + state;
	}
}