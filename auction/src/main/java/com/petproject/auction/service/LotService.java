package com.petproject.auction.service;

import com.petproject.auction.domain.entity.Lot;

import java.util.List;
import java.util.UUID;

public interface LotService {

    Lot createLot(Lot lot);

    void deleteLot(UUID id);

    List<Lot> getAllLots();

    Lot getLotById(UUID id);

    Lot updateLot(UUID id, Lot lot);
}
