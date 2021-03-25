package com.project.daycare.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @GetMapping("/")
    public String Index(Model model){

        return "Frontend/index";
    }
}
