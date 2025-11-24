package Graveyard.data.mappers;

import Graveyard.data.dto.account.UserItemDTO;
import Graveyard.entities.account.UserEntity;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-24T14:23:18-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Red Hat, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168 = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );

    @Override
    public UserItemDTO toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserItemDTO userItemDTO = new UserItemDTO();

        if ( entity.getCreatedAt() != null ) {
            userItemDTO.setDateCreated( dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168.format( entity.getCreatedAt() ) );
        }
        userItemDTO.setId( entity.getId() );
        userItemDTO.setEmail( entity.getEmail() );
        userItemDTO.setPhone( entity.getPhone() );
        userItemDTO.setImage( entity.getImage() );

        return userItemDTO;
    }
}
