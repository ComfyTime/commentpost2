package org.example.commentpost2.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private Long postId;
    private String content;
}
