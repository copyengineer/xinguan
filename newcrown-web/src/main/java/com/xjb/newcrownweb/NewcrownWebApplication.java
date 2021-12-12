package com.xjb.newcrownweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 22364
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.xjb"})
public class NewcrownWebApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(NewcrownWebApplication.class, args);
	}

}
