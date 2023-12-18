package com.project.hae_dream.controller;

import com.project.hae_dream.dto.AnalyzeDTO;
import com.project.hae_dream.dto.UserAccountDTO;
import com.project.hae_dream.entity.UserAccountEntity;
import com.project.hae_dream.repository.AnalyzeRepository;
import com.project.hae_dream.repository.UserAccountRepository;
import com.project.hae_dream.service.AnalyzeService;
import com.project.hae_dream.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@SessionAttributes()
public class UserAccountController {

    private final AnalyzeRepository analyzeRepository;
    private final UserAccountService userAccountService;
    private final AnalyzeService analyzeService;
    @Autowired private HttpSession session;
    @Autowired private UserAccountRepository userAccountRepository;

    @GetMapping("/user/signup")
    public String signForm(){
        return "account/signup";
    }
    @PostMapping("/user/signup")
    public String signup(@ModelAttribute UserAccountDTO userAccountDTO){
        System.out.println("UserAccountController.signup");
        System.out.println("userAccountDTO = " + userAccountDTO);
        userAccountService.save(userAccountDTO);
        return "account/login";
    }

    @GetMapping("/user/login")
    public String loginForm(){
        return "account/login";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute UserAccountDTO userAccountDTO){
        log.info("UserAccountController.login"); //잘 넘어가느 지확인
        log.info("userAccountDTO = {}", userAccountDTO);// 잘넘어가는지 확인
        UserAccountDTO loginResult = userAccountService.login(userAccountDTO);
        if(loginResult!=null){
            // 로그인 성공
            session.setAttribute("loginId",loginResult.getUserId());
            session.setAttribute("userName", loginResult.getUserName());
            log.info("로그인 성공 - {}", loginResult.getUserId());
            return "redirect:/index";
        }
        else{
            //로그인 실패
            return "account/login";
        }
    }

    @GetMapping("/user/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        session.removeAttribute("loginId");

        return "redirect:/index";
    }

    @GetMapping("/user/")
    public String findAll(Model model ){
        List<UserAccountDTO> userAccountDTOList = userAccountService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model 을 사용한다.
        model.addAttribute("userAccountList", userAccountDTOList);
        return "main/list";
    }

    @GetMapping("/account/mainPage")
    public String test(@ModelAttribute UserAccountDTO userAccountDTO, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {
            return "redirect:/user/login";
        }

        return "main/mainPage";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "/account/signup";
    }

    @GetMapping("/account/myPage")
    public String myPage(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {

            return "redirect:/user/login";
        } else {
            List<AnalyzeDTO> searchContent = analyzeService.findFoodLogsByUserNameAndToday(session.getAttribute("userName").toString());
            if (searchContent != null) {
                model.addAttribute("userFoods", searchContent);
            }
            HashMap<String, String> hashMap = userAccountService.searchUser(session.getAttribute("loginId").toString());
            model.addAttribute("userInfo", hashMap);
            model.addAttribute("userName", session.getAttribute("userName"));
            model.addAttribute("log", "logOut");
            return "account/myPage";
        }
    }
    @GetMapping("/account/myInfo")
    public String myInfo(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {
            model.addAttribute("log", "logIn");
        } else {
            model.addAttribute("userName", session.getAttribute("userName"));
            model.addAttribute("log", "logOut");
            HashMap<String, String> hashMap = userAccountService.searchUser(session.getAttribute("loginId").toString());
            model.addAttribute("userInfo", hashMap);
        }

        return "account/myInfo";
    }

    @PostMapping("/account/myInfo")
    public String changeBodyInfo(HttpServletRequest request ,@RequestParam("userTall") String userTall, @RequestParam("userWeight") String userWeight){
        HttpSession session = request.getSession(false);
        Optional<UserAccountEntity> byUserId = userAccountRepository.findByUserId(session.getAttribute("loginId").toString());

        UserAccountEntity user = byUserId.get();
        user.setUserTall(userTall);
        user.setUserWeight(userWeight);

        userAccountRepository.save(user);
        return "redirect:/account/myPage";
    }
}
