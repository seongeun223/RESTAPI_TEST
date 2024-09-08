package org.example.restapitest.comment.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restapitest.comment.domain.dto.CommentDTO;
import org.example.restapitest.comment.domain.dto.CreateCommentDTO;
import org.example.restapitest.comment.domain.entity.CommentEntity;
import org.example.restapitest.comment.repository.CommentRepository;
import org.example.restapitest.post.domain.entity.PostEntity;
import org.example.restapitest.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 특정 게시글의 댓글 조회
    public List<CommentEntity> findCommentsByPost(int postId) {
        log.info("전체 댓글 조회 중...");
        return postRepository.findById(postId).get().getComment();
    }

    // 전체 댓글 조회
    public List<CommentEntity> findAllComments() {
        return commentRepository.findAll();
    }

    // 댓글 등록
    public CommentEntity registComment(CreateCommentDTO commentDTO) {

        PostEntity foundPost= postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. postId: " + commentDTO.getPostId()));

        CommentEntity commentEntity = CommentEntity.builder()
                .postId(foundPost)
                .content(commentDTO.getContent())
                .createDate(LocalDateTime.now())
                .build();

        return commentRepository.save(commentEntity);
    }

    // 댓글 수정
    public CommentEntity updateComment(int commentId, CommentDTO modifyInfo) {

        // 댓글 찾기
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. comment: " + commentId));

        // 댓글 내용 수정
        comment.setContent(modifyInfo.getContent());

        LocalDateTime existingCreateDate = comment.getCreateDate();
        comment.setCreateDate(existingCreateDate);

        // 저장
        return commentRepository.save(comment);
    }


    // 댓글 삭제
    public void deleteComment(int commentId) {
        commentRepository.deleteById(commentId);
    }
}

