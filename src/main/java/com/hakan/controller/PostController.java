package com.hakan.controller;

import com.hakan.dto.request.PostRequestDto;
import com.hakan.dto.request.PostSaveRequestDto;
import com.hakan.dto.request.UserSaveRequestDto;
import com.hakan.entity.Post;
import com.hakan.entity.User;
import com.hakan.mapper.IPostMapper;
import com.hakan.mapper.IUserMapper;
import com.hakan.service.PostService;
import com.hakan.service.UserService;
import lombok.RequiredArgsConstructor;
import static com.hakan.constant.EndPoints.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(ROOT+POST)
public class PostController {

    private final PostService postService;

    @PostMapping(CREATEBYUSERID)
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto dto, @PathVariable Long userId){
        postService.createPost(dto,userId);
        return ResponseEntity.ok("Post kaydetme işlemi tamamlanmıştır...");
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Post>> getAll(){
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<Optional<Post>> findById(@PathVariable Long id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> update(@RequestBody PostSaveRequestDto dto){
        postService.update(dto);
        return ResponseEntity.ok("Post güncelleme işlemi tamamlanmıştır...");
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        postService.deleteById(id);
        return ResponseEntity.ok("Post silme işlemi tamamlanmıştır.");
    }

    @GetMapping(FINDALLPOSTBYUSERID)
    public ResponseEntity<List<Post>> findAllPostByUserId(@PathVariable Long id){
        return ResponseEntity.ok(postService.findAllPostByUserId(id));
    }

    @GetMapping(FINDBYTITLE)
    public ResponseEntity<List<Post>> findByTitleIgnoreCase(@PathVariable String title) {
        return ResponseEntity.ok(postService.findByTitleIgnoreCase(title));
    }

    @GetMapping(FINDALLBYORDERBYPUBLISHEDAT)
    public ResponseEntity<List<Post>> findAllByOrderByPublishedAt() {
        return ResponseEntity.ok(postService.findAllByOrderByPublishedAt());
    }

    @GetMapping(FINDBYCATEGORYLISTID)
    public ResponseEntity<List<Post>> findByCategoryList_Id(Long categoryId){
        return ResponseEntity.ok(postService.findByCategoryList_Id(categoryId));
    }

}
