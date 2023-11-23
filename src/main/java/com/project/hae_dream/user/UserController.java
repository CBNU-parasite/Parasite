package com.project.hae_dream.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @GetMapping("/auth/join")
    public String join() {
        return "/user/user-join";
    }
    @PostMapping("/auth/joinProc")
    public String joinProc(@Valid UserDto.RequestUserDto userDto, BindingResult bindingResult, Model model) {

        /* 검증 */
        if(bindingResult.hasErrors()) {
            /* 회원가입 실패 시 입력 데이터 값 유지 */
            model.addAttribute("userDto", userDto);

            /* 유효성 검사를 통과하지 못 한 필드와 메시지 핸들링 */
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put("valid_"+error.getField(), error.getDefaultMessage());
                log.info("회원가입 실패 ! error message : "+error.getDefaultMessage());
            }

            /* Model에 담아 view resolve */
            for(String key : errorMap.keySet()) {
                model.addAttribute(key, errorMap.get(key));
            }

            /* 회원가입 페이지로 리턴 */
            return "/user/user-join";
        }

        // 회원가입 성공 시
        userService.userJoin(userDto);
        log.info("회원가입 성공");
        return "redirect:/auth/login";
    }
}
