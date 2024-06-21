package com.community.fo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.community.fo.jpa.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

	List<BoardEntity> findByBrdCtgryCd(String brdCtgryCd);
	
	List<BoardEntity> findAllByOrderByInsrtDtmDesc();
	
	List<BoardEntity> findByBrdTlAndBrdCtgryCdOrderByInsrtDtmDesc(String brdTl, String brdCtgryCd);
	
	List<BoardEntity> findByBrdCntntAndBrdCtgryCdOrderByInsrtDtmDesc(String brdCntnt, String brdCtgryCd);

}