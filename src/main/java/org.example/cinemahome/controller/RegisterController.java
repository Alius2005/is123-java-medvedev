package org.example.cinemahome.controller;

import org.example.cinemahome.dto.UserDto;
import org.example.cinemahome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired private UserService userService;

    @PostMapping("/register")
    public String register(@ModelAttribute UserDto dto) {
        userService.registerUser(dto);
        return "redirect:/login?registered";
    }
}
