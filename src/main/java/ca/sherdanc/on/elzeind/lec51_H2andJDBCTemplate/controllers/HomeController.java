package ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {
    @GetMapping("/secure")
    public String secureIndex() {
        return "/secure/index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/permission-denied")
    public String permissionDenied() {
        return "/error/permission-denied";
    }
}

