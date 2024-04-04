package com.example.restapi;

import org.junit.jupiter.api.*;

public class JUNITCycleTest {
    //BeforeAll 1회 실행되기 때문에, static
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    //BeforeEach
    @BeforeEach
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1(){
        System.out.println("test1");
    }

    @Test
    public void test2(){
        System.out.println("test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
    }

    //AfterAll
    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }


    //AfterEach
    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach");
    }


}
