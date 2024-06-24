package com.community.fo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.fo.jpa.entity.AnswerEntity;
import com.community.fo.jpa.entity.BoardEntity;
import com.community.fo.jpa.repository.AnswerRepository;
import com.community.fo.jpa.repository.BoardRepository;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public boolean checkById(int answrSq) {
		AnswerEntity answerEntity = answerRepository.findById(answrSq).get();
		BoardEntity boardEntity = answerEntity.getBoardEntity();
		
		if(boardEntity.isBrdAnswrSlctnChck()) {
			throw new RuntimeException();
		} else {
			answerEntity.setAnswrSlctnChck(true);
			answerRepository.save(answerEntity);
			boardEntity.setBrdAnswrSlctnChck(true);
			boardRepository.save(boardEntity);
		}
		return true;
	}
	
	@Override
	public List<AnswerEntity> getAnswerList(BoardEntity boardEntity) {
		return answerRepository.findByBoardEntityOrderByInsrtDtmDesc(boardEntity);
	}
	
	@Override
	public void insertAnswer(AnswerEntity answerEntity) {
		answerRepository.save(answerEntity);
	}
	
   @Override
   public void deleteAnswer(int answrSq) {
      AnswerEntity answerEntity = answerRepository.findById(answrSq).get();
      BoardEntity boardEntity = answerEntity.getBoardEntity();
      
      if(answerEntity.getAnswrSlctnChck().TRUE) {
         answerEntity.setAnswrSlctnChck(false);
         answerRepository.save(answerEntity);
         boardEntity.setBrdAnswrSlctnChck(false);
         boardRepository.save(boardEntity);
      }
      answerRepository.deleteById(answrSq);
   }

	
	@Override
	public AnswerEntity getAnswer(int answrSq) {
		return answerRepository.findById(answrSq).get();
	}
	
	@Override
	public AnswerEntity findById(int answrSq) {
		AnswerEntity comment = answerRepository.findById(answrSq).get();
		return comment;
	}

}
