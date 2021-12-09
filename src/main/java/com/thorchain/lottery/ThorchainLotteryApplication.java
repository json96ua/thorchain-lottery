package com.thorchain.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EnableJpaRepositories("com.thorchain.lottery.*")
@ComponentScan(basePackages = { "com.thorchain.lottery.*" })
@EntityScan("com.thorchain.lottery.*")
@SpringBootApplication
public class ThorchainLotteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThorchainLotteryApplication.class, args);
	}

	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
