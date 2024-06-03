package com.sscarlett.big_ambitions_companion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sscarlett.big_ambitions_companion.dao")
public class BigAmbitionsCompanionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BigAmbitionsCompanionApplication.class, args);
	}

}
