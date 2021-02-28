package com.blog.springbootdynamodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.blog.springbootdynamodb")
public class SpringbootDynamodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDynamodbApplication.class, args);
	}

}
