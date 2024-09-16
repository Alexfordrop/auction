package com.petproject.auction.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auction")
public class Auction {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "last_user")
    private UUID lastUser;

    @Column(name = "last_price")
    private Integer lastPrice;

    @Column(name = "start_price")
    private Integer startPrice;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @Column(name = "end_time")
    private OffsetDateTime endTime;

    @Column(name = "lot_id")
    private UUID lotId;
}
