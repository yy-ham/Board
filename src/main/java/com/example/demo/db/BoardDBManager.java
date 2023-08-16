package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.BoardVO;

@Repository
public class BoardDBManager {
	public static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/example/demo/db/dbConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
					new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("[Exception] " + e.getMessage());
		}
	}
	
	public static List<BoardVO> findAll(HashMap<String, Object> map){
		List<BoardVO> list = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList("board.findAll", map);
		sqlSession.close();
		return list;
	}
}
