package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            return "redirect:/"; // исправлено на "/"
        }
        model.addAttribute("user", userObj);
        return "profile"; // profile.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html
    }

    @GetMapping("/book")
    public String book() {
        return "book"; // login.html
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/task/{taskNumber}")
    public String getTask(@PathVariable int taskNumber, Model model) {
        model.addAttribute("taskNumber", taskNumber);
        return "task";
    }

    @GetMapping("/info")
    public String getInfo(@RequestParam("level") int level, Model model) {
        model.addAttribute("level", level);
        // Можно передать содержимое справочного материала, если есть
        return "info";
    }

    @GetMapping("/theory")
    public String theory() {
        return "theory";
    }
}