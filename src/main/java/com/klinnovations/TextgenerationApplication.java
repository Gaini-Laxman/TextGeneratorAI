package com.klinnovations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.klinnovations", "com.otherpackage"})
public class TextgenerationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextgenerationApplication.class, args);
	}

}
