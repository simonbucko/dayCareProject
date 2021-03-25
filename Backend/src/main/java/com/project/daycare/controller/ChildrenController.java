package com.project.daycare.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChildrenController {
    @GetMapping("/children")
    public String Index(Model model){

        return "Frontend/html/children";
    }
}
