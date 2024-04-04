package com.example.restapi.dto;

import com.example.restapi.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Dictionary;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;

    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

}
