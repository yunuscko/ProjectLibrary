package com.example.project2.controller;

import com.example.project2.dtos.BookDto;
import com.example.project2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class    BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody BookDto bookDto){
        try {
            bookService.addBook(bookDto);
            return new ResponseEntity("book successfully added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> doesBookExist(@RequestParam String bookName){
        try {
            Boolean doesExist=bookService.doesBookExist(bookName);
            return new ResponseEntity<>(doesExist,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity geAllBooks(){
        try {

            return new ResponseEntity(bookService.getAllBooks(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity deleteBookById(@PathVariable Long bookId){
        try {
            bookService.deleteBookById(bookId);
            return new ResponseEntity("book deleted",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
