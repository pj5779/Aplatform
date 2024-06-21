package com.community.fo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.fo.jpa.entity.CommentEntity;
import com.community.fo.jpa.entity.NestedCommentEntity;
import com.community.fo.jpa.repository.NestedCommentRepository;

@Service
public class NestedCommentServiceImpl implements NestedCommentService {

	@Autowired
	private NestedCommentRepository nestedCommentRepository;
	
	@Override
	public List<NestedCommentEntity> getNestedCommentList(CommentEntity commentEntity) {
		return nestedCommentRepository.findByCommentEntityOrderByInsrtDtmDesc(commentEntity);
	}
	
	@Override
	public void insertNestedComment(NestedCommentEntity nestedCommentEntity) {
		nestedCommentRepository.save(nestedCommentEntity);
	}
	
	@Override
	public void deleteNestedComment(int nstdcSq) {
		nestedCommentRepository.deleteById(nstdcSq);
	}
	
	@Override
	public NestedCommentEntity getNestedComment(int nstdcSq) {
		return nestedCommentRepository.findById(nstdcSq).get();
	}
}
