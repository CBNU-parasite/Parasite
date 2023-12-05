package com.project.hae_dream.controller;

import com.project.hae_dream.dto.UserAccountDTO;
import com.project.hae_dream.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@SessionAttributes()
public class UserAccountController {

    private final UserAccountService userAccountService;
    @Autowired private HttpSession session;
  
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
    public String myPage(){
        return "/account/myPage";
    }
    @GetMapping("/account/myInfo")
    public String myInfo(){
        return "/account/myInfo";
    }
}
