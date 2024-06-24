package com.lixega.blog.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
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

    private String title;
    private String excerpt;
    private String slug;
    private String content;

    @Column (name = "created_at")
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "author")
    private UserAccount author;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

}