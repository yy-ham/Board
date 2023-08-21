package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User_info {
	@Id
	private int user_no;
	
	private String id;
	private String pwd;
	private String email;
	private String nickname;
	private String signup_date;
	private String role;
}
