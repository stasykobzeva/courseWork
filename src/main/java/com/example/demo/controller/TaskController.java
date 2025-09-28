package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @PostMapping("/solve/{taskNumber}")
    public ResponseEntity<?> markTaskAsSolved(@PathVariable int taskNumber, HttpSession session) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            return ResponseEntity.status(401).body("Не авторизован");
        }
        User user = (User) userObj;

        user.setSolvedTasks(user.getSolvedTasks() + 1);
        // Обновить пользователя
        userService.save(user);
        return ResponseEntity.ok("Задача отмечена как решённая");
    }

    @Autowired
    private UserService userService; // добавьте сюда
}