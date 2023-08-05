package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Board;

@Repository
public interface BoardDAO extends JpaRepository<Board, Integer> {

	@Query(value = "insert into board(board_no, title, content, writer)"
			+ "values(?, ?, ?, ?)", nativeQuery = true)
	public void insert(int board_no, String title, String content, String writer);
	
	@Query(value = "select max(board_no) from board", nativeQuery = true)
	public int getNextNo();
}
