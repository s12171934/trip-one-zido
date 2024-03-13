package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.RequestComment;
import com.example.triponezidoapi.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentApiController.class)
public class CommentApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("댓글 등록")
    void postComment() throws Exception {

        RequestComment requestComment = new RequestComment(8L,"테스트",8L,200L);

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();
        // 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestComment);
        // 객체 출력 확인
        System.out.println(jsonMember);

        mockMvc.perform(post("/api/comment/")
                        .sessionAttr("id",8L) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk())
                .andDo(print());

        verify(commentService).addComment(eq(8L), any(RequestComment.class));
    }

    @Test
    @DisplayName("댓글 수정")
    void putComment() throws Exception {
        RequestComment requestComment = new RequestComment(8L,"테스트용 수정",8L,200L);

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();
        // 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestComment);
        // 객체 출력 확인
        System.out.println(jsonMember);

        mockMvc.perform(put("/api/comment/{id}",8L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk())
                .andDo(print());

        verify(commentService).updateComment(eq(8L), any(RequestComment.class));
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment() throws Exception {

        mockMvc.perform(delete("/api/comment/{id}",200L))
                .andExpect(status().isOk())
                .andDo(print());

        verify(commentService).deleteComment(eq(200L));
    }
}
