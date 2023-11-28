package com.hakan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tbl_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commenter;
    @Column(length = 1000)
    private String content;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne
    @JsonIgnore
    private User user;
    @Builder.Default
    private LocalDateTime releasedDate = LocalDateTime.now();
    private int numberOfLike;
}
