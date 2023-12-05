package com.project.hae_dream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {
            model.addAttribute("log", "logIn");
        } else {
            model.addAttribute("log", "logOut");
        }

        return "main/mainPage";
    }
}
