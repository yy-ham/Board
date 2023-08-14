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
	
	public void save(Board board) {
		boardDAO.save(board);
	}
	
	public Board findById(int board_no) {
		return boardDAO.findById(board_no).get();
	}
	
	public void updateHit(int board_no) {
		boardDAO.updateHit(board_no);
	}
	
	public int delete(int board_no) {
		return boardDAO.delete(board_no);
	}
	
	public int count() {
		return (int)boardDAO.count();
	}
	
	public List<Board> selectAll(int start, int end){
		return boardDAO.selectAll(start, end);
	}
	
	public int update(Board board) {
		return boardDAO.update(board);
	}
}
