package com.project.hae_dream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class boardController {

    @GetMapping("/board/index")
    public String index() {
        return "/board/boardPagebefore";
    }

    @GetMapping("/board/create")
    public String createArticle() {
        return "/board/boardPageafter";
    }

    @GetMapping("/board/test")
    public String test(){return "/board/boardPagebeforeSecond";}
}
