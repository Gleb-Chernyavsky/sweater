package com.example.demo.controller;

import com.example.demo.domain.Message;
import com.example.demo.domain.User;
import com.example.demo.repos.MessagesRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessagesRepos messagesRepos;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Model model) {
        Iterable<Message> messages = messagesRepos.findAll();
        if(filter != null && !filter.isEmpty()) {
            messages = messagesRepos.findByTag(filter);
        } else {
            messages = messagesRepos.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model
    ){
        Message message = new Message(text, tag, user);
        messagesRepos.save(message);
        Iterable<Message> messages = messagesRepos.findAll();
        model.put("messages", messages);
        return "main";
    }

}