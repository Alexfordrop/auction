package com.petproject.auction_notifications.client;

import com.petproject.auction.client.UserApi;
import com.petproject.auction.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "auction")
public interface UserClient extends UserApi {

    @Override
    @GetMapping("/api/auction/{userId}")
    ResponseEntity<UserDTO> getUserById(@PathVariable("id") UUID userId);
}
