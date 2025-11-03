package Graveyard.controllers;

import Graveyard.entities.UserEntity;
import Graveyard.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    // Main page - list of users
    @GetMapping("/")
    public String home(Model model) {
        var users = userRepository.findAll();
        model.addAttribute("users", users);
        return "index"; // templates/index.html
    }

    // Registration page
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "register"; // templates/register.html
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute UserEntity user) {
        userRepository.save(user);
        return "redirect:/";
    }
}
