package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.RequestComment;
import com.example.triponezidoapi.service.CommentService;
import nonapi.io.github.classgraph.utils.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommentApiControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentApiController commentApiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentApiController).build();
    }

    @Test
    @DisplayName("댓글 등록")
    void postComment() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 8L); // 세션 속성 설정

        mockMvc.perform(post("/api/comment/")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"requestComment\": { \"comment\": \"Test comment\" } }"))
                .andExpect(status().isOk());

        verify(commentService).addComment(eq(8L), any(RequestComment.class));
    }

    @Test
    @DisplayName("댓글 수정")
    void putComment() throws Exception {
        mockMvc.perform(put("/api/comment/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"requestComment\": { \"text\": \"Updated comment\" } }"))
                .andExpect(status().isOk());

        verify(commentService).updateComment(eq(1L), any(RequestComment.class));
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment() throws Exception {
        mockMvc.perform(delete("/api/comment/1"))
                .andExpect(status().isOk());

        verify(commentService).deleteComment(eq(1L));
    }
}
