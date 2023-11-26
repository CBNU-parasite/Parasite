package com.project.hae_dream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PageController {

    @GetMapping("/")
    public String mainPage(){
        return "beforeLogin.html";
    }
}
