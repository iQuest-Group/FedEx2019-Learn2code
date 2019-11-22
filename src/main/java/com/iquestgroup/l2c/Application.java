package com.iquestgroup.l2c;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iquestgroup.l2c.core.Implementation2;
import com.iquestgroup.l2c.core.ServicePool;

@SpringBootApplication
public class Application {
	
	private static ServicePool servicePool = new ServicePool();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
