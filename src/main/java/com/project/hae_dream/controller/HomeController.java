package com.project.hae_dream.controller;

import com.project.hae_dream.dto.BoardDTO;
import com.project.hae_dream.entity.BoardEntity;
import com.project.hae_dream.entity.BoardFileEntity;
import com.project.hae_dream.service.BoardService;
import com.project.hae_dream.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MainService mainService;
    private final BoardService boardService;

    @GetMapping("/index")
    public String index(Model model, HttpServletRequest request) throws NullPointerException{

        List<BoardDTO> boardDTOList = boardService.findAll();
        Collections.reverse(boardDTOList);

        model.addAttribute("boardList",boardDTOList.subList(0,4));

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {
            model.addAttribute("log", "logIn");
        } else {
            model.addAttribute("userName", session.getAttribute("userName"));
            model.addAttribute("log", "logOut");
            HashMap<String, String> hashMap = mainService.searchUser(session.getAttribute("loginId").toString());
            model.addAttribute("userInfo", hashMap);
        }

        return "main/mainPage";
    }
}
