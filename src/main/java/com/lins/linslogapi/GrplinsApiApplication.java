package com.lins.linslogapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.lins.linslogapi.cinfig.LinsApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(LinsApiProperty.class)
public class GrplinsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrplinsApiApplication.class, args);
		
		
	}

}


