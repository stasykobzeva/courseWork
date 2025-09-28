package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/register")
    @ResponseBody
    public ResponseEntity<String> register(@RequestBody Map<String, String> body, HttpSession session) {
        String email = body.get("email");
        String name = body.get("name");
        String username = body.get("username");
        String password = body.get("password");

        if (email == null || name == null || username == null || password == null) {
            return ResponseEntity.badRequest().body("Все поля обязательны");
        }

        if (userService.findByEmail(email).isPresent() || userService.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Пользователь с таким email или username уже существует");
        }

        User user = userService.registerUser(email, name, username, password);
        session.setAttribute("user", user);
        return ResponseEntity.ok("Успешная регистрация");
    }

    @PostMapping("/api/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody Map<String, String> body, HttpSession session, HttpServletResponse response) {
        String username = body.get("username");
        String password = body.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Требуются username и password");
        }

        return userService.findByUsername(username)
                .map(user -> {
                    if (userService.checkPassword(user, password)) {
                        session.setAttribute("user", user);
                        // Не нужно вручную добавлять JSESSIONID, контейнер сделает это автоматически
                        return ResponseEntity.ok("Успешный вход");
                    } else {
                        return ResponseEntity.status(401).body("Неверный пароль");
                    }
                }).orElse(ResponseEntity.status(404).body("Пользователь не найден"));
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/"; // редирект на главную страницу
    }
}