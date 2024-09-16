package com.petproject.auction_notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuctionNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionNotificationsApplication.class, args);
	}

}
