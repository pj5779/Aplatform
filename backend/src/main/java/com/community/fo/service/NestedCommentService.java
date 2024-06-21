package com.community.fo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.community.fo.jpa.entity.CommentEntity;
import com.community.fo.jpa.entity.NestedCommentEntity;

@Service
public interface NestedCommentService {

	public List<NestedCommentEntity> getNestedCommentList(CommentEntity commentEntity);
	
	public void insertNestedComment(NestedCommentEntity nestedCommentEntity);
	
	public void deleteNestedComment(int nstdcSq);
	
	public NestedCommentEntity getNestedComment(int nstdcSq);
}
