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

import java.io.IOException;
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
    public String createArticle() {
        return "/board/boardPageafter";
    }
    @PostMapping("/save")
    public String create(@ModelAttribute BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = "+boardDTO);
        boardService.save(boardDTO);
        return "/main/mainPage";
    }

    @GetMapping("/view")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList",boardDTOList);

        return "/board/boardPagebefore";
    }

}