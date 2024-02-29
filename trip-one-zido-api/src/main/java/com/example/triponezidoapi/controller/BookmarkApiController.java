package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmark")
@Tag(name = "Bookmark", description = "Bookmark API")
public class BookmarkApiController {
    @GetMapping("/{id}")
    @Operation(summary = "Get Bookmark List", description = "찜목록 전체 조회")
    public ResponseBookmark showBookmarkAll(
            @PathVariable(required = false)
            long id,

            @SessionAttribute(name = "id")
            long sessionId){
        return null;
    }
    @PostMapping("/{id}")
    public void addBookmark(
            @PathVariable
            long id,

            @SessionAttribute(name = "id")
            long sessionId){

    }
    @DeleteMapping("/{id}")
    public void deleteBookmark(
            @PathVariable long id){

    }
}
