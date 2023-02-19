package com.userManagement.Controller;

import com.userManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/api/admin")
@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
}
