package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.Content;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentMapper {
    void addContent(Content content);
}
