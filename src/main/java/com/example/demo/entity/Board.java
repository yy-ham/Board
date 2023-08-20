package com.example.demo.entity;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "board")
@SequenceGenerator(
		name = "board_no_seq"
	    , sequenceName = "board_no_seq"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class Board {
	@Id
	@GeneratedValue(
	    	strategy = GenerationType.SEQUENCE
	    	, generator = "board_no_seq"
	    )
	private int board_no;
	
	private String title;
	private String content;
	private String writer;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String regdate;
	private int hit;
	
	//파일 업로드
	@Transient
	private MultipartFile uploadFile;
	private String fname;
}
