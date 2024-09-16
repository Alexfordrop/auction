package com.petproject.auction.rest.controller;

import com.petproject.auction.client.LotApi;
import com.petproject.auction.model.LotDTO;
import com.petproject.auction.rest.mapper.LotMapper;
import com.petproject.auction.service.LotService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class LotController implements LotApi {

    private final LotService lotService;
    private final LotMapper lotMapper;


    @Override
    public ResponseEntity<LotDTO> createLot(LotDTO lotDTO) {
        return ResponseEntity.ok(lotMapper.toDTO(lotService.createLot(lotMapper.toEntity(lotDTO))));
    }

    @Override
    public ResponseEntity<Void> deleteLot(UUID id) {
        lotService.deleteLot(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<LotDTO>> getAllLots() {
        return ResponseEntity.ok(lotMapper.toDTOList((lotService.getAllLots())));
    }

    @Override
    public ResponseEntity<LotDTO> getLotById(UUID id) {
        return ResponseEntity.ok(lotMapper.toDTO(lotService.getLotById(id)));
    }

    @Override
    public ResponseEntity<LotDTO> updateLot(UUID id, LotDTO lotDTO) {
        return ResponseEntity.ok(lotMapper.toDTO(lotService.updateLot(id, lotMapper.toEntity(lotDTO))));
    }
}
