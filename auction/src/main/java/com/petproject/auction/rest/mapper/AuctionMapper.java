package com.petproject.auction.rest.mapper;

import com.petproject.auction.domain.entity.Auction;
import com.petproject.auction.model.AuctionDTO;
import com.petproject.auction.model.DealDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuctionMapper {

    @Mappings({
            @Mapping(source = "userId", target = "lastUser"),
            @Mapping(source = "price", target = "lastPrice")
    })
    Auction toEntity(DealDTO dealDTO);

    Auction toEntity(AuctionDTO auctionDTO);

    AuctionDTO toDTO(Auction auction);

    List<AuctionDTO> toDTOList(List<Auction> auctions);

    void updateEntityFromDTO(Auction deal, @MappingTarget Auction auction);
}
