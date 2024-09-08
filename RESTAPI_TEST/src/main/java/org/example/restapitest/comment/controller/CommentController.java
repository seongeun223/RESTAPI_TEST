package org.example.restapitest.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restapitest.comment.domain.dto.CreateCommentDTO;
import org.example.restapitest.comment.domain.entity.CommentEntity;
import org.example.restapitest.comment.service.CommentService;
import org.example.restapitest.common.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 특정 게시글의 댓글 조회
    @Operation(summary = "특정 게시글의 댓글 조회")
    @GetMapping("/{postId}")
    public ResponseEntity<ResponseMessage> findCommentsByPost(@PathVariable int postId) {

        List<CommentEntity> comment = commentService.findCommentsByPost(postId);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("comment", comment);

        return ResponseEntity.ok()
                .body(new ResponseMessage(HttpStatus.OK, postId+"번 게시글의 댓글들을 불러옵니다...", responseMap));
    }

    @Operation(summary = "전체 댓글 조회")
    @GetMapping("")
    public ResponseEntity<ResponseMessage> findAllComments() {

        List<CommentEntity> comment = commentService.findAllComments();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("comment", comment);

        return ResponseEntity.ok()
                .body(new ResponseMessage(HttpStatus.OK, "모든 댓글들을 불러옵니다...", responseMap));
    }

    // 댓글 등록
    @PostMapping("")
    @Operation(summary = "댓글 등록")
    public ResponseEntity<ResponseMessage> regist(@RequestBody CreateCommentDTO commentDTO) {

        CommentEntity savedComment = commentService.registComment(commentDTO);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("comment", savedComment);

        return ResponseEntity
                .created(URI.create("/comments/" + savedComment.getCommentId()))
                .body(new ResponseMessage(HttpStatus.CREATED, savedComment.getPostId().getPostId()+"번 게시글의 댓글 등록 성공~!", responseMap));
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<ResponseMessage> deleteComment(@PathVariable int commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) // 204 상태 코드 반환
                .body(new ResponseMessage(HttpStatus.NO_CONTENT, "댓글 삭제 성공"));
    }

}
