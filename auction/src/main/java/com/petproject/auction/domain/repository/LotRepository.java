package com.petproject.auction.domain.repository;

import com.petproject.auction.domain.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LotRepository extends JpaRepository<Lot, UUID> { }
