package com.capgemini.spring.config;

import com.capgemini.spring.organization.*;
import com.capgemini.spring.organization.address.*;
import java.util.*;
import java.io.*;

import org.springframework.context.*;
import org.springframework.context.annotation.*;

@Configuration
public class MyJavaConfig
{
	@Bean
	public Organization myOrg()
	{
		Organization org = new Organization();
		org.setOrgId(101);
		org.setOrgName("Capgemini");
		org.setMarketPrice(987643);
		org.setAddress(new Address("Mumbai","Maharashtra"));
		org.setDirectors(directors());
		org.setBranches(branches());
		org.setBranchWiseHead(branchHead());
		org.setIpAddresses(ipAddress());
		org.setDatabaseDetails(databaseDetails());
		return org;
	}

	public List directors()
	{
		List<String> list = new ArrayList<>();
		list.add("Abbas Pathan");
		list.add("Krishna Kulkarni");
		return list;
	}

	public Set branches()
	{
		Set<String> set = new HashSet<>();
		set.add("Airoli");
		set.add("Vikroli");
		return set;
	}

	public Map branchHead()
	{
		Map<String,String> map = new HashMap<>();
		map.put("Airoli","Ayush");
		map.put("Vikroli","Prangshu");
		return map;
	}

	public Properties ipAddress()
	{
		Properties property = new Properties();
		property.setProperty("proxy2","125.125.125");
		property.setProperty("proxy4","101.25.555.123");
		return property;
	}

	public Properties databaseDetails()
	{
		Properties property = null;
		try
		{
			FileReader reader = new FileReader("src/dbConfig.properties");
			property = new Properties();
			property.load(reader);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}			
		return property;	
	}	
}