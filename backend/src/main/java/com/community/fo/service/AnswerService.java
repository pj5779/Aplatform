package com.community.fo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.community.fo.jpa.entity.AnswerEntity;
import com.community.fo.jpa.entity.BoardEntity;

@Service
public interface AnswerService {

	public boolean checkById(int answrSq);
	
	public List<AnswerEntity> getAnswerList(BoardEntity boardEntity);
	
	public void insertAnswer(AnswerEntity answerEntity);
	
	public void deleteAnswer(int answrSq);
	
	public AnswerEntity getAnswer(int answrSq);
	
	public AnswerEntity findById(int answrSq);
}
