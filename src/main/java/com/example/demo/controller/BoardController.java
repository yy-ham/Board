package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.BoardService;

import lombok.Setter;

@Controller
@Setter
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/list")
	public void list(Model model) {
		model.addAttribute("list", boardService.findAll());
	}
}
