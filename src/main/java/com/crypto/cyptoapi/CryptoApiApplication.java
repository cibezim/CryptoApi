package com.crypto.cyptoapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(CryptoApiApplication.class);

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

	@Scheduled(fixedRate = 30000)
	public void fetchAndDisplayTicker() {
		if (restTemplate == null) {
			log.warn("RestTemplate is null. Skipping the fetchAndDisplayTicker task.");
			return;
		}

		try {
			tickerInfoArray = restTemplate.getForObject(
					"https://api.n.exchange/en/api/v1/price/BTCLTC/latest/?market_code=nex",
					TickerInfo[].class
			);

			for (TickerInfo tickerInfo : tickerInfoArray) {
				log.info("Ask: {}, Bid: {}", tickerInfo.getTicker().getAsk(), tickerInfo.getTicker().getBid());
			}
		} catch (Exception e) {
			log.error("Error fetching and displaying ticker.", e);
		}
	}
}






