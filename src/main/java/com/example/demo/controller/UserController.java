package com.example.demo.controller;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());

        return "userList";
    }


    @GetMapping("{user}")
    public String userEditForm(
            @PathVariable Long user,
            Model model
    ) {
        List<User> users = userRepo.findAllById(Collections.singleton(user));
        User user1 = users.get(0);
        model.addAttribute("user", user1);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") Long user
    ) {
        List<User> users = userRepo.findAllById(Collections.singleton(user));
        User user2 = users.get(0);

        user2.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user2.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user2.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user2);

        return "redirect:/user";
    }

}
