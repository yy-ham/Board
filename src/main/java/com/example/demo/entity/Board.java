package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name = "board")
public class Board {
	@Id
	private int board_no;
	
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int hit;
}
