package com.example.project2.service;

import com.example.project2.dtos.BookDto;
import com.example.project2.dtos.LoanDto;
import com.example.project2.dtos.MembersDto;
import com.example.project2.entity.Members;

import java.util.List;

public interface LoanService {

    void barrowBook(Long membersId, Long bookId);

    List<LoanDto> getAllLoans();

    void returnBookById(Long loanId);
}
