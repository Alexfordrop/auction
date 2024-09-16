package com.petproject.auction.rest.mapper;

import com.petproject.auction.domain.entity.User;
import com.petproject.auction.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

    List<UserDTO> toDTOList(List<User> users);
}
