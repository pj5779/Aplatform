package com.community.fo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.community.fo.jpa.entity.UserEntity;

@Service
public interface UserService {

    List<UserEntity> findAll() ;
    
}
