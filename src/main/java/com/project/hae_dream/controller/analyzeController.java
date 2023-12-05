package com.project.hae_dream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class analyzeController {

    @GetMapping("/analyze/analyzeSearch")
    public String analyzeSearch(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {
            return "redirect:/user/login";
        } else {
            return "analyze/analyzeSearch";
        }
    }

    @PostMapping("/analyze/analyzeSearch")
    public String postAnalyzeSearch() {
        return null;
    }

    @GetMapping("/analyze/analyzeGraph")
    public String analyzeGraph() {
        return "analyze/analyzeGraph";
    }
}
