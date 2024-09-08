package org.example.restapitest.comment.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.restapitest.post.domain.entity.PostEntity;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity(name = "comment")
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    // JSON으로 변환될 때 포함되지 않으므로, 클라이언트가 해당 필드에 접근할 수 없다.
    private PostEntity postId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "create_date")
    @CreatedDate
    private LocalDateTime createDate;
}
