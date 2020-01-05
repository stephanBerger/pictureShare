package com.berger.angular.picshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.berger.angular.picshare" })
public class PictureSharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PictureSharingApplication.class, args);
	}

}
