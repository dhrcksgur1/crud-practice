package com.example.restapi.controller;

import com.example.restapi.domain.Article;
import com.example.restapi.dto.AddArticleRequest;
import com.example.restapi.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){  //리스폰스 객체(아티클 도메인) 반환 //리퀘스트바디에 들어온 것이DTO로 반환이 돼서
        Article savedArticle = blogService.save(request);// 이것을 아티클 save하고

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedArticle);
    }
}
