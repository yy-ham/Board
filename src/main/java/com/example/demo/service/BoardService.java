package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.entity.Board;

import lombok.Setter;

@Service
@Setter
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	public List<Board> findAll(){
		return boardDAO.findAll();
	}
	
	public void insert(int board_no, String title, String content, String writer) {
		boardDAO.insert(board_no, title, content, writer);
	}
	
	public int getNextNo() {
		return boardDAO.getNextNo();
	}
	
	public void save(Board board) {
		boardDAO.save(board);
	}
}
