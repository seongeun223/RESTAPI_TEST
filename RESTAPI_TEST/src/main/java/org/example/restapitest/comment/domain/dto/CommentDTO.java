package org.example.restapitest.comment.domain.dto;

import lombok.*;
import org.example.restapitest.post.domain.entity.PostEntity;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDTO {

    private int commentId; // 댓글 ID
    private String content; // 댓글 내용
    private Date createDate; // 생성 일자 (수정하지 않음)

}
