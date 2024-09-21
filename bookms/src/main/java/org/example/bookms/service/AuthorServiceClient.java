package org.example.bookms.service;


import org.example.bookms.model.AuthorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "author-microservice", url = "http://localhost:8081")
public interface AuthorServiceClient {

    @GetMapping("/authors/{id}")
    AuthorDTO getAuthorById(@PathVariable("id") Long authorId);
}