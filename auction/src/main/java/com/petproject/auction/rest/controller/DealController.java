package com.petproject.auction.rest.controller;

import com.petproject.auction.client.DealApi;
import com.petproject.auction.model.AuctionDTO;
import com.petproject.auction.model.DealDTO;
import com.petproject.auction.rest.mapper.AuctionMapper;
import com.petproject.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DealController implements DealApi {

    private final AuctionService auctionService;
    private final AuctionMapper auctionMapper;

    @Override
    public ResponseEntity<AuctionDTO> createDeal(DealDTO dealDTO) {
        return ResponseEntity.ok(auctionMapper.toDTO(auctionService
                .updateAuctionWithDeal(dealDTO.getAuctionId(), auctionMapper.toEntity(dealDTO))));
    }
}
