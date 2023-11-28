package com.hakan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 13)
    private String name;
    @Column(length = 26)
    private String surname;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 20)
    //@Size(min = 10)
    private String password;
    @OneToMany
    @JsonIgnore
    private List<Post> postList;
}
