package com.project.hae_dream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "main/mainPage";
    }

    @GetMapping("/analyzing")
    public String analize(){
        return "analyzing/analyze";
    }
}
