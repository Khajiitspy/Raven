// package Graveyard.controllers;

// import Graveyard.entities.UserEntity;
// import Graveyard.repository.IUserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;

// @Controller
// public class UserController {

//     @Autowired
//     private IUserRepository userRepository;

//     // Main page - list of users
//     @GetMapping("/")
//     public String home(Model model) {
//         var users = userRepository.findAll();
//         model.addAttribute("users", users);
//         return "index"; // templates/index.html
//     }

//     // Registration page
//     @GetMapping("/register")
//     public String registerForm(Model model) {
//         model.addAttribute("user", new UserEntity());
//         return "register"; // templates/register.html
//     }

//     @PostMapping("/register")
//     public String registerSubmit(@ModelAttribute UserEntity user,
//                                  @RequestParam("imageFile") MultipartFile imageFile) {
//         try {
//             if (!imageFile.isEmpty()) {
//                 // Set the folder where you want to save uploaded files
//                 String uploadDir = System.getProperty("user.dir") + "/uploads";
//                 Path uploadPath = Paths.get(uploadDir);
//                 if (!Files.exists(uploadPath)) {
//                     Files.createDirectories(uploadPath);
//                 }

//                 String filename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
//                 Path filePath = uploadPath.resolve(filename);
//                 imageFile.transferTo(filePath.toFile());

//                 user.setImage("/uploads/" + filename); // to use in <img th:src="${user.image}">
//             }

//             userRepository.save(user);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//         return "redirect:/";
//     }
// }
