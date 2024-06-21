package com.community.fo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void insertAnswer(@PathVariable int brdSq, @RequestBody AnswerEntity answerEntity, HttpSession session) {
		MemberEntity member = (MemberEntity) session.getAttribute("member");
		answerEntity.setInsrtMbrSq(member);
		answerEntity.setBoardEntity(boardService.findById(brdSq));
		answerService.insertAnswer(answerEntity);
	}

	// 답변 삭제
	@DeleteMapping("/answer/delete/{answrSq}")
	public void deleteAnswer(@PathVariable int answrSq) {
		if (answerService.getAnswer(answrSq) != null) {
			answerService.deleteAnswer(answrSq);
		}
	}

}
