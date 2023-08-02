package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.BoardDAO;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO boardDAO;
	
	@GetMapping("/board/list")
	public String list(Model model) {
		model.addAttribute("list", boardDAO.findAll());
		return "/board/list";
	}
}
