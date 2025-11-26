package Graveyard.data.mappers;

import Graveyard.data.dto.account.UserItemDTO;
import Graveyard.data.dto.account.UserRegisterDTO;
import Graveyard.entities.account.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-26T10:53:50-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Red Hat, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserItemDTO toDto(UserEntity category) {
        if ( category == null ) {
            return null;
        }

        UserItemDTO userItemDTO = new UserItemDTO();

        userItemDTO.setId( category.getId() );
        userItemDTO.setLastName( category.getLastName() );
        userItemDTO.setName( category.getName() );
        userItemDTO.setEmail( category.getEmail() );
        userItemDTO.setPhone( category.getPhone() );
        userItemDTO.setImage( category.getImage() );

        return userItemDTO;
    }

    @Override
    public UserEntity fromRegisterDTO(UserRegisterDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setLastName( dto.getLastName() );
        userEntity.setName( dto.getName() );
        userEntity.setEmail( dto.getEmail() );
        userEntity.setPhone( dto.getPhone() );

        return userEntity;
    }
}
