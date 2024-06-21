package com.community.fo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.fo.jpa.entity.CommentEntity;
import com.community.fo.jpa.entity.NestedCommentEntity;

public interface NestedCommentRepository extends JpaRepository<NestedCommentEntity, Integer> {

	List<NestedCommentEntity> findByCommentEntityOrderByInsrtDtmDesc(CommentEntity commentEntity);
}
