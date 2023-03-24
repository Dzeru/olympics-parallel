package com.dzeru.olympicsparallel.controller;

import com.dzeru.olympicsparallel.model.User;
import com.dzeru.olympicsparallel.service.SignUpService;
import com.dzeru.olympicsparallel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;
    private final SignUpService signUpService;

    @Autowired
    public UserController(UserService userService, SignUpService signUpService) {
        this.userService = userService;
        this.signUpService = signUpService;
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(User user, @RequestParam String repeatPassword, Model model) {
        Map<String, Object> modelSignUp = signUpService.signUp(user, repeatPassword);

        if (modelSignUp.isEmpty()) {
            return "redirect:/login";
        } else {
            model.addAllAttributes(modelSignUp);
            return "signup";
        }
    }
}
