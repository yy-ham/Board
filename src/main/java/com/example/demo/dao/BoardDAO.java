package com.example.demo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Board;

import jakarta.transaction.Transactional;

@Repository
public interface BoardDAO extends JpaRepository<Board, Integer> {
	@Modifying
	@Transactional
	@Query(value = "update board set hit = hit + 1 where board_no = ?", 
			nativeQuery = true)
	public void updateHit(int board_no);
	
	@Query(value = "select board_no, title, content, writer, regdate, hit from "
			+ "(select A.*, rownum n from (select * from board order by board_no) A) "
			+ "where n between ?1 and ?2",nativeQuery = true)
	public List<Board> selectAll(int start, int end);
}
