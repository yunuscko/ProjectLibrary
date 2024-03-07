package com.example.project2.controller;

import com.example.project2.dtos.AuthorDto;
import com.example.project2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public ResponseEntity addAuthor(@RequestBody AuthorDto authorDto) throws Exception {
        try {
            authorService.addAuthor(authorDto);
            return new ResponseEntity<>("author successfully added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getAllAuthors")
    public ResponseEntity getAllAuthors(){
        try {
            return new ResponseEntity<>(authorService.getAllAuthors(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/exists")
    public ResponseEntity<Boolean> isAuthorExist(@RequestParam String authorName){
        try {
           Boolean isExist;
                  isExist=authorService.isAuthorExist(authorName);
            return new ResponseEntity<>(isExist,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBooksByAuthor")
    public ResponseEntity getBooksByAuthor(@RequestParam String authorName){
        try {
            return new ResponseEntity<>(authorService.getBooksByAuthor(authorName),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAuthor/{authorId}")
    public ResponseEntity deleteAuthorById(@PathVariable Long authorId){
        try {
            authorService.deleteAuthorById(authorId);
            return new ResponseEntity("author deleted",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
