package com.example.restapi.controller;

import com.example.restapi.domain.Article;
import com.example.restapi.dto.AddArticleRequest;
import com.example.restapi.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    //직렬화, 역질렬화를 위한 클래스
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle: 블로그글 추가 성공")
    @Test
    public void addArticle() throws Exception {
        //3:35분 설명

        //given (선언)
        //url
        final String url = "/api/articles";
        //title
        final String title ="myTitle";
        //content
        final String content = "myContent";
        //dto
        final AddArticleRequest articleRequest = new AddArticleRequest(title,content);
        //직렬화까지
        final String requestBody = objectMapper.writeValueAsString(articleRequest);


        //when( 실제 데이터 요청. 즉, post로 데이터를 보낸다.)
        ResultActions result = mockMvc.perform(post(url)
                //application json
                .contentType(MediaType.APPLICATION_JSON)
                //requestBody로
                .content(requestBody));



        //then(이 결과의 상태가 200이면 OR 201 CREATE = state가 ok면) 행위에대한 결과
        //데이터가 잘 들어갔는지, 조회. 이때 BeforeEach로 데이터 모두 삭제했음
        result.andExpect(status().isCreated());

        List<Article> articleList =blogRepository.findAll();

        //사이즈검증
        //assertThat().isEqualTo() : 왼쪽 조건의 연산이, 오른쪽 값과 같냐.
        assertThat(articleList.size()).isEqualTo(1);

        //내가 넣은 title이 맞냐?
        assertThat(articleList.get(0).getTitle()).isEqualTo(title);

        // 내용 검증
        assertThat(articleList.get(0).getContent()).isEqualTo(content); //여기에 다른값넣으면 테스트 실패뜸

    }

}