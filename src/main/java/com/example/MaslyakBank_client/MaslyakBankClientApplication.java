package com.example.MaslyakBank_client;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableAsync
public class MaslyakBankClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaslyakBankClientApplication.class, args);
		System.out.println("COMPLETE I WORK");
	}

}
