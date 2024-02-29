package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmark")
@Tag(name = "Bookmark", description = "Bookmark API")
public class BookmarkApiController {
    @GetMapping("/{id}")
    @Operation(summary = "찜 목록 전체 조회")
    public ResponseBookmark showBookmarkAll(
            @PathVariable(required = false)
            @Schema(nullable = true)
            @Parameter(description = "찜 목록 소유 회원 번호")
            long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId
    ){
        return null;
    }
    @PostMapping("/{id}")
    @Operation(summary = "찜 등록")
    public void addBookmark(
            @PathVariable
            @Parameter(description = "찜 등록할 게시물 번호")
            long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId
    ){

    }
    @DeleteMapping("/{id}")
    @Operation(summary = "찜 삭제")
    public void deleteBookmark(
            @PathVariable
            @Parameter(description = "찜 삭제할 게시물 번호")
            long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId
    ){

    }
}
