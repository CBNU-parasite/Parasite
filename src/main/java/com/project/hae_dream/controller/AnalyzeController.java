package com.project.hae_dream.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.hae_dream.dto.AnalyzeDTO;
import com.project.hae_dream.dto.request.FoodRequest;
import com.project.hae_dream.entity.AnalyzeEntity;
import com.project.hae_dream.entity.UserAccountEntity;
import com.project.hae_dream.repository.AnalyzeRepository;
import com.project.hae_dream.repository.UserAccountRepository;
import com.project.hae_dream.service.AnalyzeService;
import com.project.hae_dream.util.FoodApi;
import com.project.hae_dream.util.FoodInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class AnalyzeController {

    private final AnalyzeRepository analyzeRepository;
    private final UserAccountRepository userAccountRepository;
    private final AnalyzeService analyzeService;

    @Autowired
    public AnalyzeController(AnalyzeRepository analyzeRepository,
                             UserAccountRepository userAccountRepository) {
        this.analyzeRepository = analyzeRepository;
        this.userAccountRepository = userAccountRepository;
        this.analyzeService = new AnalyzeService(analyzeRepository, userAccountRepository);
    }

    @GetMapping("/analyze/analyzeSearch")
    public String analyzeSearch(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {

            return "redirect:/user/login";
        } else {
            List<AnalyzeDTO> searchContent = analyzeService.findFoodLogsByUserIdAndToday(session.getAttribute("userName").toString());
            if (searchContent != null) {
                model.addAttribute("userFoods", searchContent);
            }

            model.addAttribute("userName", session.getAttribute("userName"));
            model.addAttribute("log", "logOut");
            return "analyze/analyzeSearch";
        }
    }

    @PostMapping("/analyze/analyzeSearch")
    public String postAnalyzeSearch(@RequestParam("query") String qeury, HttpServletRequest request, Model model) throws IOException, InterruptedException {
        FoodApi foodApi = new FoodApi();
        JsonNode jsonNode = foodApi.getFoodJson(qeury);

        List<FoodInfo> foodInfo = foodApi.JsonToFoodInfo(jsonNode);

        HttpSession session = request.getSession(false);

        List<AnalyzeDTO> searchContent = analyzeService.findFoodLogsByUserIdAndToday(session.getAttribute("userName").toString());
        if (searchContent != null) {
            model.addAttribute("userFoods", searchContent);
        }

        model.addAttribute("foodInfo", foodInfo);
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("log", "logOut");

        return "/analyze/analyzeSearch";
    }

    @ResponseBody
    @PostMapping("/analyze/selectFood")
    public String handleButtonClick(@ModelAttribute FoodRequest foodRequest, HttpServletRequest request) {
        // 클라이언트로부터 받은 데이터를 처리합니다.
        String foodName = foodRequest.getFoodName();
        String nutrCont1 = foodRequest.getNutrCont1();
        String nutrCont2 = foodRequest.getNutrCont2();
        String nutrCont3 = foodRequest.getNutrCont3();
        String nutrCont4 = foodRequest.getNutrCont4();

        HttpSession session = request.getSession(false);

        AnalyzeEntity analyzeEntity = new AnalyzeEntity();
        UserAccountEntity userAccountEntity = userAccountRepository.findByUserId(session.getAttribute("userName").toString()).orElse(null);
        analyzeEntity.setUserAccountEntity(userAccountEntity);
        analyzeEntity.setFoodName(foodName);
        analyzeEntity.setKcal(Double.parseDouble(nutrCont1));
        analyzeEntity.setCarbohydrate(Double.parseDouble(nutrCont2));
        analyzeEntity.setProtein(Double.parseDouble(nutrCont3));
        analyzeEntity.setFat(Double.parseDouble(nutrCont4));
        analyzeEntity.setCreatedAt(LocalDateTime.now());

        // 실제 로직을 수행하고 응답을 생성합니다.
        analyzeRepository.save(analyzeEntity);

        return "ok";
    }

    @Transactional
    @PostMapping("/analyze/deleteFood")
    public String postDeleteFood(@RequestParam(required = false) MultiValueMap<String, String> formData, HttpServletRequest request, Model model) throws IOException, InterruptedException {

        String foodName = formData.getFirst("foodName");
        analyzeRepository.deleteByFoodName(foodName);

        Thread.sleep(1000); //1초 대기

        HttpSession session = request.getSession(false);

        List<AnalyzeDTO> searchContent = analyzeService.findFoodLogsByUserIdAndToday(session.getAttribute("userName").toString());
        if (searchContent != null) {
            model.addAttribute("userFoods", searchContent);
        }

//        model.addAttribute("foodInfo", foodInfo);
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("log", "logOut");

        return "/analyze/analyzeSearch";
    }

    @GetMapping("/analyze/analyzeGraph")
    public String analyzeGraph(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {

            return "redirect:/user/login";
        } else {
            List<AnalyzeDTO> searchContent = analyzeService.findFoodLogsByUserIdAndToday(session.getAttribute("userName").toString());
            if (searchContent != null) {
                model.addAttribute("userFoods", searchContent);
            }

            model.addAttribute("userName", session.getAttribute("userName"));
            model.addAttribute("log", "logOut");
            return "analyze/analyzeGraph";
        }
    }

}
