package com.caveofprogramming.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) 
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/caveofprogramming/spring/aop/beans.xml");
			
		Camera camera = (Camera) context.getBean("camera");
			
		camera.snap();	
		camera.snap(1000);
		camera.snap(3.1);
		camera.snap("Prague castle"); //throws exception
		camera.snapNightTime();
		camera.snap(50, 8.7);
		
		camera.snapCar(new Car());
		
		context.close();
	}
}
