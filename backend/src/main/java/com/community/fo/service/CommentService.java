package com.community.fo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.community.fo.jpa.entity.AnswerEntity;
import com.community.fo.jpa.entity.BoardEntity;
import com.community.fo.jpa.entity.CommentEntity;

@Service
public interface CommentService {
	
	public List<CommentEntity> getCommentList(BoardEntity boardEntity);
	
	public List<CommentEntity> getCommentList(AnswerEntity answerEntity);
	
	public void insertComment(CommentEntity commentEntity);
	
	public void deleteComment(int cmntSq);
	
	public CommentEntity getComment(int cmntSq);
	
	public CommentEntity findById(int cmntSq);
}
