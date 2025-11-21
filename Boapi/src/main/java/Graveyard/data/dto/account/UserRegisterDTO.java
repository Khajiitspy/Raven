package Graveyard.data.dto.account;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String lastName;
    private String name;
    private String email;
    private String phone;
    private String password;
}
