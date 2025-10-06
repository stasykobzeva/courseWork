package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class ContactsController {

    @GetMapping("/contacts")
    public String getContacts(Model model) {
        List<Map<String, String>> contacts = Arrays.asList(
                Map.of("name", "Рашоян Анна", "phone", "+7 123 456 78 90", "email", "anna@example.com"),
                Map.of("name", "Кобзева Анастасия", "phone", "+7 987 654 32 10", "email", "anastasia@example.com"),
                Map.of("name", "Александр Михайлов", "phone", "+7 555 666 77 88", "email", "alex@example.com"));
        model.addAttribute("contacts", contacts);
        return "contacts"; // имя шаблона без расширения
    }
}