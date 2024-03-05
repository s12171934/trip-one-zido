package com.example.triponezidoapi.service;

import com.example.triponezidoapi.mappers.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {
    @Autowired
    static ContentMapper contentMapper;
    
    //삭제
    public static void deleteContent(Long id){
        contentMapper.deleteContent(id);
    }
}
