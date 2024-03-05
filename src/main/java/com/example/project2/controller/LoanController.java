package com.example.project2.controller;

import com.example.project2.dtos.BookDto;
import com.example.project2.dtos.LoanDto;
import com.example.project2.dtos.MembersDto;
import com.example.project2.entity.Books;
import com.example.project2.service.LoanService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Resource
    private LoanService loanService;

    @PostMapping("/barrowBook")
    public ResponseEntity barrowBook(@RequestBody MembersDto membersDto, @RequestBody BookDto bookDto){
        try {
            loanService.barrowBook(membersDto,bookDto);
            return new ResponseEntity("kitap ödünç verildi", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllLoans")
    public ResponseEntity<List<Books>> getAllLoans(){
        try {
            List<LoanDto> loanDtoList=loanService.getAllLoans();
            return new ResponseEntity(loanDtoList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity returnBookById(@PathVariable Long bookId){
        try {
            loanService.returnBookById(bookId);
            return new ResponseEntity("kitap iade edildi",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
