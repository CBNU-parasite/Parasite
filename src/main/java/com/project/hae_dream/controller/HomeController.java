package com.project.hae_dream.controller;

import com.project.hae_dream.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MainService mainService;

    @GetMapping("/index")
    public String index(Model model, HttpServletRequest request) throws NullPointerException{

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {
            model.addAttribute("log", "logIn");
        } else {
            model.addAttribute("userName", session.getAttribute("userName"));
            model.addAttribute("log", "logOut");
            HashMap<String, String> hashMap = mainService.searchUser(session.getAttribute("loginId").toString());
            model.addAttribute("userInfo", hashMap);
        }

        return "main/mainPage";
    }
}
