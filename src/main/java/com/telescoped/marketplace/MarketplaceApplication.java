package com.telescoped.marketplace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketplaceApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(MarketplaceApplication.class);

	public static void main(String[] args) {
		logger.info("Init marketplace application...");
		SpringApplication.run(MarketplaceApplication.class, args);
	}

}
