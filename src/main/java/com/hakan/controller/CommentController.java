package com.hakan.controller;

import com.hakan.dto.request.CommentSaveRequestDto;
import com.hakan.entity.Comment;
import com.hakan.service.CommentService;
import com.hakan.service.PostService;
import lombok.RequiredArgsConstructor;
import static com.hakan.constant.EndPoints.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(ROOT+COMMENT)
public class CommentController {

    private final CommentService commentService;

    @PostMapping(CREATEBYPOSTID)
    public ResponseEntity<String> createComment(@RequestBody CommentSaveRequestDto dto, @PathVariable Long postId) {
        commentService.createComment(dto, postId);
        return ResponseEntity.ok("Yorum kaydetme işlemi tamamlanmıştır...");
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Comment>> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<Optional<Comment>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findById(id));
    }

    @GetMapping(FINDALLCOMMENTBYID)
    public ResponseEntity<List<Comment>> findAllCommentByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.findAllCommentByPostId(postId));
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
        return ResponseEntity.ok("Yorum silme işlemi tamamlanmıştır.");
    }

}
