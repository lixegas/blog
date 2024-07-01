package com.lixega.blog.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author")
    private UserAccount author;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

}