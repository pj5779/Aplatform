package com.community.fo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.community.fo.jpa.entity.MemberEntity;
import com.community.fo.jpa.entity.TermsContentEntity;
import com.community.fo.jpa.repository.TermsContentRepository;


@RestController
public class TermsContentController {
   
   @Autowired
   TermsContentRepository termsContentRepository;
   
      @GetMapping("/terms/content")
      public List<TermsContentEntity> getContent() {
         
      return termsContentRepository.findAll();
      
      
      }

}
