package com.lixega.blog.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name= "user")
public class UserAccount {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private LocalDateTime birthday;


    @OneToMany(mappedBy = "author")
    private List<Article> articles;




}
