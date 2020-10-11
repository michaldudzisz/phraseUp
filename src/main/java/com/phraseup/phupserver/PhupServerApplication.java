package com.phraseup.phupserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class PhupServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhupServerApplication.class, args);
	}

}
