package Graveyard.data.mappers;

import Graveyard.data.dto.account.UserItemDTO;
import Graveyard.entities.account.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "createdAt", target = "dateCreated", dateFormat = "yyyy-MM-dd HH:mm:ss")
    UserItemDTO toDto(UserEntity entity);
}
