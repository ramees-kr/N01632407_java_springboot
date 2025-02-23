package com.jdbc.activity2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Activity2Application {
	public static void main(String[] args) {
//        SpringApplication.run(Activity1Application.class, args);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		GreetingService greeter = context.getBean(GreetingService.class);
		greeter.greeting();
		context.close();
	}
}
