package org.example.restapitest.post.service;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restapitest.exception.ResourceNotFoundException;
import org.example.restapitest.post.domain.dto.PostDTO;
import org.example.restapitest.post.domain.entity.PostEntity;
import org.example.restapitest.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;

    // 게시글 조회
    public List<PostEntity> findAllPosts() {
        log.info("전체 게시글 조회 중...");
        return postRepository.findAll();
    }

    // 게시글 단일 조회
    public PostEntity findPostById(int postId) {

        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("게시글이 존재하지 않습니다."));
    }

    // 게시글 등록
    public PostEntity registPost(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();

        postEntity.setTitle(postDTO.getTitle());
        postEntity.setContent(postDTO.getContent());

        return postRepository.save(postEntity);
    }

    // 게시글 수정
    public PostEntity updatePost(int postId, PostDTO modifyInfo) {

        PostEntity post= postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. postId: " + postId));

        post = post.toBuilder()
                .title(modifyInfo.getTitle())
                .content(modifyInfo.getContent())
                .build();

        return postRepository.save(post);
    }

    // 게시글 삭제
    public void deletePostById(int postId) {

        postRepository.deleteById(postId);
    }


}
