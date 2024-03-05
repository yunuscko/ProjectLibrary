package com.example.project2.service.impl;

import com.example.project2.dtos.BookDto;
import com.example.project2.dtos.CategoryDto;
import com.example.project2.entity.Books;
import com.example.project2.entity.Category;
import com.example.project2.repository.CategoryRepository;
import com.example.project2.service.CategoryService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private ModelMapper modelMapper;

    @Override
    public List<BookDto> getAllBookByCategory(String categoryName) {
        List<Books> optionalBooks=categoryRepository.getBooksByCategoryName(categoryName);
        List<BookDto> bookDtoList=new LinkedList<>();

        for(Books books:optionalBooks){
            BookDto bookDto=new BookDto();
            bookDto.setCategoryId(books.getCategoryId());
            bookDto.setId(books.getId());
            bookDto.setTitle(books.getTitle());
            bookDto.setAvailable(books.isAvailable());
            bookDto.setPublishDate(books.getPublishDate());

            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    @Override
    public void addCategory(CategoryDto categoryDto) {
        Category category=modelMapper.map(categoryDto,Category.class);
        categoryRepository.save(category);
    }
}
