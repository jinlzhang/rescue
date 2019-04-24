package com.cjd.rescue.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cjd.rescue")
@MapperScan("com.cjd.rescue.dao")
public class ProductApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
