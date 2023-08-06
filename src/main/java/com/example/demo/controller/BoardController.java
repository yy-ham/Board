package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

import lombok.Setter;

@Controller
@Setter
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//게시글 조회
	@GetMapping("/board/list")
	public void list(Model model) {
		model.addAttribute("list", boardService.findAll());
	}
	
	//게시글 작성 get
	@GetMapping("/board/insert")
	public void insert() {
		
	}
	
	//게시글 작성 post
	@PostMapping("/board/insert")
	public ModelAndView save(Board board) {
		ModelAndView mav = new ModelAndView("redirect:/board/list");
		boardService.save(board);
		return mav;
	}
}
