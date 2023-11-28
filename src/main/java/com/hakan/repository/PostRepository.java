package com.hakan.repository;

import com.hakan.entity.Category;
import com.hakan.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllPostByUserId(Long id);
    List<Post> findByTitleIgnoreCase(String title);
    List<Post> findAllByOrderByPublishedAt();
    List<Post>findByCategoryList_Id(Long categoryId);
}
