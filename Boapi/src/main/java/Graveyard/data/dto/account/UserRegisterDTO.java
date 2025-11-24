package Graveyard.data.dto.account;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserRegisterDTO {
    private String email;
    private String phone;
    private MultipartFile image;
    private String password;
}
