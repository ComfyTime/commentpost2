package org.example.commentpost2.service;

import lombok.RequiredArgsConstructor;
import org.example.commentpost2.dto.PostRequestDto;
import org.example.commentpost2.dto.PostResponseDto;
import org.example.commentpost2.entity.Comment;
import org.example.commentpost2.entity.Post;
import org.example.commentpost2.repository.CommentRepository;
import org.example.commentpost2.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public PostResponseDto save(PostRequestDto dto) {
        Post post = new Post(dto.getTitle());
        Post savedPost = postRepository.save(post);
        return new PostResponseDto(savedPost.getId(), savedPost.getTitle());
    }

    public PostResponseDto update(Long postId, PostRequestDto dto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException("해당 id 없음")
        );
        post.update(dto.getTitle());
        return new PostResponseDto(post.getId(), post.getTitle());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll() {
        List<Post> posts = postRepository.findAll();

        List<PostResponseDto> dtos = new ArrayList<>();
        for (Post post : posts) {
            dtos.add(new PostResponseDto(post.getId(), post.getTitle()));
        }
        return dtos;
    }

    @Transactional
    public void test() {
        Post post = postRepository.findById(1L).orElseThrow(
                () -> new IllegalStateException()
        );
        List<Comment> comments = post.getComments();
        comments.remove(0);
    }
}
