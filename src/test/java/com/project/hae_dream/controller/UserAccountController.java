package com.project.hae_dream.controller;

import com.project.hae_dream.dto.UserAccountDTO;
import com.project.hae_dream.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;
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
    public String login(@ModelAttribute UserAccountDTO userAccountDTO, HttpSession session){
        System.out.println("UserAccountController.login"); //잘 넘어가느 지확인
        System.out.println("userAccountDTO = " + userAccountDTO);// 잘넘어가는지 확인
        UserAccountDTO loginResult = userAccountService.login(userAccountDTO);
        if(loginResult!=null){
            // 로그인 성공
            session.setAttribute("loginId",loginResult.getUserId());
            return "main/main";
        }
        else{
            //로그인 실패
            return "account/login";
        }
    }
    @GetMapping("/user/")
    public String findAll(Model model ){
        List<UserAccountDTO> userAccountDTOList = userAccountService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model 을 사용한다.
        model.addAttribute("userAccountList", userAccountDTOList);
        return "main/list";
    }

}
