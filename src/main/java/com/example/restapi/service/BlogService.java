package com.example.restapi.service;

import com.example.restapi.domain.Article;
import com.example.restapi.dto.AddArticleRequest;
import com.example.restapi.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //생성자
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }
}
