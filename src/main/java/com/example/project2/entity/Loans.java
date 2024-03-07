package com.example.project2.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "loans")
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Books books;

    @ManyToOne
    private Members members;

    @OneToMany
    private Set<Books> loanBooks;

    private LocalDate loadDate;

    private LocalDate returnDate;

    private boolean isReturned;
}
