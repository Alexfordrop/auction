package com.petproject.auction.rest.mapper;

import com.petproject.auction.domain.entity.Lot;
import com.petproject.auction.model.LotDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LotMapper {

    Lot toEntity(LotDTO lotDTO);

    LotDTO toDTO(Lot lot);

    List<LotDTO> toDTOList(List<Lot> lots);
}
