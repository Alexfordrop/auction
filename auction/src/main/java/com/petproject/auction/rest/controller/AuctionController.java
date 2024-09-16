package com.petproject.auction.rest.controller;

import com.petproject.auction.client.AuctionApi;
import com.petproject.auction.model.AuctionDTO;
import com.petproject.auction.rest.mapper.AuctionMapper;
import com.petproject.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AuctionController implements AuctionApi {

    private final AuctionService auctionService;
    private final AuctionMapper auctionMapper;

    @Override
    public ResponseEntity<AuctionDTO> createAuction(AuctionDTO auctionDTO) {
        return ResponseEntity.ok(auctionMapper.toDTO(auctionService.createAuction(auctionMapper.toEntity(auctionDTO))));
    }

    @Override
    public ResponseEntity<Void> deleteAuction(UUID id) {
        auctionService.deleteAuction(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<AuctionDTO>> getAllAuctions() {
        return ResponseEntity.ok(auctionMapper.toDTOList(auctionService.getAllAuctions()));
    }

    @Override
    public ResponseEntity<AuctionDTO> getAuctionById(UUID id) {
        return ResponseEntity.ok(auctionMapper.toDTO(auctionService.getAuctionById(id)));
    }

    @Override
    public ResponseEntity<List<AuctionDTO>> getClosedAuctions() {
        return ResponseEntity.ok(auctionMapper.toDTOList(auctionService.getClosedAuctions()));
    }

    @Override
    public ResponseEntity<List<AuctionDTO>> getOpenAuctions() {
        return ResponseEntity.ok(auctionMapper.toDTOList(auctionService.getOpenAuctions()));
    }

    @Override
    public ResponseEntity<AuctionDTO> updateAuction(UUID id, AuctionDTO auctionDTO) {
        return ResponseEntity.ok(auctionMapper.toDTO(auctionService
                .updateAuction(id, auctionMapper.toEntity(auctionDTO))));
    }
}
