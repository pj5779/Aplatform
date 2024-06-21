package com.community.fo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.community.fo.jpa.entity.MemberEntity;
import com.community.fo.jpa.entity.NestedCommentEntity;
import com.community.fo.service.CommentService;
import com.community.fo.service.NestedCommentService;

import jakarta.servlet.http.HttpSession;

@RestController
public class NestedCommentController {

	@Autowired
	private NestedCommentService nestedCommentService;

	@Autowired
	private CommentService commentService;

	// 대댓글 등록
	@PostMapping("/nestedComment/insert/{cmntSq}")
	public void insertNestedComment(@PathVariable int cmntSq, @RequestBody NestedCommentEntity nestedCommentEntity,
			HttpSession session) {
		MemberEntity member = (MemberEntity) session.getAttribute("member");
		nestedCommentEntity.setInsrtMbrSq(member);
		nestedCommentEntity.setCommentEntity(commentService.findById(cmntSq));
		nestedCommentService.insertNestedComment(nestedCommentEntity);
	}
	
	// 대댓글 삭제
	@DeleteMapping("/nestedComment/delete/{nstdcSq}")
	public void deleteNestedComment(@PathVariable int nstdcSq) {
		if(nestedCommentService.getNestedComment(nstdcSq) != null) {
			nestedCommentService.deleteNestedComment(nstdcSq);
		}
	}
}
