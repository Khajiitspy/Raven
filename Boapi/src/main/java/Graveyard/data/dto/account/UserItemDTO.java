package Graveyard.data.dto.account;

import lombok.Data;

@Data
public class UserItemDTO {
    private Long id;
    private String email;
    private String phone;
    private String image;
    private String dateCreated;
}
