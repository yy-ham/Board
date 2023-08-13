package com.example.demo.controller;


import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class BoardController {
	
	//페이징 처리 변수
	public int pageSIZE = 5; //한 페이지에 보여줄 게시글 수
	public int totalRecord = 0; //전체 게시글 수
	public int totalPage = 1; //전체 페이지 수
	
	@Autowired
	private BoardService boardService;
	
	//게시글 조회
	@GetMapping("/board/list/{pageNUM}")
	public String list(Model model, @PathVariable int pageNUM) {
//		model.addAttribute("list", boardService.findAll());
		
		totalRecord = boardService.count();
		totalPage = (int)Math.ceil((double)totalRecord/pageSIZE);
		
		int start = (pageNUM - 1) * pageSIZE + 1;
		int end = start + pageSIZE - 1;
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", boardService.selectAll(start, end));
		
		return "/board/list";
	}
	
	//게시글 작성 get
	@GetMapping("/board/insert")
	public void insert() {
		
	}
	
	//게시글 작성 및 수정
	@PostMapping("/board/save")
	public ModelAndView save(Board board, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/board/list/1");
		
		//파일 업로드
		String path = request.getServletContext().getRealPath("/files");
		System.out.println("path:" + path);
		String fname = null;
		MultipartFile uploadFile = board.getUploadFile();
		fname = uploadFile.getOriginalFilename();
		
		if(fname != null && !fname.equals("")) {
			//파일이 있는 경우
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(path + "/" + fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fileOutputStream);
				fileOutputStream.close();
			} catch (Exception e) {
				System.out.println("[Exception] " + e.getMessage());
			}
		}else {
			//파일이 없는 경우
			fname = "";
		}
		
		board.setFname(fname);
		boardService.save(board);
		
		return mav;
	}
	
	//게시글 상세
	@GetMapping("/board/detail/{board_no}")
	public String detail(Model model, @PathVariable int board_no) {
		boardService.updateHit(board_no);
		model.addAttribute("detail", boardService.findById(board_no));
		return "/board/detail";
	}
	
	//게시글 수정
	@GetMapping("/board/update/{board_no}")
	public String update(Model model, @PathVariable int board_no) {
		model.addAttribute("board", boardService.findById(board_no));
		return "/board/update";
	}
	
	//게시글 삭제
	@GetMapping("/board/delete/{board_no}")
	public ModelAndView delete(@PathVariable int board_no, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/board/list/1");

		//게시글 삭제 시 해당 게시글에 포함되어 있는 파일도 삭제
		String path = request.getServletContext().getRealPath("/files");
		String fname = boardService.findById(board_no).getFname();
		int re = boardService.delete(board_no);
		
		if(re > 0) {
			//게시글 삭제 성공 -> 파일도 삭제
			try {
				File file = new File(path + "/" + fname);
				file.delete();
			} catch (Exception e) {
				System.out.println("[Exception] " + e.getMessage());
			}
		}
		return mav;
	}
}
