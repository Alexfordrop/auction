package com.petproject.auction.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.petproject.auction.domain.entity.Auction;
import com.petproject.auction.domain.repository.AuctionRepository;
import com.petproject.auction.exception.NotFoundException;
import com.petproject.auction.kafka.AuctionDealProducer;
import com.petproject.auction.rest.mapper.AuctionMapper;
import com.petproject.auction.service.AuctionService;
import com.petproject.auction.service.task.FinishAuctionTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final ScheduledExecutorService scheduler;
    private final AuctionMapper auctionMapper;
    private final AuctionDealProducer auctionDealProducer;

    @Override
    public Auction createAuction(Auction auction) {
        auction = auctionRepository.save(auction);
        scheduleAuctionCompletion(auction);
        return auction;
    }

    private void scheduleAuctionCompletion(Auction auction) {
        OffsetDateTime endDate = auction.getEndTime();
        long delay = endDate.atZoneSameInstant(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli() - System.currentTimeMillis();

        if (delay > 0) {
            FinishAuctionTask task = new FinishAuctionTask(this, auction.getId());
            scheduler.schedule(task, delay, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void deleteAuction(UUID id) {
        if (auctionRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Аукцион с id " + id + " не найден");
        }
        auctionRepository.deleteById(id);
    }

    @Override
    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    @Override
    public Auction getAuctionById(UUID id) {
        return auctionRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Аукцион с id " + id + " не найден"));
    }

    @Override
    public List<Auction> getOpenAuctions() {
        return auctionRepository.findByIsActiveTrue();
    }

    @Override
    public List<Auction> getClosedAuctions() {
        return auctionRepository.findByIsActiveFalse();
    }

    @Override
    public Auction updateAuction(UUID id, Auction auction) {
        if (auctionRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Аукцион с id " + id + " не найден");
        }
        auction.setId(id);
        return auctionRepository.save(auction);
    }

    @Override
    public void finishAuction(UUID id) {
        Auction auction = auctionRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Аукцион с id " + id + " не найден"));
        auction.setIsActive(false);
        auctionRepository.save(auction);
    }

    @Override
    public Auction updateAuctionWithDeal(UUID id, Auction auction) {
        Auction updatedAuction = auctionRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Аукцион с id " + id + " не найден"));
        if (!updatedAuction.getIsActive()) {
            throw new RuntimeException("Ставку можно делать только у активного аукциона");
        }
        if (updatedAuction.getStartPrice() >= auction.getLastPrice()) {
            throw new IllegalArgumentException("Ставка должна быть больше начальной цены.");
        }
        if (updatedAuction.getLastPrice() != null && updatedAuction.getLastPrice() >= auction.getLastPrice()) {
            throw new IllegalArgumentException("Ставка должна быть больше последней предложенной ставки.");
        }

        Integer startPrice = updatedAuction.getStartPrice();
        auctionMapper.updateEntityFromDTO(auction, updatedAuction);
        updatedAuction.setStartPrice(startPrice);
        Auction savedAuction = auctionRepository.save(updatedAuction);

        auctionDealProducer.sendMessage(savedAuction);

        return savedAuction;
    }
}
