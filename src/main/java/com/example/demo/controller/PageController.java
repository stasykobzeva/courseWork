package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    /*  @GetMapping("/contacts")
    public String contacts() {
        return "contacts";}

     */
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

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // register.html
    }

    @GetMapping("/task/{taskNumber}")
    public String getTask(@PathVariable int taskNumber, Model model) {
        model.addAttribute("taskNumber", taskNumber);
        return "task"; // шаблон task.html
    }
}