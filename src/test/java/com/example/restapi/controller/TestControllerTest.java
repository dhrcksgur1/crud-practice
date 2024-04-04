package com.example.restapi.controller;

import com.example.restapi.domain.Member;
import com.example.restapi.repository.MemberRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
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
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//클래스 명에다가 alt + Enter 눌러서 create Test가능

@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); //context 셋업을 해줄건데 context는 25라인의 context.
    }

    @AfterEach  //어떤 메서드 실행 후 가지고있는데이터 모두 삭제
    public void cleanUp(){
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers: 멤버 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception{


        //given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동")); //멤버 리포지토리에 저장
        //when
        final ResultActions result = mockMvc.perform(get(url) // 1 //mockMVC로 url을 수행하라고 위임 가져온 결과를 토대로 객체를 받아온다.
                .accept(MediaType.APPLICATION_JSON)); // 2

        //then
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }

}


