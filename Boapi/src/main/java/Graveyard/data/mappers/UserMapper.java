package Graveyard.data.mappers;

import Graveyard.data.dto.account.UserRegisterDTO;
import Graveyard.data.dto.account.UserItemDTO;
import Graveyard.entities.account.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserItemDTO toDto(UserEntity category);

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserEntity fromRegisterDTO(UserRegisterDTO dto);
}
