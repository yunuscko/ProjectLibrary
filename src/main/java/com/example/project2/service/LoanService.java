package com.example.project2.service;

import com.example.project2.dtos.BookDto;
import com.example.project2.dtos.LoanDto;
import com.example.project2.dtos.MembersDto;

import java.util.List;

public interface LoanService {

    void barrowBook(MembersDto membersDto, BookDto bookDto);

    List<LoanDto> getAllLoans();

    void returnBookById(Long loanId);
}
