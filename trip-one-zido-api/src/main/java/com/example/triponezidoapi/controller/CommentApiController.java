package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@Tag(name = "Comment", description = "Comment API")
public class CommentApiController {
    @PostMapping("/")
    @Operation(summary = "댓글 등록")
    public void addComment(
            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId,

            @RequestBody
            @Parameter(description = "댓글 등록 정보")
            RequestComment requestComment
    ){

    }
    @PutMapping("/{id}")
    @Operation(summary = "댓글 수정")
    public void updateComment(
            @PathVariable
            @Parameter(description = "수정할 댓글 번호")
            long id,

            @RequestBody
            @Parameter(description = "수정 내용")
            RequestComment requestComment
    ){

    }
    @DeleteMapping("/{id}")
    @Operation(summary = "댓글 삭제")
    public void deleteComment(
            @PathVariable
            @Parameter(description = "삭제할 댓글 번호")
            long id
    ){

    }
}
