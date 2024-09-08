package org.example.restapitest.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restapitest.common.ResponseMessage;
import org.example.restapitest.post.domain.dto.PostDTO;
import org.example.restapitest.post.domain.entity.PostEntity;
import org.example.restapitest.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 전체 조회
    @Operation(summary = "게시글 전체 조회")
    @GetMapping("")
    public ResponseEntity<ResponseMessage> findAllPosts() {

        List<PostEntity> posts = postService.findAllPosts();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("posts", posts);

        return ResponseEntity.ok()
                .body(new ResponseMessage(HttpStatus.OK, "게시글 전체 조회 성공", responseMap));
    }

    // 게시글 단일 조회
    @Operation(summary = "게시글 단일 조회")
    @GetMapping("/{postId}")
    public ResponseEntity<ResponseMessage> findPostById(@PathVariable int postId) {
        PostEntity post = postService.findPostById(postId); // 게시글 조회

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("post", post);

        return ResponseEntity.ok()
                .body(new ResponseMessage(HttpStatus.OK, "게시글 조회 성공", responseMap));
    }

    // 게시글 등록
    @PostMapping("")
    @Operation(summary = "게시글 등록")
    public ResponseEntity<ResponseMessage> regist(@RequestBody PostDTO postDTO) {

        PostEntity savedTest = postService.registPost(postDTO);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("post", savedTest);

        return ResponseEntity
                .created(URI.create("/posts/" + savedTest.getPostId()))
                .body(new ResponseMessage(HttpStatus.CREATED, "게시글 등록 성공", responseMap));
    }

    // post 수정
    @Operation(summary = "게시글 수정", description = "우리 사이트 게시글 수정")
    @PutMapping("/{postId}")
    public ResponseEntity<ResponseMessage> modifyPost(@PathVariable int postId, @RequestBody PostDTO modifyInfo) {

        PostEntity updatedPost = postService.updatePost(postId, modifyInfo);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("post", updatedPost);

        return ResponseEntity.created(URI.create("/entity/TestEntity/" + postId))
                .body(new ResponseMessage(HttpStatus.OK, "게시글 수정 성공", responseMap));
    }

    // 게시글 삭제
    @Operation(summary = "게시글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게시글 삭제 성공!"),
            //  204 No Content 대신 200 OK를 사용하여 본문에 메시지를 포함할 수 있게 했다.
            @ApiResponse(responseCode = "400", description = "잘못 입력된 파라미터")
    })
    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseMessage> deletePost(@PathVariable int postId) {

        postService.deletePostById(postId);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "게시글 삭제 성공");

        return ResponseEntity.ok()
                .body(new ResponseMessage(HttpStatus.OK, "게시글 삭제 성공", responseMap));

    }
}
