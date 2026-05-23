package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class HomeController {

    @GetMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute("userName") == null) {
            return "redirect:/view/login";
        }
        return "Home";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/view/home";
    }
}
