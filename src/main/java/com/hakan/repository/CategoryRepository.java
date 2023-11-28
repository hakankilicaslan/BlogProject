package com.hakan.repository;

import com.hakan.entity.Category;
import com.hakan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findByNameIgnoreCase(String name);

    Boolean existsByName(String name);

}
