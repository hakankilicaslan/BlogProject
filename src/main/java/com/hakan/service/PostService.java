package com.hakan.service;

import com.hakan.dto.request.PostRequestDto;
import com.hakan.dto.request.PostSaveRequestDto;
import com.hakan.dto.request.UserSaveRequestDto;
import com.hakan.entity.Category;
import com.hakan.entity.Comment;
import com.hakan.entity.Post;
import com.hakan.entity.User;
import com.hakan.exception.BlogException;
import com.hakan.exception.ErrorType;
import com.hakan.mapper.ICategoryMapper;
import com.hakan.mapper.IPostMapper;
import com.hakan.mapper.IUserMapper;
import com.hakan.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private Category category;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public String createPost(PostRequestDto dto, Long userId) {

        Optional<User> optionalUser = userService.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Post post = Post.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .categoryList(List.of(category= ICategoryMapper.INSTANCE.categoryDtoToCategory(dto.getCategoryDto())))
                    .user(user)
                    .build();

            user.getPostList().add(post);
            Post createdPost = postRepository.save(post);
            return createdPost.getId()+" numaralı post yayınlanmıştır.";
        } else {
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public void update(PostSaveRequestDto dto){
        Optional<Post> optionalPost = findById(dto.getId());
        Optional<User> optionalUser = userService.findById(dto.getUserId());
        if(optionalPost.isPresent()){
            Post post = Post.builder()
                    .id(dto.getId())
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .user(optionalUser.get())
                    .build();

            postRepository.save(post);

        }else{
            throw new BlogException(ErrorType.POST_NOT_FOUND);
        }
    }



    public void deleteById(Long id) {
        Optional<Post> optionalPost = findById(id);
        if (optionalPost.isPresent()) {
            postRepository.deleteById(id);
        } else {
            throw new BlogException(ErrorType.POST_NOT_FOUND);
        }
    }

    public List<Post> findAllPostByUserId(Long id) {
        Optional<User>optionalUser=userService.findById(id);
        if (optionalUser.isPresent()){
            return postRepository.findAllPostByUserId(id);
        }else {
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
    }

    public List<Post> findByTitleIgnoreCase(String title) {
        return postRepository.findByTitleIgnoreCase(title);
    }

    public List<Post> findAllByOrderByPublishedAt() {
        return postRepository.findAllByOrderByPublishedAt();
    }

    public List<Post> findByCategoryList_Id(Long categoryId) {
        return postRepository.findByCategoryList_Id(categoryId);
    }

}
