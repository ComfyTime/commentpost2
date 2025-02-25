package org.example.commentpost2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@RequiredArgsConstructor

public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String content) {
        this.content = content;
    }

    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }

    public void update(String content) {
        this.content = content;
    }
}
