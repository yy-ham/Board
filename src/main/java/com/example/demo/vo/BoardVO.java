package com.example.demo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int board_no;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int hit;
	private String fname;
}
