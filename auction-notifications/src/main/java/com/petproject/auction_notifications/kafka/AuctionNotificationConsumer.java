package com.petproject.auction_notifications.kafka;

import com.petproject.auction.model.AuctionDTO;
import com.petproject.auction.model.UserDTO;
import com.petproject.auction_notifications.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionNotificationConsumer {

    private final UserClient userClient;

    @KafkaListener(topics = "${kafka.topic}",
            groupId = "${kafka.consumer.group-id}",
            containerFactory = "auctionListener")
    public void handle(@Payload AuctionDTO auction) {
        UserDTO user = userClient.getUserById(auction.getLastUser()).getBody();
        System.out.println(user);
    }
}
