package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.User_infoDAO;
import com.example.demo.entity.User_info;

import lombok.Setter;

@Service
@Setter
public class UserService {
	
	@Autowired
	private User_infoDAO userDAO;
	
	public int insert(User_info user_info) {
		return userDAO.insert(user_info);
	}
	
	public int getNextNo() {
		return userDAO.getNextNo();
	}

}
