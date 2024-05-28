package com.community.fo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.community.fo.jpa.entity.UserEntity;
import com.community.fo.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; 
 

@CrossOrigin(origins = "http://localhost:8800")
@RestController
public class DataController {
     
    @Autowired
    private UserService userService;
    
    
    @GetMapping("/")
    public String home(){

        System.out.println("hello");

        return "Data 준비 중 .... ";
    }

    @GetMapping("/api")
    public String api(){

        System.out.println("hello");

        return "AppData 준비 중 .... ";
    }

    @PostMapping("/findAll")
    public List<UserEntity> findAll() {

        //TODO: process POST request
         
        return userService.findAll();
    }
    

}
