package com.community.fo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.community.fo.jpa.entity.BoardEntity;
import com.community.fo.jpa.entity.CommentEntity;
import com.community.fo.jpa.entity.MemberEntity;
import com.community.fo.jpa.entity.NestedCommentEntity;
import com.community.fo.jpa.repository.BoardRepository;
import com.community.fo.service.BoardService;
import com.community.fo.service.CommentService;
import com.community.fo.service.NestedCommentService;


import jakarta.servlet.http.HttpSession;

@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentService commentService;

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private NestedCommentService nestedCommentService;

	// 글 목록
	@GetMapping("/board/list")
	public List<BoardEntity> getAllList() {
		List<BoardEntity> boardList = boardService.findAll();
		return boardList;
	}

	// 카테고리별 글 목록
	@GetMapping("/board/list/{brdCtgryCd}")
	public List<BoardEntity> getCategoryList(@PathVariable(required = false) String brdCtgryCd,
			@RequestParam(required = false) String brdTl, @RequestParam(required = false) String brdCntnt) {
		List<BoardEntity> boardList;
		if ("general".equals(brdCtgryCd) && brdTl == null && brdCntnt == null) {
			boardList = boardRepository.findByBrdCtgryCd("general");

		} else if ("feedback".equals(brdCtgryCd) && brdTl == null && brdCntnt == null) {
			boardList = boardRepository.findByBrdCtgryCd("feedback");

		} else if ("qna".equals(brdCtgryCd) && brdTl == null && brdCntnt == null) {
			boardList = boardRepository.findByBrdCtgryCd("qna");

		} else if ("general".equals(brdCtgryCd) && brdTl != null && brdCntnt == null) {
			boardList = boardService.searchTitle(brdTl, "general");

		} else if ("feedback".equals(brdCtgryCd) && brdTl != null && brdCntnt == null) {
			boardList = boardService.searchTitle(brdTl, "feedback");

		} else if ("qna".equals(brdCtgryCd) && brdTl != null && brdCntnt == null) {
			boardList = boardService.searchTitle(brdTl, "qna");

		} else if ("general".equals(brdCtgryCd) && brdTl == null && brdCntnt != null) {
			boardList = boardService.searchContent(brdCntnt, "general");

		} else if ("feedback".equals(brdCtgryCd) && brdTl == null && brdCntnt != null) {
			boardList = boardService.searchContent(brdCntnt, "feedback");

		} else if ("qna".equals(brdCtgryCd) && brdTl == null && brdCntnt != null) {
			boardList = boardService.searchContent(brdCntnt, "qna");

		} else {
			boardList = boardService.findAll();
		}
		return boardList;
	}

	// 글 등록
	@PostMapping("/board/insert")
	public void insertBoard(@RequestPart("board") BoardEntity boardEntity, 
							@RequestPart(value = "file", required = false) MultipartFile file,
							HttpSession session) {
		MemberEntity member = (MemberEntity) session.getAttribute("member");
		boardEntity.setInsrtMbrSq(member);
		boardService.insertBoard(boardEntity, file);
	}
	
	// 파일 다운로드
	@GetMapping("/board/download/{brdSq}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int brdSq) {
      return boardService.downloadFile(brdSq);
    }

	// 글 상세
	@GetMapping("/board/detail/{brdSq}")
	public BoardEntity findById(@PathVariable int brdSq) {
		BoardEntity board = boardService.findById(brdSq);
		boardService.boardHits(brdSq); // 조회수
		List<CommentEntity> comments = commentService.getCommentList(board);
		for (CommentEntity commentEntity : comments) {
			List<NestedCommentEntity> nestedComments = nestedCommentService.getNestedCommentList(commentEntity);
			commentEntity.setNestedCommentEntity(nestedComments);
		}
		board.setCommentEntity(comments);
		return board;
	}

	// 글 수정
	@PostMapping("/board/update/{brdSq}")
	public void updateBoard(@PathVariable int brdSq, @RequestBody BoardEntity boardEntity) {
		boardService.updateBoard(boardEntity);
	}

	// 글 삭제
	@DeleteMapping("/board/delete/{brdSq}")
	public void deleteBoard(@PathVariable int brdSq) {
		boardRepository.deleteById(brdSq);
	}
}
