package org.example.restapitest.comment.repository;

import org.example.restapitest.comment.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
