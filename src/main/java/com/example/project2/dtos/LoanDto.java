package com.example.project2.dtos;

import com.example.project2.entity.Books;
import com.example.project2.entity.Members;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class LoanDto {
    private Long id;

    @OneToOne
    private Books booksId;

    @ManyToOne
    private Members membersId;

    @OneToMany
    private Set<Books> loanBooks;

    private Date loadDate;

    private Date returnDate;

    private boolean isReturned;
}
