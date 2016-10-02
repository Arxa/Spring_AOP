package com.caveofprogramming.spring.aop;

import org.springframework.stereotype.Component;


@Component("camera")
//@Deprecated
public class Camera 
{
	
	@Deprecated
	public void snap()
	{
		System.out.println("SNAP");
	}

	public void snap(int exposure)
	{
		System.out.println("SNAP Exposure " + exposure);
	}
	
	public void snap(double exposure)
	{
		System.out.println("SNAP Exposure " + exposure);
	}
	
	public void snap(int exposure, double aperture)
	{
		System.out.println("SNAP Exposure " + exposure + aperture);
	}
	
	public String snap(String name) throws IllegalArgumentException
	{
		throw new IllegalArgumentException("An exception passing by..");
		//System.out.println("SNAP Nmae: " + name);
		//return name;
	}
	
	public void snapNightTime()
	{
		System.out.println("SNAP Night Mode " );
	}
	
	public void snapCar(Car car)
	{
		System.out.println("Snapping car! ");
	}
	
}
