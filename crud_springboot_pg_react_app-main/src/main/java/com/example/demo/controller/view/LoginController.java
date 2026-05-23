package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {
        String role = authService.authenticate(email, password);
        if (role != null) {
            session.setAttribute("userName", authService.getUserName(email));
            session.setAttribute("userEmail", email);
            session.setAttribute("userRole", role);
            if ("CLIENTE".equals(role)) {
                return "redirect:/view/mis-citas";
            }
            return "redirect:/view/home";
        }
        return "redirect:/view/login?error=true";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/view/login";
    }
}
