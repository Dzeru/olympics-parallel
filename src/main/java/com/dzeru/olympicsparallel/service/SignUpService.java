package com.dzeru.olympicsparallel.service;

import com.dzeru.olympicsparallel.model.User;
import com.dzeru.olympicsparallel.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, Object> signUp(User user, String repeatPassword) {
        var username = user.getUsername();
        var password = user.getPassword();
        log.info("Start sign up process for user " + username);
        Map<String, Object> model = new HashMap<>();

        if (!user.getPassword().equals(repeatPassword)) {
            model.put("error", "Passwords are not equal!");
        } else {
            User userFromDB = userRepository.findByUsername(username);

            if (userFromDB != null) {
                model.put("error", "User already exists!");
            }
            if (password.length() < 4) {
                model.put("error", "Password is too short!");
            }
            if (username == null || username.isEmpty()) {
                model.put("error", "Username is empty!");
            }

            if (model.isEmpty()) {
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
            }
            log.info("Finish sign up process for user " + username);
        }

        return model;
    }
}