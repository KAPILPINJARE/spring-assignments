package com.capgemini.spring.calculator;

import org.springframework.stereotype.*;

@Service
public class CalculateImpl implements Calculator
{
	
	public void add(Integer num1,Integer num2)
	{
		System.out.println(num1+num2);
	}
	
	public void subtract(Integer num1, Integer num2)
	{
		System.out.println(num1-num2);
	}
	
	public Integer multiply(Integer num1,Integer num2)
	{
		return (num1*num2);
	}
	
	public void divide(Integer num1,Integer num2)
	{
		System.out.println(num1/num2);
	}
}