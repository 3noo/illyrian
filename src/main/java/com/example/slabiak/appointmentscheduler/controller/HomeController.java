package com.example.slabiak.appointmentscheduler.controller;

import com.example.slabiak.appointmentscheduler.security.CustomUserDetails;
import com.example.slabiak.appointmentscheduler.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String showRoot(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        if (currentUser != null) {
            model.addAttribute("user", userService.getUserById(currentUser.getId()));
        }
        return "index"; // Make sure 'index.html' is a valid template for your public home page
    }
    @GetMapping("/about")
    public String about() {
        return "about";  // This will return the about.html Thymeleaf template
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";  // This will return the contact.html Thymeleaf template
    }

    @GetMapping("/home")
    public String showHome(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        model.addAttribute("user", userService.getUserById(currentUser.getId()));
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        if (currentUser != null) {
            return "redirect:/home";
        }
        return "users/login";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }


}
