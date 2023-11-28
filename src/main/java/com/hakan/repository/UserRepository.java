package com.hakan.repository;

import com.hakan.entity.Category;
import com.hakan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    List<User> findByNameIgnoreCase(String name);
    Boolean existsByName(String name);
    List<User> findAllByNameStartsWith(String word);
}
