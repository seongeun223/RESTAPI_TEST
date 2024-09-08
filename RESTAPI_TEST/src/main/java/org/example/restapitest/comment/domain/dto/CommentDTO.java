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

    private int commentId;
    private PostEntity postId;
    private String content;
    private Date createDate;

}
