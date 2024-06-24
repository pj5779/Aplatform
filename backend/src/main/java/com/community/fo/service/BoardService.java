package com.community.fo.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.community.fo.jpa.entity.BoardEntity;

@Service
public interface BoardService {

	public List<BoardEntity> findAll();

	public void insertBoard(BoardEntity boardEntity, MultipartFile file);

	public BoardEntity findById(int brdSq);

	public void updateBoard(BoardEntity updatedBoard);
	
	public void boardHits(int brdSq);
	
	public List<BoardEntity> searchTitle(String brdTl, String brdCtgryCd);
	
	public List<BoardEntity> searchContent(String brdCntnt, String brdCtgryCd);
	
	public ResponseEntity<Resource> downloadFile(int brdSq);

}
