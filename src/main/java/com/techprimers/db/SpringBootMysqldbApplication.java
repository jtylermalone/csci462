package com.techprimers.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;


@EnableJpaRepositories(basePackages = "com.techprimers.db.repository")
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class SpringBootMysqldbApplication {

	@RequestMapping(value = "/users")
	public String test(){
		return "Test";
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMysqldbApplication.class, args);
	}
}
