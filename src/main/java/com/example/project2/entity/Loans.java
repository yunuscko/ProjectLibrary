package com.example.project2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "loans")
@Getter
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Books books;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members members;

    @OneToMany
    private Set<Books> loanBooks;

    @Temporal(TemporalType.DATE)
    private LocalDate loadDate;

    @Temporal(TemporalType.DATE)
    private LocalDate returnDate;

    private boolean isReturned;



}
