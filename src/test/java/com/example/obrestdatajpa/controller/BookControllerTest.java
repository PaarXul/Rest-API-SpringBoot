package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //Se debe especificar que se realizara un test desde spring
class BookControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;
    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Comprobar hola mundo desde Controladores Spring REST")
@Test
    void hello (){
        ResponseEntity<String> response =
            testRestTemplate.getForEntity("/hola", String.class);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(200, response.getStatusCodeValue());
            assertEquals("Hola mundo",response.getBody());
    }

    @Test
    void findAll() {
        ResponseEntity<Book[]> response=
        testRestTemplate.getForEntity("/api/books", Book[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        List<Book> books =  Arrays.asList(response.getBody());

        System.out.println(books.size());
    }

    @Test
    void findOneById() {
        ResponseEntity<Book> response =
                testRestTemplate.getForEntity("/api/books/2", Book.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String  json = """
                {
                    "title": "libro nuevo creado desde spring test",
                    "author": "Gian Parra",
                    "pages": 450,
                    "price": 29.99,
                    "release": "2018-12-01",
                    "online": true
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json,headers);

        ResponseEntity<Book> response =
                testRestTemplate.exchange("/api/books",HttpMethod.POST,request,Book.class);
        Book result = response.getBody();

        assertEquals(1L,result.getId());
        assertEquals("libro nuevo creado desde spring test",result.getTitle());



    }
}