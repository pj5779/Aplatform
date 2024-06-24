package com.community.fo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.community.fo.jpa.entity.AnswerEntity;
import com.community.fo.jpa.entity.BoardEntity;
import com.community.fo.jpa.entity.CommentEntity;
import com.community.fo.jpa.entity.MemberEntity;
import com.community.fo.jpa.entity.NestedCommentEntity;
import com.community.fo.jpa.repository.AnswerRepository;
import com.community.fo.service.AnswerService;
import com.community.fo.service.BoardService;
import com.community.fo.service.CommentService;
import com.community.fo.service.NestedCommentService;

import jakarta.servlet.http.HttpSession;

@RestController
public class AnswerController {
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	NestedCommentService nestedCommentService;
	
	// 답변 채택
	@PostMapping("/answer/selection/{answrSq}")
	public void answerSelection(@PathVariable int answrSq) {
		answerService.checkById(answrSq);
	}
	
	// 답변 등록
	@PostMapping("/answer/insert/{brdSq}")
	public ResponseEntity<String> insertAnswer(@PathVariable int brdSq, @RequestBody AnswerEntity answerEntity, HttpSession session) {
		try {			
			MemberEntity member = (MemberEntity) session.getAttribute("member");
			answerEntity.setInsrtMbrSq(member);
			answerEntity.setBoardEntity(boardService.findById(brdSq));
			answerService.insertAnswer(answerEntity);
			
	        return ResponseEntity.ok("답변이 성공적으로 작성되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("등록에 실패했습니다: " + e.getMessage());
		}
	}

	// 답변 삭제
	@DeleteMapping("/answer/delete/{answrSq}")
	public void deleteAnswer(@PathVariable int answrSq) {
		if (answerService.getAnswer(answrSq) != null) {
			answerService.deleteAnswer(answrSq);
		}
	}
	
	// 답변 상세
	@GetMapping("/answer/detail/{answrSq}")
	public AnswerEntity findById(@PathVariable int answrSq) {
		AnswerEntity answer = answerService.findById(answrSq);
		List<CommentEntity> comments = commentService.getCommentList(answer);
		for (CommentEntity commentEntity : comments) {
			List<NestedCommentEntity> nestedComments = nestedCommentService.getNestedCommentList(commentEntity);
			commentEntity.setNestedCommentEntity(nestedComments);
		}
		answer.setCommentEntity(comments);
		return answer;
	}

}
