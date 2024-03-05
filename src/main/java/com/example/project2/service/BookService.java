package com.example.project2.service;

import com.example.project2.dtos.BookDto;

import java.util.List;

public interface BookService {

    void addBook(BookDto bookDto);

    boolean doesBookExist(String bookName);

    List<BookDto> getAllBooks();

    void deleteBookById(Long bookId);
}
