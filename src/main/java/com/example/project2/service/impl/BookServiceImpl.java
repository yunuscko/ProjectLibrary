package com.example.project2.service.impl;

import com.example.project2.dtos.BookDto;
import com.example.project2.entity.Books;
import com.example.project2.repository.BookRepository;
import com.example.project2.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addBook(BookDto bookDto) {
        Books books=modelMapper.map(bookDto,Books.class);
        bookRepository.save(books);
    }

    @Override
    public boolean doesBookExist(String bookTitle) {
        Optional<Books> optionalBooks=bookRepository.getBookByTitle(bookTitle);
        return optionalBooks.isPresent();
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Books> booksList=bookRepository.findAll();

        List<BookDto> bookDtos=booksList.stream()
                .map(books -> modelMapper.map(books,BookDto.class))
                .collect(Collectors.toList());

        return bookDtos;
    }

    @Override
    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
