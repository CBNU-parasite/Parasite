package com.project.hae_dream.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.hae_dream.util.FoodApi;
import com.project.hae_dream.util.FoodInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class analyzeController {

    @GetMapping("/analyze/analyzeSearch")
    public String analyzeSearch(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {
            return "redirect:/user/login";
        } else {
            model.addAttribute("userName", session.getAttribute("userName"));
            model.addAttribute("log", "logOut");
            return "analyze/analyzeSearch";
        }
    }

    @PostMapping("/analyze/analyzeSearch")
    public String postAnalyzeSearch(@RequestParam("query") String qeury, Model model) throws IOException, InterruptedException {
        FoodApi foodApi = new FoodApi();
        JsonNode jsonNode = foodApi.getFoodJson(qeury);

        List<FoodInfo> foodInfo = foodApi.JsonToFoodInfo(jsonNode);

        model.addAttribute("foodInfo", foodInfo);

        return "/analyze/analyzeSearch";
    }

    @GetMapping("/analyze/analyzeGraph")
    public String analyzeGraph() {
        return "analyze/analyzeGraph";
    }

}
