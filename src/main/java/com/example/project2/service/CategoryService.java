package com.example.project2.service;

import com.example.project2.dtos.BookDto;
import com.example.project2.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<BookDto> getAllBookByCategory(String categoryName);

    void addCategory(CategoryDto categoryDto);
}
