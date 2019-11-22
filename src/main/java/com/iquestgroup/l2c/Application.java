package com.iquestgroup.l2c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.iquestgroup.l2c.core.InheritedComponent;

@SpringBootApplication
@ComponentScan(basePackages = "com.iquestgroup.l2c", includeFilters = @ComponentScan.Filter(InheritedComponent.class))
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
