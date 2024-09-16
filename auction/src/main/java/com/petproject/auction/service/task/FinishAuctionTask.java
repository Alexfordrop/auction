package com.petproject.auction.service.task;

import com.petproject.auction.service.AuctionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import java.util.concurrent.Callable;

@AllArgsConstructor
public class FinishAuctionTask implements Runnable {

    private final AuctionService auctionService;
    private final UUID auctionId;

    @Override
    public void run() {
        auctionService.finishAuction(auctionId);
    }
}