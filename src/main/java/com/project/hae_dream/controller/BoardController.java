package com.project.hae_dream.controller;

import com.project.hae_dream.dto.BoardDTO;
import com.project.hae_dream.entity.BoardEntity;
import com.project.hae_dream.repository.BoardRepository;
import com.project.hae_dream.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

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
    public String findAll(Model model, HttpServletRequest request, @RequestParam(defaultValue = "0") int page){
//        List<BoardDTO> boardDTOList = boardService.findAll();
//        Collections.reverse(boardDTOList);

        System.out.println(page);
        int pageSize = 4;

        Sort.Order order = Sort.Order.desc("createdTime");
        Sort sort = Sort.by(order);

        Page<BoardDTO> boardPage = boardService.getItems(PageRequest.of(page, pageSize, sort));
        model.addAttribute("boardList",boardPage);
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


    @PostMapping("/deleteItem")
    public String deleteItem(@RequestParam("boardId") Long boardId) {
        boardRepository.deleteById(boardId);

        return "redirect:/board/view";
    }

}