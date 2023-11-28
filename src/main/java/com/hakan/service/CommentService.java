package com.hakan.service;

import com.hakan.dto.request.CommentSaveRequestDto;
import com.hakan.entity.Comment;
import com.hakan.entity.Post;
import com.hakan.exception.BlogException;
import com.hakan.exception.ErrorType;
import com.hakan.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private  final CommentRepository commentRepository;
    private  final PostService postService;

    public String createComment(CommentSaveRequestDto dto, Long postId){

        Optional<Post> optionalPost= postService.findById(postId);

        if (optionalPost.isPresent()){
            Post post=optionalPost.get();

            Comment comment=Comment.builder()
                    .commenter(dto.getCommenter())
                    .content(dto.getContent())
                    .post(post)
                    .build();

            post.getCommentList().add(comment);
            Comment createdComment=commentRepository.save(comment);
            return createdComment.getId()+" numaralı yorum yayınlanmıştır.";
        } else {
            throw  new BlogException(ErrorType.POST_NOT_FOUND);
        }
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);

    }
    public List<Comment> findAllCommentByPostId(Long postId) {
        return commentRepository.findAllCommentByPostId(postId);
    }

    public void deleteById(Long id) {
        Optional<Comment> optionalComment = findById(id);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(id);
        } else {
            throw new BlogException(ErrorType.COMMENT_NOT_FOUND);
        }
    }

}

