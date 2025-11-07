package Graveyard.controllers;

import Graveyard.entities.UserEntity;
import Graveyard.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AccountService accountService;

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        // if (authentication != null && authentication.isAuthenticated()) {
        //     // try {
        //     //     UserEntity user = accountService.findByUsername(authentication.getName());
        //     //     model.addAttribute("user", user);
        //     // } catch (Exception e) {
        //     //     // user not found in DB — ignore
        //     // }
        // }

        return "home";
    }
}
