package com.example.project2.service;

import com.example.project2.dtos.AuthorDto;
import com.example.project2.dtos.BookDto;

import java.util.List;
import java.util.Set;

public interface AuthorService {

    void addAuthor(AuthorDto authorDto) throws Exception;

    List<AuthorDto> getAllAuthors();

    Boolean isAuthorExist(String authorName);

    Set<BookDto> getBooksByAuthor(String authorName);

    void deleteAuthorById(Long authorId);

}
