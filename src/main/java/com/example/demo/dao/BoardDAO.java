package com.example.demo.dao;


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
}
