package Graveyard.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import Graveyard.data.dto.account.UserRegisterDTO;
import Graveyard.data.dto.account.UserItemDTO;
import Graveyard.services.UserService;
import Graveyard.validators.helpers.ValidatedDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
@Tag(name = "Account")
public class UserController {

    private final UserService accountService;

    @PostMapping(value = "/register", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserItemDTO> register(@ValidatedDto @ModelAttribute UserRegisterDTO dto) {
        return ResponseEntity.ok(accountService.registerUser(dto));
    }
}
