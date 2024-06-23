package com.lixega.blog.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {

    @Id
    @Column (name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    @Column
    private String title;
    @Column
    private String excerpt;
    @Column (name = "created_at")
    private LocalDateTime createdAt;
    private String content;
    @Column
    private String slug;


    @ManyToOne
    @JoinColumn(name = "author")
    private UserAccount author;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;



}
