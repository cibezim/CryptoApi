package com.crypto.cyptoapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class CryptoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoApiApplication.class, args);
	}

	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		return args -> fetchAndDisplayTicker();
	}

	private TickerInfo[] tickerInfoArray;

	@Scheduled(fixedRate = 30000) // Run every 30 seconds
	public void fetchAndDisplayTicker() {
		if (restTemplate == null) {
			return;
		}


		tickerInfoArray = restTemplate.getForObject(
				"https://api.n.exchange/en/api/v1/price/BTCLTC/latest/?market_code=nex",
				TickerInfo[].class
		);


		for (TickerInfo tickerInfo : tickerInfoArray) {
			System.out.println("Ask: " + tickerInfo.getTicker().getAsk() + ", Bid: " + tickerInfo.getTicker().getBid());
		}
	}
}



