package com.petproject.auction.service.impl;

import com.petproject.auction.domain.entity.Lot;
import com.petproject.auction.domain.repository.LotRepository;
import com.petproject.auction.service.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LotServiceImpl implements LotService {

    private final LotRepository lotRepository;


    @Override
    public Lot createLot(Lot lot) {
        return lotRepository.save(lot);
    }

    @Override
    public void deleteLot(UUID id) {
        if (lotRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Лот с id " + id + " не найден");
        }
        lotRepository.deleteById(id);
    }

    @Override
    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    @Override
    public Lot getLotById(UUID id) {
        return lotRepository.findById(id).orElseThrow(() -> new RuntimeException("Лот с id " + id + " не найден"));
    }

    @Override
    public Lot updateLot(UUID id, Lot lot) {
        if (lotRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Лот с id " + id + " не найден");
        }
        lot.setId(id);
        return lotRepository.save(lot);
    }
}
