package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.entity.Board;
import com.example.demo.entity.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@ContextConfiguration(classes = BoardApplication.class)
class BoardApplicationTests {
	
	@Autowired
	EntityManager entityManager;

	@Test
	@Transactional
	void contextLoads() {
		Board board = new Board();
        entityManager.persist(board);
        
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QBoard qBoard = QBoard.board;
        
        String title = "테스트";

        List<Board> result = query
        		.selectFrom(qBoard)
        		.where(qBoard.title.contains(title))
        		.fetch();
        
//        assertThat(result.get(0).getBoard_no()).isEqualTo(board.getBoard_no());
	}

}
