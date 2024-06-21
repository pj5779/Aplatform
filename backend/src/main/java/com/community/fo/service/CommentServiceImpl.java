package com.community.fo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.fo.jpa.entity.AnswerEntity;
import com.community.fo.jpa.entity.BoardEntity;
import com.community.fo.jpa.entity.CommentEntity;
import com.community.fo.jpa.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public List<CommentEntity> getCommentList(BoardEntity boardEntity) {
		return commentRepository.findByBoardEntityOrderByInsrtDtmDesc(boardEntity);
	}
	
	@Override
	public List<CommentEntity> getCommentList(AnswerEntity answerEntity) {
		return commentRepository.findByAnswerEntityOrderByInsrtDtmDesc(answerEntity);
	}
	
	@Override
	public void insertComment(CommentEntity commentEntity) {
		commentRepository.save(commentEntity);
	}
	
	@Override
	public void deleteComment(int cmntSq) {
		commentRepository.deleteById(cmntSq);
	}
	
	@Override
	public CommentEntity getComment(int cmntSq) {
		return commentRepository.findById(cmntSq).get();
	}
	
	@Override
	public CommentEntity findById(int cmntSq) {
		CommentEntity comment = commentRepository.findById(cmntSq).get();
		return comment;
	}
}
