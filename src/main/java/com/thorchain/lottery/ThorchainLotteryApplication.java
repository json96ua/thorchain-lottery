package com.thorchain.lottery;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableJpaRepositories("com.thorchain.lottery.*")
@ComponentScan(basePackages = { "com.thorchain.lottery.*" })
@EntityScan("com.thorchain.lottery.*")
@EnableScheduling
@SpringBootApplication
public class ThorchainLotteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThorchainLotteryApplication.class, args);
	}

	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

}
