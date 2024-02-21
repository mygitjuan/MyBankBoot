package com.dxc.mypersonalbankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class MyBankApiApplication {

	private static final Logger logger = LoggerFactory.getLogger(MyBankApiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MyBankApiApplication.class, args);
	}

}
