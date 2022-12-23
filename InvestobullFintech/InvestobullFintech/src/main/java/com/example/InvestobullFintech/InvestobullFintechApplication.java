package com.example.InvestobullFintech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.example.InvestobullFintech.Repo.CandlesRepository;

@SpringBootApplication

public class InvestobullFintechApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestobullFintechApplication.class, args);
		
		
	}

}
