package com.petproject.auction.service;

import com.petproject.auction.domain.entity.Auction;

import java.util.List;
import java.util.UUID;

public interface AuctionService {

    Auction createAuction(Auction auction);

    void deleteAuction(UUID id);

    List<Auction> getAllAuctions();

    Auction getAuctionById(UUID id);

    List<Auction> getOpenAuctions();

    List<Auction> getClosedAuctions();

    Auction updateAuction(UUID id, Auction auction);

    void finishAuction(UUID id);

    Auction updateAuctionWithDeal(UUID id, Auction auction);

}
