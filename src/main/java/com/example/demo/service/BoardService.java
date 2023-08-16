package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.db.BoardDBManager;
import com.example.demo.entity.Board;
import com.example.demo.vo.BoardVO;

import lombok.Setter;

@Service
@Setter
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	public List<BoardVO> findAll(HashMap<String, Object> map){
		return BoardDBManager.findAll(map);
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
	
	public List<Board> search(String column, String keyword){
		return boardDAO.search(column, keyword);
	}
}
