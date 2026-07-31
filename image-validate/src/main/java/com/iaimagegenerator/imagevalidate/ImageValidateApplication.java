package com.iaimagegenerator.imagevalidate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ImageValidateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageValidateApplication.class, args);
	}

}
