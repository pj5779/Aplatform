package com.community.fo.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.community.fo.jpa.entity.BoardEntity;
import com.community.fo.jpa.repository.BoardRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Override
	public List<BoardEntity> findAll() {
		return boardRepository.findAllByOrderByInsrtDtmDesc();
	}


	@Override
	public void insertBoard(BoardEntity boardEntity, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String filePath = "C:/uploads/" + fileName; // 원하는 경로로 변경
            try {
                file.transferTo(new File(filePath));
                boardEntity.setFileName(fileName); // BoardEntity에 파일 이름 저장
                boardEntity.setFilePath(filePath); // BoardEntity에 파일 경로 저장
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("File upload error: " + e.getMessage());
            }
        }
		
		boardRepository.save(boardEntity);
	}

	@Override
	public BoardEntity findById(int brdSq) {
		BoardEntity board = boardRepository.findById(brdSq).get();
		return board;
	}

	@Override
	public void updateBoard(BoardEntity boardEntity) {
		BoardEntity findBoard = boardRepository.findById(boardEntity.getBrdSq())
				.orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다: " + boardEntity.getBrdSq()));

		findBoard.setBrdTl(boardEntity.getBrdTl());
		findBoard.setBrdCntnt(boardEntity.getBrdCntnt());
		findBoard.setUpdtDtm(LocalDateTime.now());

		boardRepository.save(findBoard);
	}

	@Override
	public void boardHits(int brdSq) {
		BoardEntity board = boardRepository.findById(brdSq)
				.orElseThrow(() -> new EntityNotFoundException("BoardEntity with id " + brdSq + " not found"));

		board.setBrdHits(board.getBrdHits() + 1);
		boardRepository.save(board);
	}
	
	@Override
	public List<BoardEntity> searchTitle(String brdTl, String brdCtgryCd) {
		return boardRepository.findByBrdTlAndBrdCtgryCdOrderByInsrtDtmDesc(brdTl, brdCtgryCd);
	}
	
	@Override
	public List<BoardEntity> searchContent(String brdCntnt, String brdCtgryCd) {
		return boardRepository.findByBrdCntntAndBrdCtgryCdOrderByInsrtDtmDesc(brdCntnt, brdCtgryCd);
	}
}
