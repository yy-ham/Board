package com.example.demo.entity;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@DynamicInsert
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
	private Date regdate;
	private int hit;
}
