package com.example.project2.service.impl;

import com.example.project2.dtos.AuthorDto;
import com.example.project2.dtos.BookDto;
import com.example.project2.entity.Authors;
import com.example.project2.entity.Books;
import com.example.project2.repository.AuthorRepository;
import com.example.project2.repository.BookRepository;
import com.example.project2.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addAuthor(AuthorDto authorDto) throws Exception {
        Optional<Authors>optionalAuthors=authorRepository.getAuthorsByAuthorsName(authorDto.getAuthorsName());
        if(optionalAuthors.isPresent()){
            throw new Exception("Author is not found");
        }
        Authors authors=modelMapper.map(authorDto,Authors.class);
        authorRepository.save(authors);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Authors> authors=authorRepository.findAll();
       return authors.stream()
               .map(authors1 -> modelMapper.map(authors1,AuthorDto.class))
               .collect(Collectors.toList());
    }

    @Override
    public Boolean isAuthorExist(String authorName) {
        Optional<Authors> optionalAuthors = authorRepository.getAuthorsByAuthorsName(authorName);
        return optionalAuthors.isPresent();
    }


    @Override
    public Set<BookDto> getBooksByAuthor(String authorName) {
        Set<Books> books=bookRepository.findBooksByAuthorsName(authorName);
        if(books!=null && !books.isEmpty()){
            return books.stream()
                    .map(books1 -> modelMapper.map(books1,BookDto.class))
                    .collect(Collectors.toSet());
        }
            return Collections.emptySet();
    }

    @Override
    public void deleteAuthorById(Long authorId) {
        authorRepository.deleteById(authorId);
    }
}
