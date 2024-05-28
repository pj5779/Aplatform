package com.community.fo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.fo.jpa.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    
    
}
