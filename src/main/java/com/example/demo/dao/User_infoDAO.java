package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User_info;

import jakarta.transaction.Transactional;

public interface User_infoDAO extends JpaRepository<User_info, String> {
	
	@Modifying
	@Transactional
	@Query(value = "insert into user_info (user_no, id, pwd, email, nickname, role) "
			+ "values (:#{#u.user_no}, :#{#u.id}, :#{#u.pwd}, :#{#u.email}, "
			+ ":#{#u.nickname}, :#{#u.role})", nativeQuery = true)
	public int insert(User_info u);
	
	@Query(value = "select nvl(max(user_no), 0) + 1 from user_info", nativeQuery = true)
	public int getNextNo();

}
