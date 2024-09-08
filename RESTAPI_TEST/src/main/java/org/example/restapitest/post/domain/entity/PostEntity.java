package org.example.restapitest.post.domain.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "post")
@Table(name = "Post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;


}
