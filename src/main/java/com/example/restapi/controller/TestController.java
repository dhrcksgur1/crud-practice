package com.example.restapi.controller;

import com.example.restapi.domain.Member;
import com.example.restapi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TestController {


    //의존성 주입
    @Autowired
    TestService testService;

    @GetMapping
    public String index(){
        return "hello world";
    }

    @GetMapping("/test")
    public List<Member> getAllMember(){
        List<Member> members = testService.getAllmembers();
        return members;
    }
}
