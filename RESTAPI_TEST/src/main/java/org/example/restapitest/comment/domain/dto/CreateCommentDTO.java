package org.example.restapitest.comment.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateCommentDTO {

    private int postId;
    private String content;
}
