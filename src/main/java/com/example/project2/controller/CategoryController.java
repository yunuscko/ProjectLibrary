package com.example.project2.controller;

import com.example.project2.dtos.BookDto;
import com.example.project2.dtos.CategoryDto;
import com.example.project2.entity.Books;
import com.example.project2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllBooksByAuthor")
    public ResponseEntity<List<Books>> getAllBookByCategory(@RequestParam String categoryName){
        try {
           List<BookDto> booksList= categoryService.getAllBookByCategory(categoryName);
            return new ResponseEntity(booksList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(Collections.emptySet(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addCategory")
    public ResponseEntity addCategory(@RequestBody CategoryDto categoryDto){
        try {
            categoryService.addCategory(categoryDto);
            return new ResponseEntity("category successfully added",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
