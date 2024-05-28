package com.community.fo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.fo.jpa.entity.UserEntity;
import com.community.fo.jpa.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }
    
}
