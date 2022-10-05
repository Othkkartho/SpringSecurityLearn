package com.example.springsecuritylearn.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {
    @GetMapping(value = "messages")
    public String messages() {
        return "/user/messages";
    }

    @GetMapping("/api/messages")
    @ResponseBody
    public String apiMessage() {
        return "messages ok";
    }
}
