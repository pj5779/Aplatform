package com.community.fo.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.fo.jpa.entity.AnswerEntity;
import com.community.fo.jpa.entity.BoardEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {
	
	List<AnswerEntity> findByBoardEntityOrderByInsrtDtmDesc(BoardEntity boardEntity);
}
