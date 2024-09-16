package com.petproject.auction.domain.repository;

import com.petproject.auction.domain.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuctionRepository extends JpaRepository<Auction, UUID> {

    List<Auction> findByIsActiveTrue();

    List<Auction> findByIsActiveFalse();
}
