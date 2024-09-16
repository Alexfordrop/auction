package com.petproject.auction_notifications.kafka;

import com.petproject.auction.model.AuctionDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class NotificationConsumerConfig {

    @Value("${kafka.servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, AuctionDTO> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(AuctionDTO.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AuctionDTO> auctionListener() {
        ConcurrentKafkaListenerContainerFactory<
                String, AuctionDTO> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
