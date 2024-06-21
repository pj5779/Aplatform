package com.community.fo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.community.fo.jpa.entity.AnswerEntity;
import com.community.fo.jpa.entity.BoardEntity;
import com.community.fo.jpa.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	
	List<CommentEntity> findByBoardEntityOrderByInsrtDtmDesc(BoardEntity boardEntity);
	
	List<CommentEntity> findByAnswerEntityOrderByInsrtDtmDesc(AnswerEntity answerEntity);
}
