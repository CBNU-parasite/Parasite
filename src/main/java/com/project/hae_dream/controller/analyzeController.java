package com.project.hae_dream.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.hae_dream.dto.FoodRequest;
import com.project.hae_dream.util.FoodApi;
import com.project.hae_dream.util.FoodInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/analyze/selectFood")
    @ResponseBody
    public String handleButtonClick(@ModelAttribute FoodRequest foodRequest) {
        // 클라이언트로부터 받은 데이터를 처리합니다.
        String foodName = foodRequest.getFoodName();
        String nutrCont1 = foodRequest.getNutrCont1();
        String nutrCont2 = foodRequest.getNutrCont2();
        String nutrCont3 = foodRequest.getNutrCont3();
        String nutrCont4 = foodRequest.getNutrCont4();

        // 실제 로직을 수행하고 응답을 생성합니다.
        String responseData = "Data received from the client: " + foodName;

        System.out.println("responseData = " + responseData);
        System.out.println("nutrCont4 = " + nutrCont4);
        System.out.println("nutrCont3 = " + nutrCont3);
        System.out.println("nutrCont2 = " + nutrCont2);
        System.out.println("nutrCont1 = " + nutrCont1);

        return responseData;
    }

    @GetMapping("/analyze/analyzeGraph")
    public String analyzeGraph() {
        return "analyze/analyzeGraph";
    }

}
