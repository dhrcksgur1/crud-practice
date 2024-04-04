package com.example.restapi.service;

import com.example.restapi.domain.Article;
import com.example.restapi.dto.AddArticleRequest;
import com.example.restapi.dto.UpdateArticleRequest;
import com.example.restapi.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor //생성자
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: " + id));
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: " +id));
        article.update(request.getTitle(),request.getContent());
        return article;
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }
}
