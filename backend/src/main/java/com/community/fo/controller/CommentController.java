package com.community.fo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.community.fo.jpa.entity.CommentEntity;
import com.community.fo.jpa.entity.MemberEntity;
import com.community.fo.jpa.entity.NestedCommentEntity;
import com.community.fo.service.AnswerService;
import com.community.fo.service.BoardService;
import com.community.fo.service.CommentService;
import com.community.fo.service.NestedCommentService;

import jakarta.servlet.http.HttpSession;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private AnswerService answerService;

	@Autowired
	private NestedCommentService nestedCommentService;

	// board 댓글 등록
	@PostMapping("/comment/insert/board/{brdSq}")
	public void insertBoardComment(@PathVariable int brdSq, @RequestBody CommentEntity commentEntity, HttpSession session) {
		MemberEntity member = (MemberEntity) session.getAttribute("member");
		commentEntity.setInsrtMbrSq(member);
		commentEntity.setBoardEntity(boardService.findById(brdSq));
		commentService.insertComment(commentEntity);
	}
	
	// answer 댓글 등록
	@PostMapping("/comment/insert/answer/{answrSq}")
	public void insertAnswerComment(@PathVariable int answrSq, @RequestBody CommentEntity commentEntity, HttpSession session) {
		MemberEntity member = (MemberEntity) session.getAttribute("member");
		commentEntity.setInsrtMbrSq(member);
		commentEntity.setAnswerEntity(answerService.findById(answrSq));
		commentService.insertComment(commentEntity);
	}

	// 댓글 삭제
	@DeleteMapping("/comment/delete/{cmntSq}")
	public void deleteComment(@PathVariable int cmntSq) {
		if (commentService.getComment(cmntSq) != null) {
			commentService.deleteComment(cmntSq);
		}
	}
	
}
