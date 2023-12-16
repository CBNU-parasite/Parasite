package com.project.hae_dream.controller;

import com.project.hae_dream.dto.BoardDTO;
import com.project.hae_dream.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/index")
    public String index() {
        return "/board/boardPagebefore";
    }

//    @GetMapping("/view")
//    public String viewArticle(){
//            return "/board/boardPagebefore";
//    }

    @GetMapping("/save")
    public String createArticle(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {
            return "redirect:/user/login";
        }
        else {
            model.addAttribute("userName", session.getAttribute("userName"));
            model.addAttribute("log", "logOut");
        }
        return "/board/boardPageafter";
    }
    @PostMapping("/save")
    public String create(@ModelAttribute BoardDTO boardDTO, Model model, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        System.out.println("boardDTO = "+boardDTO);
        boardDTO.setBoardWriter(session.getAttribute("userName").toString());
        boardService.save(boardDTO);
        return "redirect:/board/view";
    }

    @GetMapping("/view")
    public String findAll(Model model, HttpServletRequest request){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList",boardDTOList);
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {
            model.addAttribute("log", "logIn");
        }
        else {
            model.addAttribute("userName", session.getAttribute("userName"));
            model.addAttribute("log", "logOut");
        }
        return "/board/boardPagebefore";
    }

}