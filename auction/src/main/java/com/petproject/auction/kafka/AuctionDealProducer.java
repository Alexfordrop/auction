package com.petproject.auction.kafka;

import com.petproject.auction.domain.entity.Auction;
import com.petproject.auction.model.AuctionDTO;
import com.petproject.auction.rest.mapper.AuctionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionDealProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final AuctionMapper auctionMapper;

    @Value("${kafka.topic}")
    private String topicName;

    public void sendMessage(Auction auction) {
        AuctionDTO auctionDTO = auctionMapper.toDTO(auction);
        kafkaTemplate.send(topicName, auctionDTO);
    }
}
