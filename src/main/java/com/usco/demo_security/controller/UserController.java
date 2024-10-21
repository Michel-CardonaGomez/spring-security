package com.usco.demo_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping
public class UserController {

    @GetMapping("/new")
    public String hello() {
        return "crear";
    }

    @GetMapping("/edit")
    public String helloSecured() {
        return "editar";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/delete")
    public String delete() {
        return "eliminar";
    }

    @GetMapping("/")
    public String index() {
        return "inicio";
    }
}
